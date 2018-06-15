package org.ys.soft.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ys.soft.framework.base.exception.business.NotLoggedException;
import org.ys.soft.framework.base.exception.business.PermissionDeniedException;

/**
 * [系统异常处理]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@ControllerAdvice
public class ExceptionHandlerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> exception(HttpServletRequest request, Exception e) {
		logger.error(e.getMessage(), e);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", "9999");
		returnMap.put("desc", "未知异常");
		return returnMap;
	}

	@ExceptionHandler(NotLoggedException.class)
	@ResponseBody
	public Map<String, Object> notLoggedException(HttpServletRequest request, Exception e) {
		logger.error(e.getMessage(), e);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", "0001");
		returnMap.put("desc", "用户未登录");
		return returnMap;
	}

	@ExceptionHandler(PermissionDeniedException.class)
	@ResponseBody
	public Map<String, Object> permissionDeniedException(HttpServletRequest request, Exception e) {
		logger.error(e.getMessage(), e);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", "0100");
		returnMap.put("desc", "用户未授权");
		return returnMap;
	}
}
