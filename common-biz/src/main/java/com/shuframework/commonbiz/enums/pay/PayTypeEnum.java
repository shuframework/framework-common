package com.shuframework.commonbiz.enums.pay;


/**
 * 付款方式
 *
 * @author shuheng
 */
public enum PayTypeEnum {

    APP("1", "APP支付"),
    WAP("2", "H5支付"),
    SCAN("3", "扫码支付"),
    FREE("4", "免密支付")
    ;

    private String code;
    private String name;

    PayTypeEnum(String code, String name) {
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
