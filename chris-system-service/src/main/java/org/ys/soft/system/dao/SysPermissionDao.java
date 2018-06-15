package org.ys.soft.system.dao;

import java.util.List;
import java.util.Map;

import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.system.model.SysPermission;
import org.ys.soft.system.model.SysRole;

/**
 * [权限dao]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see SysPermission
 * @see SysRole
 */
public interface SysPermissionDao extends BaseDao<SysPermission> {

	/**
	 * [获取用户和权限的绑定记录个数]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	int getUserPermissionCount(Map<String, Object> paramMap);

	/**
	 * [获取用户可使用的菜单编码集合]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	List<String> getAccessMenuCodeList(Long userId);

	/**
	 * [获取可使用菜单下所有权限集合(若角色拥有该权限,checked字段为true)]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	List<SysPermission> getAccessMenuPermissionList(Long roleId);
}
