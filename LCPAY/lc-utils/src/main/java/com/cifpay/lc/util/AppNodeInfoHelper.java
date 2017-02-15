package com.cifpay.lc.util;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.util.Enumeration;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.util.security.MD5Utils;

/**
 * 应用节点信息获取工具类，用于获取应用实例所在服务器的IP或机器名、应用实例对应的物理运行路径。
 * 
 * 
 *
 */
public abstract class AppNodeInfoHelper {
	private static Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
	private static Logger logger = LoggerFactory.getLogger(AppNodeInfoHelper.class);

	private static String machineAddress;
	private static String appBinaryPath;

	public static String getMachineAddress() {
		if (null == machineAddress) {
			String hostAddr = null;
			try {
				String fallbackIp = null;
				String preferIp = null;
				String fallbackMacAddress = null;
				String preferMacAddress = null;
				Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
				for (; nis.hasMoreElements();) {
					NetworkInterface ni = nis.nextElement();
					if (!ni.isLoopback()) {
						byte[] macAddrBytes = ni.getHardwareAddress();
						if (null != macAddrBytes) {
							fallbackMacAddress = new String(macAddrBytes);
						}
						Enumeration<InetAddress> a = ni.getInetAddresses();
						for (; a.hasMoreElements();) {
							InetAddress addr = a.nextElement();
							if (!addr.isLoopbackAddress()) {
								String ip = addr.getHostAddress();
								if (ipPattern.matcher(ip).matches() && !ip.startsWith("127.")
										&& !ip.startsWith("169.")) {
									// VirtualBox network interface
									if (ip.startsWith("192.168.56.")) {
										fallbackIp = ip;
									} else {
										preferIp = ip;
										macAddrBytes = ni.getHardwareAddress();
										if (null != macAddrBytes) {
											preferMacAddress = new String(macAddrBytes);
										}
									}
								}
							}
						}
					}
				}

				hostAddr = null != preferIp ? preferIp : fallbackIp;

				if (null == hostAddr) {
					hostAddr = InetAddress.getLocalHost().getHostName();
				}
				if ("127.0.0.1".equals(hostAddr)) {
					if (null != preferMacAddress) {
						hostAddr = preferMacAddress;
					} else if (null != fallbackMacAddress) {
						hostAddr = fallbackMacAddress;
					}
				}
			} catch (Exception e) {
				logger.warn("无法获取当前机器的IP或机器名称，参考错误信息：{}", e.getMessage());
			}

			if (null == hostAddr) {
				hostAddr = "127.0.0.1";
			}

			machineAddress = hostAddr;
		}
		return machineAddress;
	}

	public static String getAppBinaryPath() {
		if (null == appBinaryPath) {
			String path = "";
			try {
				URI tmpURI = AppNodeInfoHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI();
				File tmpFile = new File(tmpURI);
				if (tmpFile.isFile()) {
					path = tmpFile.getParent();
				} else {
					path = tmpURI.getPath();
				}
			} catch (Exception e) {
				logger.warn("无法获取当前Java应用的运行目录信息(#1)，参考错误信息：{}", e.getMessage());
			}

			if (null == path || 0 == path.trim().length() || "/".equals(path.trim())) {
				try {
					path = AppNodeInfoHelper.class.getResource("/").getFile();
				} catch (Exception e) {
					logger.warn("无法获取当前Java应用的运行目录信息(#2)，参考错误信息：{}", e.getMessage());
				}
			}

			if (null == path || 0 == path.trim().length() || "/".equals(path.trim())) {
				path = System.getProperty("user.dir");
			}

			appBinaryPath = path;

			logger.info("识别到当前应用的appBinaryPath为: {}", appBinaryPath);
		}
		return appBinaryPath;
	}

	/**
	 * 获取表示当前应用节点的一个全局（跨服务器）唯一ID，理论该ID不会因为应用的重启而发现改变。
	 * 
	 * @return
	 */
	public static String getAppNodeId() {
		String appPath = getAppBinaryPath();
		String host = getMachineAddress();
		return MD5Utils.calcMD5(appPath) + "@" + host;
	}

	/**
	 * 获取表示当前应用运行路径的一个标识，该标识只在同一个操作系统内唯，全局不保证唯一。理论上该ID不会因为应用的重启而发现改变。
	 * 
	 * @return
	 */
	public static String getLocalRuntimePathIdentifier() {
		String appPath = getAppBinaryPath();
		return MD5Utils.calcMD5(appPath);
	}

	public static String getAppNameOverriddenByJVM(String defaultAppName) {
		String key = "CIFPAYLC_APPNAME_" + defaultAppName;
		String overriddenAppName = System.getProperty(key);
		if (null == overriddenAppName || 0 == (overriddenAppName = overriddenAppName.trim()).length()) {
			overriddenAppName = System.getenv(key);
		}
		if (null != overriddenAppName && (overriddenAppName = overriddenAppName.trim()).length() > 0) {
			System.out.println("当前JVM实例为" + defaultAppName + "应用启用了自定义的APP NAME [" + overriddenAppName + "] 替代默认的 "
					+ defaultAppName + "。");
			logger.info("当前JVM实例为{}应用启用了自定义的APP NAME [{}] 替代默认的 {}。", defaultAppName, overriddenAppName,
					defaultAppName);
		} else {
			System.out.println("当前应用使用默认的APP NAME [" + defaultAppName + "]。");
			logger.info("当前应用使用默认的APP NAME [{}]。", defaultAppName);
			overriddenAppName = defaultAppName;
		}
		return overriddenAppName;
	}

}
