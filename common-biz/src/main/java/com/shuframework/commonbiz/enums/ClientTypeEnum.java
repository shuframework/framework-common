package com.shuframework.commonbiz.enums;


/**
 * 客户端类型
 *
 * @author shuheng
 */
public enum ClientTypeEnum {

    ANDROID("1", "android"),
    IOS("2", "ios"),
    H5("3", "h5");

    private String code;
    private String name;

    ClientTypeEnum(String code, String name) {
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
