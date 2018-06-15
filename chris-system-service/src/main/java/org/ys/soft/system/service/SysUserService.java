package org.ys.soft.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ys.soft.framework.base.Where;
import org.ys.soft.framework.base.WhereFactory;
import org.ys.soft.framework.base.model.system.SysUser;
import org.ys.soft.framework.base.utils.database.DatabaseUtil;
import org.ys.soft.framework.base.utils.id.IdUtil;
import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.framework.service.BaseService;
import org.ys.soft.system.dao.SysUserDao;
import org.ys.soft.system.dao.SysUserRoleDao;
import org.ys.soft.system.model.SysUserRole;

/**
 * [用户服务类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service
public class SysUserService extends BaseService<SysUser> {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	protected BaseDao<SysUser> getBaseDao() {
		return sysUserDao;
	}

	/**
	 * [批量删除用户及用户与角色的关联关系]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void deleteBatch(String ids) {
		// 删除用户
		String tempIds = DatabaseUtil.wrapSingleQuotes(ids);
		Where where = WhereFactory.createInstance(SysUser.class);
		where.field("id").in(tempIds);
		sysUserDao.deleteBatchWhere(where);
		// 删除用户和角色的关联关系
		Where dWhere = WhereFactory.createInstance(SysUserRole.class);
		dWhere.field("userId").in(tempIds);
		sysUserRoleDao.deleteBatchWhere(dWhere);
	}

	/**
	 * [保存用户与角色的关联关系]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void saveRoleConfig(Long userId, String roleIds) {
		// 通过角色id,删除该用户和角色的所有关联关系
		SysUserRole model = new SysUserRole();
		model.setUserId(userId);
		sysUserRoleDao.deleteBatch(model);

		// 依次添加配置的用户和角色关联关系
		for (String roleId : roleIds.split(",")) {
			model.setId(IdUtil.nextId());
			model.setRoleId(Long.valueOf(roleId));
			sysUserRoleDao.create(model);
		}
	}

}
