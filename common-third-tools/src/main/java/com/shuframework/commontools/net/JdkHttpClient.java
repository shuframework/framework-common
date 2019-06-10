package com.shuframework.commontools.net;

import com.shuframework.commonbase.exception.UtilException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 基于jdk8实现，jdk8+ 换了实现类
 * 链式编程 后续可改成lambda方式
 *
 * @author shuheng
 */
public class JdkHttpClient {

    /**
     * HttpURLConnection 工厂函数
     *
     * @param url 请求目的地址
     * @return HttpURLConnection 对象
     */
    public HttpURLConnection initHttpConnection(String url) {
//        URL httpUrl = null;
        try {
            URL httpUrl = new URL(url);
            return (HttpURLConnection) httpUrl.openConnection();
        } catch (MalformedURLException e) {
            throw new UtilException("初始化连接出错！URL：" + url + "格式不对！");
        } catch (IOException e) {
            throw new UtilException("初始化连接出错！URL：" + url);
        }
    }


}
