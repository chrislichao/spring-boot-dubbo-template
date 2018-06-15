package org.ys.soft.web.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.ys.soft.framework.base.BaseConstants;
import org.ys.soft.framework.base.annotation.ValidatePermission;
import org.ys.soft.framework.base.exception.business.NotLoggedException;
import org.ys.soft.framework.base.exception.business.PermissionDeniedException;
import org.ys.soft.framework.base.model.system.SysUser;
import org.ys.soft.web.aop.base.BaseAspect;
import org.ys.soft.web.service.SystemService;

/**
 * [权限验证切面]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Aspect
@Component
public class PermissionAspect extends BaseAspect {

	@Autowired
	private SystemService systemService;

	/**
	 * [切入点]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Pointcut("@annotation(org.ys.soft.framework.base.annotation.ValidatePermission) and @annotation(org.springframework.web.bind.annotation.RequestMapping) and execution(public * org.ys.soft..controller..*(..))")
	public void permissionAspect() {

	}

	/**
	 * [源方法执行前执行的方法,完成用户在线验证和权限验证]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Before("permissionAspect()")
	public void doBefore(JoinPoint joinPoint) {
		Method sourceMethod = getSourceMethod(joinPoint);
		if (sourceMethod != null) {
			String methodFullName = String.format("%s.%s", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());

			ValidatePermission validate = sourceMethod.getAnnotation(ValidatePermission.class);
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			SysUser user = (SysUser) request.getSession().getAttribute(BaseConstants.SESSION_KEY_USER);
			// 验证用户是否登录
			if (user == null) {
				throw new NotLoggedException(String.format("User not logged when execute method[%s].", methodFullName));
			}
			// 如果当前用户是超级管理员,直接有权限
			if (user.getId().equals(BaseConstants.SUPERADMIN_ID)) {
				return;
			}
			// 否则,需要验证权限
			if (validate.effective()) {
				// 验证用户是否有访问权限
				String permissionCode = String.format("%s-%s", validate.menu().getCode(), validate.action().getCode());
				if (!systemService.hasPermission(user.getId(), permissionCode)) {
					throw new PermissionDeniedException(String.format("Permission denied when user[%s] execute method[%s].", user.getUsername(),
							methodFullName));
				}
			}
		}
	}
}
