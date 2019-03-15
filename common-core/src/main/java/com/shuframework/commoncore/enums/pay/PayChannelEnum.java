package com.shuframework.commoncore.enums.pay;


/**
 * 支付渠道类型
 *
 * @author shuheng
 */
public enum PayChannelEnum {

    ALIPAY_APP("0101", "支付宝APP支付"),
    ALIPAY_H5("0102", "支付宝H5支付"),
    ALIPAY_SCAN("0103", "支付宝扫码支付"),
    ALIPAY_FREE("0104", "支付宝免密支付"),

    WECHAT_APP("0201","微信APP支付"),
    WECHAT_PUBLIC("0202","微信公众号支付"),
    WECHAT_SCAN("0203","微信扫码支付"),
    WECHAT_APP_FREE("0204","微信APP免密支付"),
    WECHAT_MINI("0205","微信小程序支付"),
    WECHAT_MINI_FREE("0206","微信小程序免密支付"),

    UNIONPAY_WAP("0301","银联WAP支付"),
    UNIONPAY_ALIPAY("0302","银联支付宝支付"),
    UNIONPAY_WECHAT("0303","银联微信支付"),

    CCBPAY_WAP("0305","建行支付"),

    ONENET_H5("0401","一网通H5支付"),
    ONENET_FREE("0402","一网通免密支付");


    private String code;
    private String name;

    PayChannelEnum(String code, String name) {
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
