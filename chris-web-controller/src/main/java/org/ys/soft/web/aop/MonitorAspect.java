package org.ys.soft.web.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.ys.soft.common.model.LogMonitor;
import org.ys.soft.framework.base.BaseConstants;
import org.ys.soft.framework.base.annotation.Monitor;
import org.ys.soft.framework.base.exception.FrameworkException;
import org.ys.soft.framework.base.model.system.SysUser;
import org.ys.soft.framework.base.utils.date.DateUtil;
import org.ys.soft.framework.base.utils.id.IdUtil;
import org.ys.soft.framework.base.utils.json.JsonUtil;
import org.ys.soft.framework.base.utils.web.RequestUtil;
import org.ys.soft.web.aop.base.BaseAspect;
import org.ys.soft.web.service.CommonService;

/**
 * [方法监控切面]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Aspect
@Component
public class MonitorAspect extends BaseAspect {
	private Logger logger = LoggerFactory.getLogger(MonitorAspect.class);

	private final String logFormat = "Executed in {%d msec} when invoke method [%s].";

	private final String errorMsgFormat = "Exception[%s] ---> %s.%s(*)-[Line][%d].";

	@Autowired
	public CommonService commonService;

	/**
	 * [切入点]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Pointcut("@annotation(org.ys.soft.framework.base.annotation.Monitor) and @annotation(org.springframework.web.bind.annotation.RequestMapping) and execution(public * org.ys.soft..controller..*(..))")
	public void monitorAspect() {

	}

	/**
	 * [环绕处理逻辑,记录方法执行所需时间,记录日志]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Around("monitorAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) {
		Object result = null;
		Method sourceMethod = getSourceMethod(joinPoint);
		if (sourceMethod != null) {
			String methodFullName = String.format("%s.%s", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
			Monitor monitor = sourceMethod.getAnnotation(Monitor.class);
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			SysUser user = (SysUser) request.getSession().getAttribute(BaseConstants.SESSION_KEY_USER);
			// 开始时间
			Long start = System.currentTimeMillis();

			LogMonitor log = new LogMonitor();
			log.setId(IdUtil.nextId());
			log.setMethod(methodFullName);
			log.setParams(getParamsValue(joinPoint.getArgs()));
			log.setInvokeTime(DateUtil.getCurrentSeconds());
			log.setInvokeBy(user != null ? user.getId() : null);
			log.setInvokeIp(RequestUtil.getIpAddress(request));
			log.setDescription(monitor.description());
			try {
				result = joinPoint.proceed();
				// 调用成功
				log.setStatus(1);
				log.setTimeConsuming(System.currentTimeMillis() - start);
				log.setReturnedValue(JsonUtil.beanToJson(result));

				if (monitor.storeInDatabase()) {
					logger.info(String.format(logFormat, log.getTimeConsuming(), methodFullName));
					commonService.saveLog(log);
				}
			} catch (Throwable e) {
				// 调用出现异常
				StackTraceElement element = e.getStackTrace()[0];

				log.setStatus(-1);
				log.setErrorMsg(e.getLocalizedMessage());
				log.setErrorClass(element.getClassName());
				log.setErrorMethod(element.getMethodName());
				log.setErrorLine(element.getLineNumber());
				log.setTimeConsuming(System.currentTimeMillis() - start);

				if (monitor.storeInDatabase()) {
					String errorMsg = String.format(errorMsgFormat, e.getLocalizedMessage(), element.getClassName(), element.getMethodName(), element
							.getLineNumber());
					logger.error(errorMsg);
					commonService.saveLog(log);
				}
				throw new FrameworkException(e.getLocalizedMessage());
			}
		}
		return result;
	}

	/**
	 * [获取参数值]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	private String getParamsValue(Object[] args) {
		StringBuffer paramBuffer = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			paramBuffer.append("{param[").append(i + 1).append("] = ").append("[");
			if (args[i] instanceof HttpServletRequest) {
				paramBuffer.append(args[i]);
			} else {
				paramBuffer.append(JsonUtil.beanToJson(args[i]));
			}
			paramBuffer.append("]}");
		}
		return paramBuffer.toString();
	}
}
