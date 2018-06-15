package org.ys.soft.system.model;

import org.apache.commons.lang.StringUtils;
import org.ys.soft.framework.base.annotation.database.Column;
import org.ys.soft.framework.base.annotation.database.LeftJoin;
import org.ys.soft.framework.base.annotation.database.Table;
import org.ys.soft.framework.base.enums.database.Group;
import org.ys.soft.framework.base.model.CommonEntity;
import org.ys.soft.framework.base.model.system.SysUser;

/**
 * [权限]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Table
public class SysPermission extends CommonEntity {
	/**
	 * 权限ToString的格式
	 */
	private final String TO_STR_FORMAT = "{Permission [code = %s][name = %s][url = %s]}";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 所属菜单编码
	 */
	@Column
	private String menuCode;
	/**
	 * 权限编码
	 */
	@Column
	private String code;
	/**
	 * 权限名称
	 */
	@Column
	private String name;
	/**
	 * 权限对应的访问url
	 */
	@Column
	private String url;
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
	 * 是否选中(给角色配置权限时用到)
	 */
	private Integer checked;

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return String.format(TO_STR_FORMAT, code, name, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (StringUtils.isBlank(code)) {
			return false;
		}
		SysPermission another = (SysPermission) obj;
		return code.equals(another.getCode());
	}
}
