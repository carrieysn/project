package com.cifpay.lc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * 工具类，提供扫描类文件的功能。
 * 
 * 
 *
 */
public abstract class ClassScanUtils {
	private static final String RESOURCE_PATTERN = "/**/*.class";

	/**
	 * 在指定的包路径范围内，扫描出所有标记了指定的注解的Class，使用默认的ClassLoader。
	 * 
	 * @param annotationClzName
	 *            需要扫描的注解名称（含包路径）
	 * @param includeInterfaces
	 *            扫描结果是否包含接口类
	 * @param includeAbstractClasses
	 *            扫描结果是否包含抽象类
	 * @param packagesToScan
	 *            扫描的包路径范围
	 * @return
	 */
	public static Class<?>[] scanClassesWithAnnotation(String annotationClzName, boolean includeInterfaces,
			boolean includeAbstractClasses, String... packagesToScan) {
		return scanClassesWithAnnotation(annotationClzName, includeInterfaces, includeAbstractClasses, null,
				packagesToScan);
	}

	/**
	 * 在指定的包路径范围内，扫描出所有标记了指定的注解的Class。
	 * 
	 * @param annotationClzName
	 *            需要扫描的注解名称（含包路径）
	 * @param includeInterfaces
	 *            扫描结果是否包含接口类
	 * @param includeAbstractClasses
	 *            扫描结果是否包含抽象类
	 * @param classLoader
	 *            指定ClassLoader
	 * @param packagesToScan
	 *            扫描的包路径范围
	 * @return
	 */
	public static Class<?>[] scanClassesWithAnnotation(String annotationClzName, boolean includeInterfaces,
			boolean includeAbstractClasses, ClassLoader classLoader, String... packagesToScan) {
		Assert.notNull(annotationClzName, "参数annotationClzName不应为null");
		Assert.notNull(packagesToScan, "参数packagesToScan不应为null");

		List<Class<?>> foundClasses = new ArrayList<Class<?>>();
		try {
			if (null == classLoader) {
				classLoader = Thread.currentThread().getContextClassLoader();
				if (null == classLoader) {
					classLoader = ClassScanUtils.class.getClassLoader();
				}
			}
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(classLoader);

			for (String packageToScan : packagesToScan) {
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(packageToScan) + RESOURCE_PATTERN;
				Resource[] resources = resourcePatternResolver.getResources(pattern);
				MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
				for (Resource resource : resources) {
					MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
					if (metadataReader.getAnnotationMetadata().isAnnotated(annotationClzName)) {
						String className = metadataReader.getClassMetadata().getClassName();
						Class<?> annotatedClass = resourcePatternResolver.getClassLoader().loadClass(className);
						foundClasses.add(annotatedClass);
					}
				}
			}
		} catch (IOException ex) {
			throw new RuntimeException("Failed to scan classpath for unlisted classes", ex);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Failed to load annotated classes from classpath", ex);
		}

		return foundClasses.toArray(new Class<?>[foundClasses.size()]);
	}

	/**
	 * 在指定的包路径范围内，扫描出所有实现了指定接口的类，扫描结果为具体实现或抽象类，不包括子接口。
	 * 
	 * @param interfaceClz
	 *            需要扫描的接口类
	 * @param includeAbstractClasses
	 *            扫描结果是否包括抽象类
	 * @param packagesToScan
	 *            扫描的包路径范围
	 * @return
	 */
	public static Class<?>[] scanClassesWithInterface(Class<?> interfaceClz, boolean includeAbstractClasses,
			String... packagesToScan) {
		return scanClassesWithInterface(interfaceClz, includeAbstractClasses, null, packagesToScan);
	}

	/**
	 * 在指定的包路径范围内，扫描出所有实现了指定接口的类，扫描结果为具体实现或抽象类，不包括子接口。
	 * 
	 * @param interfaceClz
	 *            需要扫描的接口类
	 * @param includeAbstractClasses
	 *            扫描结果是否包括抽象类
	 * @param classLoader
	 *            指定ClassLoader
	 * @param packagesToScan
	 *            扫描的包路径范围
	 * @return
	 */
	public static Class<?>[] scanClassesWithInterface(Class<?> interfaceClz, boolean includeAbstractClasses,
			ClassLoader classLoader, String... packagesToScan) {
		Assert.notNull(interfaceClz, "参数interfaceClz不应为null");
		Assert.notNull(packagesToScan, "参数packagesToScan不应为null");

		List<Class<?>> foundClasses = new ArrayList<Class<?>>();
		try {
			if (null == classLoader) {
				classLoader = Thread.currentThread().getContextClassLoader();
				if (null == classLoader) {
					classLoader = ClassScanUtils.class.getClassLoader();
				}
			}
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(classLoader);

			for (String packageToScan : packagesToScan) {
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(packageToScan) + RESOURCE_PATTERN;
				Resource[] resources = resourcePatternResolver.getResources(pattern);
				MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
				for (Resource resource : resources) {
					MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
					ClassMetadata classMetadata = metadataReader.getClassMetadata();
					if (!classMetadata.isInterface() && (includeAbstractClasses || !classMetadata.isAbstract())) {
						String className = metadataReader.getClassMetadata().getClassName();
						Class<?> annotatedClass = resourcePatternResolver.getClassLoader().loadClass(className);
						if (interfaceClz.isAssignableFrom(annotatedClass)) {
							foundClasses.add(annotatedClass);
						}
					}
				}
			}
		} catch (IOException ex) {
			throw new RuntimeException("Failed to scan classpath for unlisted classes", ex);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Failed to load annotated classes from classpath", ex);
		}

		return foundClasses.toArray(new Class<?>[foundClasses.size()]);
	}

	/**
	 * 在指定的包路径范围内，扫描出某指定父类的所有子类，使用默认的ClassLoader。
	 * 
	 * @param interfaceClz
	 *            需要扫描的父类
	 * @param includeAbstractClasses
	 *            扫描结果是否包括抽象类
	 * @param classLoader
	 *            指定ClassLoader
	 * @param packagesToScan
	 *            扫描的包路径范围
	 * @return
	 */
	public static Class<?>[] scanClassesWithParentClass(Class<?> parentClz, boolean includeAbstractClasses,
			String... packagesToScan) {
		return scanClassesWithInterface(parentClz, includeAbstractClasses, packagesToScan);
	}

	/**
	 * 在指定的包路径范围内，扫描出某指定父类的所有子类。
	 * 
	 * @param interfaceClz
	 *            需要扫描的父类
	 * @param includeAbstractClasses
	 *            扫描结果是否包括抽象类
	 * @param classLoader
	 *            指定ClassLoader
	 * @param packagesToScan
	 *            扫描的包路径范围
	 * @return
	 */
	public static Class<?>[] scanClassesWithParentClass(Class<?> parentClz, boolean includeAbstractClasses,
			ClassLoader classLoader, String... packagesToScan) {
		return scanClassesWithInterface(parentClz, includeAbstractClasses, classLoader, packagesToScan);
	}

}
