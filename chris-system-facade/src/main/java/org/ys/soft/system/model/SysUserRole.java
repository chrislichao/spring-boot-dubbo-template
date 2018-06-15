package org.ys.soft.system.model;

import org.ys.soft.framework.base.annotation.database.Column;
import org.ys.soft.framework.base.annotation.database.Id;
import org.ys.soft.framework.base.annotation.database.Table;
import org.ys.soft.framework.base.model.BaseEntity;

/**
 * [用户与角色关联对象]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Table
public class SysUserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	private Long id;
	/**
	 * 用户id
	 */
	@Column
	private Long userId;
	/**
	 * 角色id
	 */
	@Column
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
