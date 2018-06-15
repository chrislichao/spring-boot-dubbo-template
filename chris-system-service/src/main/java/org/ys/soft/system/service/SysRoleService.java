package org.ys.soft.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ys.soft.framework.base.Where;
import org.ys.soft.framework.base.WhereFactory;
import org.ys.soft.framework.base.utils.database.DatabaseUtil;
import org.ys.soft.framework.base.utils.id.IdUtil;
import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.framework.service.BaseService;
import org.ys.soft.system.dao.SysRoleDao;
import org.ys.soft.system.dao.SysRolePermissionDao;
import org.ys.soft.system.model.SysRole;
import org.ys.soft.system.model.SysRolePermission;

/**
 * [角色服务类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service
public class SysRoleService extends BaseService<SysRole> {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysRolePermissionDao sysRolePermissionDao;

	@Override
	protected BaseDao<SysRole> getBaseDao() {
		return sysRoleDao;
	}

	/**
	 * [获取所有角色集合(若用户拥有该角色,checked字段为true)]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<SysRole> getAccessRoleList(Long userId) {
		return sysRoleDao.getAccessRoleList(userId);
	}

	/**
	 * [批量删除角色及相关的角色和权限的关联关系]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void deleteBatch(String ids) {
		// 删除角色
		String tempIds = DatabaseUtil.wrapSingleQuotes(ids);
		Where where = WhereFactory.createInstance(SysRole.class);
		where.field("id").in(tempIds);
		sysRoleDao.deleteBatchWhere(where);
		// 删除角色和权限的关联关系
		Where dWhere = WhereFactory.createInstance(SysRolePermission.class);
		dWhere.field("roleId").in(tempIds);
		sysRolePermissionDao.deleteBatchWhere(dWhere);
	}

	/**
	 * [保存角色与权限的关联关系]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void savePermissionConfig(Long roleId, String permissionIds) {
		// 通过角色id,删除该角色和权限的所有关联关系
		SysRolePermission model = new SysRolePermission();
		model.setRoleId(roleId);
		sysRolePermissionDao.deleteBatch(model);

		// 依次添加配置的角色和权限关联关系
		for (String permissionId : permissionIds.split(",")) {
			model.setId(IdUtil.nextId());
			model.setPermissionId(Long.valueOf(permissionId));
			sysRolePermissionDao.create(model);
		}
	}

}
