package com.shuframework.commonbiz.enums.pay;


/**
 * 付款方式
 *
 * @author shuheng
 */
public enum PaymentMethodEnum {

    ALIPAY("1", "支付宝"),
    WECHAT_PAY("2", "微信"),
    BANK_CARD_PAY("3", "银行卡"),
    CASH_PAY("4", "现金/线下支付"),
    BALANCE_PAY("5", "钱包/余额支付"),
    ENTERPRISE_PAY("6", "企业"),
    OTHER_PAY("7", "其他");

    private String code;
    private String name;

    PaymentMethodEnum(String code, String name) {
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
