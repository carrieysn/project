package com.cifpay.lc.util.spring;

import java.lang.annotation.Annotation;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.BeanFactory;

/**
 * Java方法过滤器
 * 
 * 
 *
 */
public abstract class MethodAopFilter extends AbstractBeanFactoryAwareAdvisingPostProcessor implements MethodInterceptor {
	private static final long serialVersionUID = -609099077779468094L;

	public MethodAopFilter() {
		this.setProxyTargetClass(true);
	}

	@Override
	public final void setBeanFactory(BeanFactory beanFactory) {
		super.setBeanFactory(beanFactory);
		this.advisor = new MethodPointcutAdvisor(new AnnotationMatchingPointcut(getMatchedAnnotationOnClass(), getMatchedAnnotationOnMethod()), this);
	}

	protected static class MethodPointcutAdvisor extends AbstractPointcutAdvisor {
		private static final long serialVersionUID = -1586170865450508393L;
		private Pointcut pointcut;
		private MethodInterceptor methodInterceptor;

		public MethodPointcutAdvisor(Pointcut pointcut, MethodInterceptor methodInterceptor) {
			super();
			this.pointcut = pointcut;
			this.methodInterceptor = methodInterceptor;
		}

		@Override
		public Pointcut getPointcut() {
			return pointcut;
		}

		@Override
		public Advice getAdvice() {
			return methodInterceptor;
		}
	}

	/**
	 * 默认为null，匹配所有类
	 * 
	 * @return
	 */
	protected Class<? extends Annotation> getMatchedAnnotationOnClass() {
		return null;
	}

	/**
	 * 指定需要匹配的注解，只有标记该了该注解的方法，才会执行本过滤器的逻辑。
	 * 
	 * @return
	 */
	protected abstract Class<? extends Annotation> getMatchedAnnotationOnMethod();

	@Override
	public abstract Object invoke(MethodInvocation invocation) throws Throwable;

}
