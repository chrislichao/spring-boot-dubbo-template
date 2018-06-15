package org.ys.soft.web.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ys.soft.framework.base.annotation.RateLimit;
import org.ys.soft.framework.base.exception.FrameworkException;
import org.ys.soft.framework.base.utils.Assert;
import org.ys.soft.web.aop.base.BaseAspect;
import org.ys.soft.web.service.CommonService;

/**
 * [接口访问限流切面]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Aspect
@Component
public class RateLimitAspect extends BaseAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String SUCCESS_LOG_FORMAT = " ---{RateLimit}--- 本单位时间[%d秒]内,接口方法[%s]限定访问次数为:[%d],当前访问次数为[%d],允许访问.";

	private final String FAILED_LOG_FORMAT = " ---{RateLimit}--- 本单位时间[%d秒]内,接口方法[%s]限定访问次数为:[%d],当前访问次数为[%d],拒绝访问.";

	@Autowired
	public CommonService commonService;

	/**
	 * [切入点]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Pointcut("@annotation(org.ys.soft.framework.base.annotation.RateLimit) and @annotation(org.springframework.web.bind.annotation.RequestMapping) and execution(public * org.ys.soft..controller..*(..))")
	public void rateLimitAspect() {

	}

	/**
	 * [源方法执行前执行的方法,完成用户在线验证和权限验证]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Before("rateLimitAspect()")
	public void doBefore(JoinPoint joinPoint) {
		Method sourceMethod = getSourceMethod(joinPoint);
		if (sourceMethod != null) {
			String methodFullName = String.format("%s.%s", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());

			RateLimit limit = sourceMethod.getAnnotation(RateLimit.class);
			Assert.isTrue(limit.timeout() > 0, "接口访问限流周期必须设置为正整数!");
			Assert.isTrue(limit.count() > 0, "接口访问限定次数必须设置为正整数!");
			long visitCount = commonService.getVisitCount(limit.timeout(), methodFullName);
			if (visitCount > limit.count()) {
				logger.warn(String.format(FAILED_LOG_FORMAT, limit.timeout(), methodFullName, limit.count(), visitCount));
				throw new FrameworkException("亲,服务器正忙,请稍后再试!");
			}
			logger.debug(String.format(SUCCESS_LOG_FORMAT, limit.timeout(), methodFullName, limit.count(), visitCount));
		}
	}

}
