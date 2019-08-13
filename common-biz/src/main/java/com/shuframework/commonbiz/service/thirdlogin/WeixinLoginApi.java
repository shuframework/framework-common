package com.shuframework.commonbiz.service.thirdlogin;

import com.shuframework.commonbase.result.Result;
import com.shuframework.commonbase.result.ResultUtil;
import com.shuframework.commonbase.util.SystemUtil;
import com.shuframework.commontools.json.JsonUtil;
import com.shuframework.commontools.net.OkHttpClientUtil;

/**
 * 微信用户 api
 * @author shuheng
 *
 * 参考文章 https://www.cnblogs.com/sunshq/p/5132811.html
 */
public class WeixinLoginApi {

//    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String WX_APP_ID = "wx0cfba88c5eddf310";
    private static final String WX_APP_SECRET = "d6300054b792d1956731491d3caccec0";

    //code 是根据 地址请求在变化的，所以前端访问比较好

    /**
     * 获取微信 登录的token
     *
     * @param code
     * @return
     */
    public Result getWeixinToken(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                WX_APP_ID, WX_APP_SECRET, code);
        return getResultByTokenResult(url);
    }

    /**
     * 刷新token
     *
     * @param refreshToken
     * @return
     */
    public Result refreshToken(String refreshToken) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s",
                WX_APP_ID, refreshToken);
        return getResultByTokenResult(url);
    }

    private Result getResultByTokenResult(String url) {
        String strResult = OkHttpClientUtil.getInstance().get(url);
//        logger.info("微信获取TOKEN_URL返回:{}", strResult);//内部有打印结果，重新定义关键字查询也行
        WeixinTokenResult weixinTokenResult = JsonUtil.jsonStr2Obj(strResult, WeixinTokenResult.class);
        // 如果有错误 解析返回
        String errmsg = weixinTokenResult.getErrmsg();
        if (SystemUtil.isNotEmpty(errmsg)) {
            return ResultUtil.failure(weixinTokenResult.getErrcode(), errmsg, weixinTokenResult);
        }
        return ResultUtil.success(weixinTokenResult);
    }


    /**
     * 获得微信用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    public Result getUserInfo(String accessToken, String openId) {
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s",
                accessToken, openId);
        String strResult = OkHttpClientUtil.getInstance().get(url);
        WeixinUserInfo userInfo = JsonUtil.jsonStr2Obj(strResult, WeixinUserInfo.class);
        // 如果有错误 解析返回
        String errmsg = userInfo.getErrmsg();
        if (SystemUtil.isNotEmpty(errmsg)) {
            return ResultUtil.failure(userInfo.getErrcode(), errmsg, userInfo);
        }
        return ResultUtil.success(userInfo);
    }

}
