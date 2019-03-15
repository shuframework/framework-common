package com.shuframework.commoncore.enums;


/**
 * 归属类型
 *
 * @author shuheng
 */
public enum BelongTypeEnum {

    SELF("1", "自营"),
    JOIN("2", "加盟"),
    RENT("3", "租赁");

    private String code;
    private String name;

    BelongTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return this.name;
    }

}
