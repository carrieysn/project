package com.cifpay.lc.util.uid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tweeter Snowflake分布式ID生成算法的抽象封装，由子类具体去实现如何分配worker id. <br>
 * 更多信息请参考：https://github.com/twitter/snowflake<br>
 * 
 * 具体算法规则如下：<br>
 * (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 * 注意这里进行了小改动: snowkflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id <br>
 * (b) 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 * 
 * 
 */
public abstract class AbstractIdWorker {

	private final static Logger logger = LoggerFactory.getLogger(AbstractIdWorker.class);

	private final long epoch = 1460352689450L; // 时间起始标记点，作为基准，一般取系统的最近时间
	private final long workerIdBits = 10L; // 机器标识位数
	private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 机器ID，最大值:1023（10进制）
	private long workerId;
	private long sequence = 0L; // 0，并发控制
	private final long sequenceBits = 12L; // 毫秒内自增位

	private final long workerIdShift = this.sequenceBits; // 12
	private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits; // 4095,111111111111,12位
	private long lastTimestamp = -1L;

	// 标识是否已经成功初始过
	private boolean initialized = false;

	public final void checkAndInit() {
		if (!initialized) {
			synchronized (this) {
				long workerId = configureWorkerId();
				if (workerId > this.maxWorkerId || workerId < 0) {
					throw new IllegalStateException(
							String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
				}
				this.workerId = workerId;
				initialized = true;
			}
		}
	}

	/**
	 * 获取一个适合分布式环境使用的新ID值（long类型）。
	 * 
	 * @return
	 * @throws IllegalStateException
	 *             当总的应用节点超过1024个时，或操作系统时钟被外部条件调慢了，则会抛出IllegalStateException异常（
	 *             概率极低） 。
	 */
	public final synchronized long nextId() throws IllegalStateException {

		checkAndInit();

		long timestamp = timeGen();

		// 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环);
		// 对新的timestamp，sequence从0开始
		if (this.lastTimestamp == timestamp) {
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				// 重新生成timestamp
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds",
					(this.lastTimestamp - timestamp)));
			throw new IllegalStateException(
					String.format("clock moved backwards.Refusing to generate id for %d milliseconds",
							(this.lastTimestamp - timestamp)));
		}

		this.lastTimestamp = timestamp;
		return ((timestamp - this.epoch) << this.timestampLeftShift) | (this.workerId << this.workerIdShift)
				| this.sequence;
	}

	/**
	 * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 获得系统当前毫秒数
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}

	/**
	 * 提供workerId的获取逻辑，有效范围0 ~ 1023。分布式环境下，该workerId一般需要通过从全局共享的数据库节点获取，
	 * 以保证整个每一个JVM实例使用的workerId在整个分布式环境中是唯一的。
	 * 
	 * @return
	 */
	protected abstract long configureWorkerId();

}
