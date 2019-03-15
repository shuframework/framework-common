package com.shuframework.commoncore.enums.pay;


/**
 * 支付状态
 *
 * @author shuheng
 */
public enum PayStateEnum {

    UN_PAID("0", "待支付"),
    SUCCESS("1", "支付成功"),
    CANCEL("2", "取消"),
    FAIL("9", "异常失败/支付失败");

    private String code;
    private String name;

    PayStateEnum(String code, String name) {
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
