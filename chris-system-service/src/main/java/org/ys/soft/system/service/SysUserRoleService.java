package org.ys.soft.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.framework.service.BaseService;
import org.ys.soft.system.dao.SysUserRoleDao;
import org.ys.soft.system.model.SysUserRole;

/**
 * [用户角色服务类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service
public class SysUserRoleService extends BaseService<SysUserRole> {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	protected BaseDao<SysUserRole> getBaseDao() {
		return sysUserRoleDao;
	}

}
