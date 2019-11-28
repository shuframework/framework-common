package com.shuframework.commonbase.enums;

import java.math.BigDecimal;

/**
 * 保留小数 类型的枚举
 * java.math.RoundingMode
 * 
 * @author shuheng
 *
 */
public enum RoundTypeEnum {

	// 0.50 保留1位是ROUND_HALF_UP是1，ROUND_HALF_DOWN是0
	ROUND_HALF_UP(BigDecimal.ROUND_HALF_UP, "正常四舍五入,若舍弃部分首位大于5"),
	ROUND_HALF_DOWN(BigDecimal.ROUND_HALF_DOWN, "四舍五入,若舍弃部分>.5进位"),

	// ceil 天花板; floor 地板;
	ROUND_CEILING(BigDecimal.ROUND_CEILING, "向上取整"),
//	ROUND_UP(BigDecimal.ROUND_UP, "向上取整,不关心符号(负数时有问题)"),
	ROUND_FLOOR(BigDecimal.ROUND_FLOOR, "向下取整"),
//	ROUND_DOWN(BigDecimal.ROUND_DOWN, "向下取整,不关心符号(负数时有问题)"),

	ROUND_HALF_EVEN(BigDecimal.ROUND_HALF_EVEN, "奇数是ROUND_HALF_UP, 偶数是ROUND_HALF_DOWN")
	;

	private int code;
	private String msg;

	RoundTypeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
