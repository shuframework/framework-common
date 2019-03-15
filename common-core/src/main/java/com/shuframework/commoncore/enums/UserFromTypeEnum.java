package com.shuframework.commoncore.enums;


/**
 * 用户来源类型
 *
 * @author shuheng
 */
public enum UserFromTypeEnum {

    APP_ANDROID("1", "android-app"),
    APP_IOS("2", "ios-app"),
    H5("3", "h5"),
    WEB("4", "网站/后台系统"),
    MINIPROGRAM_WEIXIN("5", "微信小程序"),
    MINIPROGRAM_ALI("6", "阿里小程序");

    private String code;
    private String name;

    UserFromTypeEnum(String code, String name) {
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
