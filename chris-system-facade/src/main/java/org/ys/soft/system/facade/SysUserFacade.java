package org.ys.soft.system.facade;

import org.ys.soft.framework.base.Where;
import org.ys.soft.framework.base.model.system.SysUser;

import com.github.pagehelper.PageInfo;

/**
 * [用户dubbo服务接口]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public interface SysUserFacade {
	/**
	 * [新增单个用户]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void create(SysUser model);

	/**
	 * [根据主键修改用户信息]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void updateByPk(SysUser queryModel);

	/**
	 * [通过主键查询用户]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public SysUser retrieveByPk(SysUser param);

	/**
	 * [获取单个用户对象]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public SysUser retrieveOne(SysUser queryModel);

	/**
	 * [查询用户信息,分页]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public PageInfo<SysUser> retrievePageList(SysUser queryModel, Integer pageNum, Integer pageSize);

	/**
	 * [自定义查询参数,查询用户信息,分页]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public PageInfo<SysUser> retrievePageListWhere(Where where, Integer pageNum, Integer pageSize);

	/**
	 * [批量删除]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void deleteBatch(String ids);

	/**
	 * [保存角色配置]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void saveRoleConfig(Long userId, String roleIds);

}
