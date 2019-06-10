package com.shuframework.commonbiz.enums;

/**
 * 菜单类型枚举
 */
public enum SysMenuTypeEnum {

	MODULE("1", "模块"),
	MENU("2", "菜单"),
	BUTTON("3", "按钮");

	private String code;
	private String name;

	SysMenuTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
