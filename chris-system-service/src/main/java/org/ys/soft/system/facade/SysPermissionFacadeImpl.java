package org.ys.soft.system.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.ys.soft.framework.base.BaseConstants;
import org.ys.soft.framework.base.Where;
import org.ys.soft.framework.base.enums.system.Menu;
import org.ys.soft.system.model.SysPermission;
import org.ys.soft.system.service.SysPermissionService;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;

/**
 * [系统权限dubbo服务接口实现]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service(version = "1.0.0")
public class SysPermissionFacadeImpl implements SysPermissionFacade {

	@Autowired
	private SysPermissionService sysPermissionService;

	@Override
	public void saveInitPermissionData(List<SysPermission> permissionList) {
		sysPermissionService.saveInitPermissionData(permissionList);
	}

	@Override
	public SysPermission retrieveByPk(SysPermission param) {
		return sysPermissionService.retrieveByPk(param);
	}

	@Override
	public void updateByPk(SysPermission model) {
		sysPermissionService.updateByPk(model);
	}

	@Override
	public List<SysPermission> retrieveList(SysPermission queryModel) {
		return sysPermissionService.retrieveList(queryModel);
	}

	@Override
	public PageInfo<SysPermission> retrievePageListWhere(Where where, Integer pageNum, Integer pageSize) {
		return sysPermissionService.retrievePageListWhere(where, pageNum, pageSize);
	}

	@Override
	public PageInfo<SysPermission> retrievePageList(SysPermission queryModel, Integer pageNum, Integer pageSize) {
		return sysPermissionService.retrievePageList(queryModel, pageNum, pageSize);
	}

	@Override
	public boolean hasPermission(Long userId, String permissionCode) {
		return sysPermissionService.getUserPermissionCount(userId, permissionCode) > 0;
	}

	@Override
	public List<String> getAccessMenuCodeList(Long userId) {
		// 超级管理员返回系统中定义的所有菜单
		if (userId.equals(BaseConstants.SUPERADMIN_ID)) {
			List<String> menuCodeList = new ArrayList<String>();
			for (Menu menu : Menu.values()) {
				if (!menu.getCode().equalsIgnoreCase(BaseConstants.NULL)) {
					menuCodeList.add(menu.getCode());
				}
			}
			return menuCodeList;
		}
		// 其他用户根据配置的权限获取菜单
		return sysPermissionService.getAccessMenuCodeList(userId);
	}

	@Override
	public List<SysPermission> getAccessMenuPermissionList(Long roleId) {
		return sysPermissionService.getAccessMenuPermissionList(roleId);
	}

}
