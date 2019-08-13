package com.shuframework.commonbiz.enums.pay;


/**
 * 付款类型
 *
 * @author shuheng
 */
public enum PayMethodEnum {

    ALIPAY("1", "支付宝"),
    WECHAT_PAY("2", "微信"),
    BANK_CARD_PAY("3", "银行卡"),
    ONENET_PAY("4", "一网通"),
    BALANCE_PAY("5", "钱包/余额支付"),
    ENTERPRISE_PAY("6", "企业"),
    CASH_PAY("7", "现金/线下支付");

    private String code;
    private String name;

    PayMethodEnum(String code, String name) {
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
