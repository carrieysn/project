package com.cifpay.lc.util.uid;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单的AbstractIdWorker实现，通过构造函数直接设置workerId。
 * 
 * 
 *
 */
public class SimpleIdWorker extends AbstractIdWorker {

	private static final Map<Long, SimpleIdWorker> instances = new HashMap<Long, SimpleIdWorker>();
	private long workerId;

	/**
	 * 根据Worker ID获取SimpleIdWorker单实例。
	 * 
	 * @param workerId
	 * @return
	 */
	public static SimpleIdWorker getInstance(int workerId) {
		Long key = Long.valueOf(workerId);
		SimpleIdWorker worker = instances.get(key);
		if (null == worker) {
			synchronized (instances) {
				worker = instances.get(key);
				if (null == worker) {
					SimpleIdWorker tmp = new SimpleIdWorker(key);
					instances.put(key, tmp);
					worker = tmp;
				}
			}
		}
		return worker;
	}

	/**
	 * @param workerId
	 *            有效范围0 ~ 1023。
	 */
	private SimpleIdWorker(long workerId) {
		super();
		this.workerId = workerId;
	}

	@Override
	protected long configureWorkerId() {
		return workerId;
	}

}
