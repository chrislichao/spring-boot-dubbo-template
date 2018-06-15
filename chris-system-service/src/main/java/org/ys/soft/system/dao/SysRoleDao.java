package org.ys.soft.system.dao;

import java.util.List;

import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.system.model.SysPermission;
import org.ys.soft.system.model.SysRole;

/**
 * [角色dao]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see SysRole
 * @see SysPermission
 */
public interface SysRoleDao extends BaseDao<SysRole> {

	/**
	 * [获取所有角色集合(若用户拥有该角色,checked字段为true)]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	List<SysRole> getAccessRoleList(Long userId);
}
