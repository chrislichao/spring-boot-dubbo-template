package org.ys.soft.system.facade;

import java.util.List;

import org.ys.soft.framework.base.Where;
import org.ys.soft.system.model.SysRole;

import com.github.pagehelper.PageInfo;

/**
 * [角色dubbo服务接口]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public interface SysRoleFacade {
	/**
	 * [新增单个角色]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void create(SysRole model);

	/**
	 * [根据主键修改角色信息]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void updateByPk(SysRole queryModel);

	/**
	 * [通过主键查询角色]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public SysRole retrieveByPk(SysRole param);

	/**
	 * [获取单个角色对象]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public SysRole retrieveOne(SysRole queryModel);

	/**
	 * [查询角色信息,分页]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public PageInfo<SysRole> retrievePageList(SysRole queryModel, Integer pageNum, Integer pageSize);

	/**
	 * [自定义查询参数,查询角色信息,分页]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public PageInfo<SysRole> retrievePageListWhere(Where where, Integer pageNum, Integer pageSize);

	/**
	 * [批量删除]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void deleteBatch(String ids);

	/**
	 * [保存角色与权限的关联关系]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void savePermissionConfig(Long roleId, String permissionIds);

	/**
	 * [获取所有角色集合(若用户拥有该角色,checked字段为true)]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<SysRole> getAccessRoleList(Long userId);

}
