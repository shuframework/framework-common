package com.shuframework.commontools.net;

import com.shuframework.commontools.json.JsonUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模拟http请求工具类
 * @author shu
 *
 */
public class HttpClientUtil {
	
	private static final String METHODTYPE_POST = "post";
	private static final String METHODTYPE_POSTJSON = "postJson";	
	private static final String METHODTYPE_GET = "get";
	private static final String CHARTSET_UTF8 = "utf-8";
	
	/**
	 * 获得CloseableHttpResponse 对象
	 * @param url
	 * @param param
	 * @param methodType
	 * @param httpClient
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static CloseableHttpResponse initResponse(String url, Map<String, Object> param, String methodType, CloseableHttpClient httpClient) throws URISyntaxException, ClientProtocolException, IOException {
		CloseableHttpResponse response = null;
		if (METHODTYPE_POST.equals(methodType)) {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key).toString()));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, CHARTSET_UTF8);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			
		}else if (METHODTYPE_POSTJSON.equals(methodType)) {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			String json = JsonUtil.obj2JsonStr(param);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			
		}else if (METHODTYPE_GET.equals(methodType)) {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key).toString());
				}
			}
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);	
			// 执行请求
			response = httpClient.execute(httpGet);
		}
		return response;
	}
	
	/**
	 * 获得CloseableHttpClient 对象
	 * @return
	 */
	private static CloseableHttpClient initHttpClient(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		return httpClient;
	}
	
	/**
	 * 获得String形式的请求结果
	 * @param url	请求地址
	 * @param param	参数
	 * @param methodType	请求类型，如get、post
	 * @return
	 */
	private static String getResult(String url, Map<String, Object> param, String methodType) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = initHttpClient();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 执行请求
			response = initResponse(url, param, methodType, httpClient);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), CHARTSET_UTF8);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	/**
	 * 有参数的get请求, 返回json形式字符串
	 * @param url	请求地址
	 * @param param	参数
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> param) {
		return getResult(url, param, METHODTYPE_GET);
	}
	
	/**
	 * 无参数的get请求, 返回json形式字符串
	 * @param url	请求地址
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}
	
	/**
	 * 有参数的get请求, 返回map
	 * @param url	请求地址
	 * @param param	参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map doGet2Map(String url, Map<String, Object> param) {
		String jsonStr = doGet(url, param);
		return JsonUtil.jsonStr2Map(jsonStr);
	}
	
	/**
	 * 无参数的get请求, 返回map
	 * @param url	请求地址
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map doGet2Map(String url) {
		String jsonStr = doGet(url);
		return JsonUtil.jsonStr2Map(jsonStr);
	}
	
	/**
	 * 有参数的post请求
	 * @param url	请求地址
	 * @param param	参数
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> param) {
		return getResult(url, param, METHODTYPE_POST);
	}
	
	/**
	 * 无参数的post请求
	 * @param url	请求地址
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url, null);
	}
	
	/**
	 * 有参数的post请求, 返回map
	 * @param url	请求地址
	 * @param param	参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map doPost2Map(String url, Map<String, Object> param) {
		String jsonStr = doPost(url, param);
		return JsonUtil.jsonStr2Map(jsonStr);
	}
	
	/**
	 * 无参数的post请求, 返回map
	 * @param url	请求地址
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map doPost2Map(String url) {
		String jsonStr = doPost(url);
		return JsonUtil.jsonStr2Map(jsonStr);
	}

	
	/**
	 * 有参数的post ajax请求
	 * @param url	请求地址
	 * @param param	参数
	 * @return
	 */
	public static String doPostJson(String url, Map<String, Object> param) {
		return getResult(url, param, METHODTYPE_POSTJSON);
	}

}
