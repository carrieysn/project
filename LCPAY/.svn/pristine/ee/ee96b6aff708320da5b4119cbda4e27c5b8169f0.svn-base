package com.cifpay.lc.core.uid.base;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.core.db.dao.GuidWorkerIdDao;
import com.cifpay.lc.core.db.pojo.GuidWorkerId;
import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.security.MD5Utils;

/**
 * 基于DB管理Snowflake work id的分配。
 * 
 * 
 *
 */
@Component
public class SnowflakeWorkerIdManagerBaseedDB {
	private static final int MAX_WORKER_ID = 1023;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GuidWorkerIdDao guidWorkerIdDao;

	private int assignedWorkerId = -1;

	/**
	 * 为当前应用实例（可以理解成JVM实例）分配Snowflake Worker ID。
	 * 
	 * @return
	 */
	public synchronized int assignWorkerIdForCurrentAppInstance() {
		if (this.assignedWorkerId >= 0) {
			return this.assignedWorkerId;
		}

		int assignWorkerId = -1;

		String machineAddress = AppNodeInfoHelper.getMachineAddress();
		String appBinaryPath = AppNodeInfoHelper.getAppBinaryPath();
		String machineId = MD5Utils.calcMD5(machineAddress);
		String appInstanceId = MD5Utils.calcMD5(appBinaryPath);

		logger.info("准备为当前应用节点设定或分配Snowflake Worker ID...");
		logger.info("当前机器节点标识：{}", machineAddress);
		logger.info("当前应用物理路径：{}", appBinaryPath);
		logger.info("计算得出的机器节点ID：{}", machineId);
		logger.info("计算得出的应用实例ID：{}", appInstanceId);

		GuidWorkerId existing = guidWorkerIdDao.selectByPrimaryKey(machineId, appInstanceId);
		if (null != existing) {
			logger.info("当前应用已经分配有Snowflake Worker Id ({})，此次应用启动只更新时间信息", existing.getWorkerId());
			assignWorkerId = existing.getWorkerId();

			// 如果该应用节点曾经分配过worker id，则更新时间信息即可。
			GuidWorkerId updating = new GuidWorkerId();
			updating.setMachineId(machineId);
			updating.setAppInstanceId(appInstanceId);
			updating.setAppLastStartedDate(new Date());
			guidWorkerIdDao.updateByPrimaryKeySelective(updating);
		} else {
			Date now = new Date();
			GuidWorkerId newRec = new GuidWorkerId();
			newRec.setMachineId(machineId);
			newRec.setAppInstanceId(appInstanceId);
			if (machineAddress.length() > 256) {
				newRec.setMachineName(machineAddress.substring(0, 256));
			} else {
				newRec.setMachineName(machineAddress);
			}
			if (appBinaryPath.length() > 2048) {
				newRec.setAppBinaryPath(appBinaryPath.substring(0, 2048));
			} else {
				newRec.setAppBinaryPath(appBinaryPath);
			}
			newRec.setInitializedDate(now);
			newRec.setAppLastStartedDate(now);

			boolean duplicated = false;
			int retryCount = 0;
			SecureRandom random = new SecureRandom();
			do {
				assignWorkerId = tryAssignNewWorkerId(newRec);
				logger.info("当前应用节点被分配到的Snowflake worker id是: {}", assignWorkerId);
				int duplicateCnt = guidWorkerIdDao.countWithSameWorkerId(assignWorkerId);
				if (duplicateCnt > 1) {
					guidWorkerIdDao.deleteByPrimaryKey(machineId, appInstanceId);
					duplicated = true;
					assignWorkerId = -1;
					retryCount++;
					try {
						long sleepMs = random.nextInt(5000) + 1000;
						logger.info("此次尝试分配的Snowflake worker id ({})与其他应用节点的worker id重复，{} ms后将会进行第{}次重新分配...",
								assignWorkerId, sleepMs, retryCount);
						Thread.sleep(sleepMs);
					} catch (InterruptedException e) {
					}
				} else {
					duplicated = false;
				}
			} while (duplicated && retryCount < 30);
		}

		logger.info("设定或分配完毕，当前应用节点的Snowflake Worker ID为: {}", assignWorkerId);
		this.assignedWorkerId = assignWorkerId;
		return assignWorkerId;
	}

	private int tryAssignNewWorkerId(GuidWorkerId newRec) {
		int assignWorkerId;

		String machineId = newRec.getMachineId();
		String appInstanceId = newRec.getAppInstanceId();

		guidWorkerIdDao.insertBaseCurrentMaxWorkerId(newRec);

		GuidWorkerId checkResult = guidWorkerIdDao.selectByPrimaryKey(machineId, appInstanceId);
		if (checkResult.getWorkerId() <= MAX_WORKER_ID) {
			assignWorkerId = checkResult.getWorkerId();
		} else {
			guidWorkerIdDao.deleteByPrimaryKey(machineId, appInstanceId);

			List<Integer> usedWorkerIds = guidWorkerIdDao.selectAllWorkerIds();
			Collections.sort(usedWorkerIds, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				}
			});
			assignWorkerId = usedWorkerIds.get(usedWorkerIds.size() - 1) + 1;
			if (assignWorkerId > MAX_WORKER_ID) {
				assignWorkerId = -1;
				Set<Integer> usedWorkerIdsSet = new HashSet<Integer>(usedWorkerIds);
				for (int id = 0; id <= MAX_WORKER_ID; id++) {
					if (!usedWorkerIdsSet.contains(id)) {
						assignWorkerId = id;
						break;
					}
				}
			}
		}

		if (assignWorkerId < 0) {
			throw new RuntimeException("实际的应用节点数已超出Snowflake ID算法的最大限制（" + (MAX_WORKER_ID + 1)
					+ "个），无法为该当前应用节点分配Snowflake Worker ID，请人工检查CIFPAY_GUID_WORKER_ID表是否存在可以手工删除的worker id记录。");
		}
		return assignWorkerId;
	}

}
