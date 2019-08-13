package com.shuframework.commonbiz.enums;

/**
 * 菜单类型枚举
 */
public enum ProfileTypeEnum {

	LOCAL("local", "本地环境"), // 本地环境可以省略
	DEV("dev", "开发环境"),
	TEST("test", "测试环境"),
	// 预发布的库，一般与正式环境相同，或者数据和结构都是一样; 也可用来做压测
	STAGE("stage", "预发布环境"),
	PROD("prod", "正式环境");

	private String code;
	private String name;

	ProfileTypeEnum(String code, String name) {
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
