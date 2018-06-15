package org.ys.soft.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.framework.service.BaseService;
import org.ys.soft.system.dao.SysRolePermissionDao;
import org.ys.soft.system.model.SysRolePermission;

/**
 * [角色权限服务类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service
public class SysRolePermissionService extends BaseService<SysRolePermission> {
	@Autowired
	private SysRolePermissionDao sysRolePermissionDao;

	@Override
	protected BaseDao<SysRolePermission> getBaseDao() {
		return sysRolePermissionDao;
	}
}
