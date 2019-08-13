package com.shuframework.commonbiz.service.thirdlogin;

import lombok.Data;

import java.util.List;

@Data
public class WeixinUserInfo {

    /** 用户的唯一标识 */
    private String openid;
    /** 用户昵称 */
    private String nickname;
    /** 用户的性别，1男性，2女性，0未知 */
    private Integer sex;
    /** 省份 */
    private String province;
    /** 城市 */
    private String city;
    /** 国家，如中国为CN */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。
     * 若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private List<String> privilege;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;


    /** 错误编码 */
    private Integer errcode;
    /** 错误信息 */
    private String errmsg;

}
