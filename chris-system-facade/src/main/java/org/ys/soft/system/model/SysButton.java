package org.ys.soft.system.model;

import org.ys.soft.framework.base.annotation.database.Column;
import org.ys.soft.framework.base.annotation.database.Table;
import org.ys.soft.framework.base.model.CommonEntity;

/**
 * [按钮]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Table
public class SysButton extends CommonEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 按钮名称
	 */
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
