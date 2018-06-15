package org.ys.soft.system.facade;

import java.util.List;

import org.ys.soft.framework.base.Where;
import org.ys.soft.system.model.SysPermission;

import com.github.pagehelper.PageInfo;

/**
 * [权限dubbo服务接口]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public interface SysPermissionFacade {

	/**
	 * [保存初始化权限数据]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void saveInitPermissionData(List<SysPermission> permissionList);

	/**
	 * [通过主键获取权限对象]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public SysPermission retrieveByPk(SysPermission param);

	/**
	 * [通过主键修改权限对象,仅修改备注]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void updateByPk(SysPermission model);

	/**
	 * [获取满足条件的权限集合]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<SysPermission> retrieveList(SysPermission queryModel);

	/**
	 * [获取权限分页集合]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public PageInfo<SysPermission> retrievePageListWhere(Where where, Integer pageNum, Integer pageSize);

	/**
	 * [获取权限分页集合]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public PageInfo<SysPermission> retrievePageList(SysPermission queryModel, Integer pageNum, Integer pageSize);

	/**
	 * [检查用户{userId}是否有权限{permissionCode}]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public boolean hasPermission(Long userId, String permissionCode);

	/**
	 * [获取用户可使用的菜单编码集合]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<String> getAccessMenuCodeList(Long userId);

	/**
	 * [获取可使用菜单下所有权限集合(若角色拥有该权限,checked字段为true)]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<SysPermission> getAccessMenuPermissionList(Long roleId);

}
