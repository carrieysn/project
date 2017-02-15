package com.cifpay.lc.core.uid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.std.business.BusinessJUnitTestBase;

/**
 * Test for LcSendIdWorker
 * 
 * 
 *
 */
public class LcSendIdWorkerTest extends BusinessJUnitTestBase {

	@Autowired
	private LcSendIdWorker lcSendIdWorker;

	@Test
	public void testNextId() {
		int mockTotalThreads = 100;
		Date startTime = new Date(System.currentTimeMillis() + 20000L);
		int genIdCountPerThread = 2000;
		Monitor monitor = new Monitor(mockTotalThreads);

		for (int i = 0; i < mockTotalThreads; i++) {
			MockIdConsumerThread mockThread = new MockIdConsumerThread(lcSendIdWorker, startTime, genIdCountPerThread,
					monitor);
			mockThread.start();
		}

		monitor.waitForAllMockThreadsCompleted();
		Set<Long> producedIds = monitor.getProcudedIds();
		int expectedIdTotal = mockTotalThreads * genIdCountPerThread;
		System.out.println(
				"模拟" + mockTotalThreads + "个线程，每个线程生成" + genIdCountPerThread + "个ID，共计划生成" + expectedIdTotal + "个ID");
		System.out.println("期望生成不重复的ID数为：" + expectedIdTotal + "个，实际为：" + producedIds.size() + "个。");
		Assert.assertTrue("多个线程间生成的ID出现重复的情况", producedIds.size() == expectedIdTotal);

		List<Long> idList = new ArrayList<Long>(producedIds);
		Collections.sort(idList);
		Long minId = idList.get(0);
		Long maxId = idList.get(idList.size() - 1);
		System.out.println("实际生成的ID最小值为：" + minId + "，最大值为：" + maxId);
	}

	static class Monitor {
		private int totalThreads;
		private int completedThreads;
		private final Set<Long> procudedIds = new HashSet<Long>();

		public Monitor(int totalThreads) {
			super();
			this.totalThreads = totalThreads;
		}

		public synchronized void addCompletedMockThread() {
			++completedThreads;
			this.notify();
		}

		public synchronized void waitForAllMockThreadsCompleted() {
			while (completedThreads < totalThreads) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void saveProcudedIds(Set<Long> ids) {
			synchronized (procudedIds) {
				procudedIds.addAll(ids);
			}
		}

		public Set<Long> getProcudedIds() {
			return procudedIds;
		}
	}

	static class MockIdConsumerThread extends Thread {

		private LcSendIdWorker lcSendIdWorker;
		private Date startTime;
		private int mockIdTotalPerThread;
		private Monitor monitor;

		public MockIdConsumerThread(LcSendIdWorker lcSendIdWorker, Date startTime, int mockIdTotalPerThread,
				Monitor monitor) {
			super();
			this.lcSendIdWorker = lcSendIdWorker;
			this.startTime = startTime;
			this.mockIdTotalPerThread = mockIdTotalPerThread;
			this.monitor = monitor;
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			long sleepMs = System.currentTimeMillis() - startTime.getTime();
			if (sleepMs > 0) {
				try {
					Thread.sleep(sleepMs);
				} catch (InterruptedException e) {
				}
			}

			try {
				Set<Long> ids = new HashSet<Long>();
				for (int i = 0; i < mockIdTotalPerThread; i++) {
					long id = lcSendIdWorker.nextId();
					System.out.println(threadName + ": " + id);
					Assert.assertTrue("生成的ID出现负数或为0的情况", id > 0);
					ids.add(id);
				}
				Assert.assertTrue("同一线程内生成的ID出现重复的情况", ids.size() == mockIdTotalPerThread);
				monitor.saveProcudedIds(ids);
			} finally {
				monitor.addCompletedMockThread();
			}
		}
	};

}
