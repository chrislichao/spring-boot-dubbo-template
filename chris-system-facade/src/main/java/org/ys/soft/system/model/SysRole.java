package org.ys.soft.system.model;

import org.ys.soft.framework.base.annotation.database.Column;
import org.ys.soft.framework.base.annotation.database.LeftJoin;
import org.ys.soft.framework.base.annotation.database.Table;
import org.ys.soft.framework.base.enums.database.Group;
import org.ys.soft.framework.base.model.CommonEntity;
import org.ys.soft.framework.base.model.system.SysUser;

/**
 * [角色]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Table
public class SysRole extends CommonEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 角色名称
	 */
	@Column
	private String name;
	/**
	 * 创建人名称
	 */
	@LeftJoin(refModel = SysUser.class, group = Group.ONE, selfOnField = "createBy", refValField = "nickname")
	private String createByName;
	/**
	 * 修改人名称
	 */
	@LeftJoin(refModel = SysUser.class, group = Group.TWO, selfOnField = "updateBy", refValField = "nickname")
	private String updateByName;
	/**
	 * 是否选中
	 */
	private Integer checked;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Integer getChecked() {
		return checked;
	}

}
