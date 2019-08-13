package com.shuframework.commonbiz.enums.pay;


/**
 * 支付渠道类型，其实是 PayMethodEnum + PayTypeEnum
 *
 * @author shuheng
 */
public enum PayChannelEnum {

    ALIPAY_APP("0101", "支付宝APP支付"),
    ALIPAY_WAP("0102", "支付宝H5支付"),
    ALIPAY_SCAN("0103", "支付宝扫码支付"),
    ALIPAY_FREE("0104", "支付宝免密支付"),

    WECHAT_APP("0201","微信APP支付"),
    WECHAT_SCAN("0203","微信扫码支付"),
    WECHAT_FREE("0204","微信APP免密支付"),
    WECHAT_MINI("0205","微信小程序支付"),
    WECHAT_MINI_FREE("0206","微信小程序免密支付"),
    WECHAT_PUBLIC("0207","微信公众号支付"),

    UNIONPAY_WAP("0302","银联WAP支付"),
//    UNIONPAY_ALIPAY("0305","银联支付宝支付"),
//    UNIONPAY_WECHAT("0306","银联微信支付"),

    CCBPAY_WAP("0311","建行支付"),

    ONENET_WAP("0402","一网通H5支付"),
    ONENET_FREE("0404","一网通免密支付");


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
