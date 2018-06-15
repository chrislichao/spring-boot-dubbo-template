package org.ys.soft.web.service;

import org.springframework.stereotype.Component;
import org.ys.soft.system.facade.SysPermissionFacade;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * [系统服务集合]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Component
public class SystemService {
	
	@Reference(version = "1.0.0")
	private SysPermissionFacade sysPermissionFacade;

	/**
	 * [检查用户{userId}是否有权限{permissionCode}]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public boolean hasPermission(Long userId, String permissionCode) {
		return sysPermissionFacade.hasPermission(userId, permissionCode);
	}
}
