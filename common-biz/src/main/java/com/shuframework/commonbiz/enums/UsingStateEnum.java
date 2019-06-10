package com.shuframework.commonbiz.enums;


/**
 * 使用状态
 *
 * @author shuheng
 */
public enum UsingStateEnum {

    NONE("0", "初始化"),
    TO_START("1", "待生效/未开始"),
    STARTED("2", "已生效/使用中"),
    TO_END("3", "即将到期/即将结束"),
    END("4", "已到期/已结束"),
    CANCEL("5", "取消");

    private String code;
    private String name;

    UsingStateEnum(String code, String name) {
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
