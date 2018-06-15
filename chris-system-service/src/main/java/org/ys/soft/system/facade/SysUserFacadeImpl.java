package org.ys.soft.system.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.ys.soft.framework.base.Where;
import org.ys.soft.framework.base.model.system.SysUser;
import org.ys.soft.system.service.SysUserService;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;

/**
 * [系统用户dubbo服务接口实现]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service(version = "1.0.0")
public class SysUserFacadeImpl implements SysUserFacade {

	@Autowired
	public SysUserService sysUserService;

	@Override
	public void create(SysUser model) {
		sysUserService.create(model);
	}

	@Override
	public SysUser retrieveByPk(SysUser queryModel) {
		return sysUserService.retrieveByPk(queryModel);
	}

	@Override
	public SysUser retrieveOne(SysUser queryModel) {
		return sysUserService.retrieveOne(queryModel);
	}

	@Override
	public void updateByPk(SysUser queryModel) {
		sysUserService.updateByPk(queryModel);
	}

	@Override
	public PageInfo<SysUser> retrievePageList(SysUser queryModel, Integer pageNum, Integer pageSize) {
		return sysUserService.retrievePageList(queryModel, pageNum, pageSize);
	}

	@Override
	public PageInfo<SysUser> retrievePageListWhere(Where where, Integer pageNum, Integer pageSize) {
		return sysUserService.retrievePageListWhere(where, pageNum, pageSize);
	}

	@Override
	public void deleteBatch(String ids) {
		sysUserService.deleteBatch(ids);
	}

	@Override
	public void saveRoleConfig(Long userId, String roleIds) {
		sysUserService.saveRoleConfig(userId, roleIds);
	}

}
