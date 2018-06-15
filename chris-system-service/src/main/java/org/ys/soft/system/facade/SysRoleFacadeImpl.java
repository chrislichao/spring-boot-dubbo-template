package org.ys.soft.system.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.ys.soft.framework.base.Where;
import org.ys.soft.system.model.SysRole;
import org.ys.soft.system.service.SysRoleService;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;

/**
 * [系统角色dubbo服务接口实现]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service(version = "1.0.0")
public class SysRoleFacadeImpl implements SysRoleFacade {

	@Autowired
	public SysRoleService sysRoleService;

	@Override
	public void create(SysRole model) {
		sysRoleService.create(model);
	}

	@Override
	public SysRole retrieveByPk(SysRole queryModel) {
		return sysRoleService.retrieveByPk(queryModel);
	}

	@Override
	public SysRole retrieveOne(SysRole queryModel) {
		return sysRoleService.retrieveOne(queryModel);
	}

	@Override
	public void updateByPk(SysRole queryModel) {
		sysRoleService.updateByPk(queryModel);
	}

	@Override
	public PageInfo<SysRole> retrievePageList(SysRole queryModel, Integer pageNum, Integer pageSize) {
		return sysRoleService.retrievePageList(queryModel, pageNum, pageSize);
	}

	@Override
	public PageInfo<SysRole> retrievePageListWhere(Where where, Integer pageNum, Integer pageSize) {
		return sysRoleService.retrievePageListWhere(where, pageNum, pageSize);
	}

	@Override
	public void deleteBatch(String ids) {
		sysRoleService.deleteBatch(ids);
	}

	@Override
	public void savePermissionConfig(Long roleId, String permissionIds) {
		sysRoleService.savePermissionConfig(roleId, permissionIds);
	}

	@Override
	public List<SysRole> getAccessRoleList(Long userId) {
		return sysRoleService.getAccessRoleList(userId);
	}
}
