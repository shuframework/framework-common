package com.shuframework.commonbiz.service.thirdlogin;

import lombok.Data;

/**
 * 微信 token信息
 */
@Data
public class WeixinTokenResult {

    private String access_token;
    /** token超时时间，单位秒 */
    private Integer expires_in;
    /** 刷新access_token */
    private String refresh_token;

    private String openid;
    /** 用户授权的作用域 */
    private String scope;
    /** 获得用户的userinfo授权时，才会出现 */
    private String unionid;

    /** 小程序返回 */
    private String session_key;

    /** 错误编码 */
    private Integer errcode;
    /** 错误信息 */
    private String errmsg;

}
