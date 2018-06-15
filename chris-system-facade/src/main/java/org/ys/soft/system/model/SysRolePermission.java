package org.ys.soft.system.model;

import org.ys.soft.framework.base.annotation.database.Column;
import org.ys.soft.framework.base.annotation.database.Id;
import org.ys.soft.framework.base.annotation.database.Table;
import org.ys.soft.framework.base.model.BaseEntity;

/**
 * [角色与权限关联对象]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Table
public class SysRolePermission extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private Long id;
	/**
	 * 角色id
	 */
	@Column
	private Long roleId;
	/**
	 * 权限id
	 */
	@Column
	private Long permissionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

}
