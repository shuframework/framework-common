package com.shuframework.commontools.net;

import com.shuframework.commonbase.enums.RequestMethod;
import com.shuframework.commonbase.result.Result;
import com.shuframework.commonbase.result.ResultUtil;
import com.shuframework.commontools.json.JsonUtil;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于okhttp3 实现的客户端http请求，可支持异步请求
 * execute的方法是同步方法; enqueue的方法是异步方法;
 * 暂时加入的都是同步方法,只支持http
 *
 * @author shuheng
 */
public class OkHttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtil.class);

    //时间单位都是秒
    public final static int READ_TIMEOUT = 100;
    public final static int CONNECT_TIMEOUT = 60;
    public final static int WRITE_TIMEOUT = 60;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient okHttpClient;

    private OkHttpClientUtil() {
        okHttpClient = new OkHttpClient.Builder()
                //读取超时
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                //连接超时
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                //写入超时
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    //内部类
    private static class InnerHttpClass {
        private static OkHttpClientUtil instance = new OkHttpClientUtil();
    }

    /**
     * 单例模式获取NetUtils
     *
     * @return
     */
    public static OkHttpClientUtil getInstance() {
        return InnerHttpClass.instance;
    }

    /**
     * 无需token的 get 请求
     * @param url
     * @return
     */
    public Result get(String url) {
        return get(url, null);
    }

    //get 请求
    public Result get(String url, String token) {
        return urlRequest(url, token, RequestMethod.GET);
    }

    //delete 请求
    public Result delete(String url, String token) {
        return urlRequest(url, token, RequestMethod.DELETE);
    }

    // url的请求，即参数在url上
    private Result urlRequest(String url, String token, RequestMethod method) {
        Request.Builder builder = new Request.Builder().url(url);
        if (RequestMethod.GET.equals(method)){
            builder.get();
        }
        if (RequestMethod.DELETE.equals(method)){
            builder.delete();
        }
        if (token != null) {
            builder.addHeader("Authorization", token);
        }
        Request request = builder.build();
        return getResult(request);
    }


    /**
     * 无需token的post请求
     * @param url
     * @param json
     * @return
     */
    public Result post(String url, String json) {
        return post(url, null, json);
    }

    public Result post(String url, String token, String json) {
        return jsonRequest(url, token, json, RequestMethod.POST);
    }

    public Result put(String url, String token, String json) {
        return jsonRequest(url, token, json, RequestMethod.PUT);
    }

    // RequestMethod 可以是自己弄的一个枚举，这里用的spring的
    // json请求， 即参数是json格式的
    private Result jsonRequest(String url, String token, String json, RequestMethod method) {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder().url(url);
        if (RequestMethod.POST.equals(method)){
            builder.post(body);
        }
        if (RequestMethod.PUT.equals(method)){
            builder.put(body);
        }
        if (RequestMethod.DELETE.equals(method)){
            builder.delete(body);
        }
        if (token != null) {
            builder.addHeader("Authorization", token);
        }
        Request request = builder.build();
        return getResult(request);
    }

    //form post请求
    // new FormBody.Builder().add().add().build();
    public Result formPost(String url, String token, FormBody formBody) {
        Request.Builder builder = new Request.Builder().url(url).method("POST", formBody);
        if (token != null) {
            builder.addHeader("Authorization", token);
        }
        Request request = builder.build();
        return getResult(request);
    }

    public Result formPost(String url, String token, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach ((k, v) -> {
            builder.add(k, v);
        });
        return formPost(url, token, builder.build());
    }


    private Result getResult(Request getRequest) {
        try {
            Response response = okHttpClient.newCall(getRequest).execute();
            String str = response.body().string();
            // 解析返回结果
            Map returnMap = JsonUtil.jsonStr2Map(str);
            Result result = ResultUtil.success(returnMap);
            // 这里也可以对result 进行再次解析为满足业务的对象
            logger.info("返回结果：{}", result.toString());
            return result;
        } catch (IOException e) {
            logger.error("异常信息：{}", e.getMessage());
        }
        return null;
    }
}
