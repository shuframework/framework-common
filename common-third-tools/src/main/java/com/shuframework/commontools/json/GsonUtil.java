package com.shuframework.commontools.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Google的Gson
 * 
 * @author shuheng
 * Gson类：解析json的最基础的工具类<br>
 *       JsonParser类：解析器来解析<br>
 *       JSON到JsonElements的解析树 <br>
 *       JsonElement类：一个类代表的JSON元素<br>
 *       JsonObject类：JSON对象类型 <br>
 *       JsonArray类：JsonObject数组<br>
 *       TypeToken类：用于创建type，比如泛型List<?>
 */
public class GsonUtil {

	/**
	 * bean转换json <br>
	 * 默认的处理时间,yyyy-MM-dd HH:mm:ss<br>
	 * pojo、list、map都可以, 顺序是对象属性的顺序<br>
	 * (注意如果有null值不会被序列化)
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2JsonStr(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		//gson默认会把数值,byte,int,short,long,float数据反序列化时，变成double类型。指定Long类型的数据序列化方式为字符串方式
		builder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		Gson gson = builder.create();
		// Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}

	/**
	 * 将json转为map
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map jsonStr2Map(String jsonStr) {
		Gson gson = new Gson();
		Map map = gson.fromJson(jsonStr, Map.class);
		return map;
	}
	
	/**
	 * 将json转为map(测试转换map的第二种方式)
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map jsonStr2MapByTypeToken(String jsonStr) {
		Gson gson = new Gson();
		Map map = gson.fromJson(jsonStr, new TypeToken<Map>() {}.getType());
		return map;
	}
	
	/**
	 * 将json转为T对象
	 * 
	 * @param jsonStr
	 * @return 
	 * @return
	 */
	public static <T> T jsonStr2Obj(String jsonStr, Class<T> clazz) {
		Gson gson = new Gson();
		T t = gson.fromJson(jsonStr, clazz);
		return t;
	}

	/**
	 * 将json转换成list
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map> jsonStr2List(String jsonStr) {
//		Gson gson = new Gson();
//		// 将json转换成List(复杂类型的bean),需要使用TypeToken
//		List list = gson.fromJson(jsonStr, new TypeToken<List>() {}.getType());
//		return list;
		return jsonStr2List(jsonStr, Map.class);
	}
	
//	//这样转换反而麻烦了，gson是直接内部处理了复杂情况
//	public static List<Map<String, Object>> json2List(String jsonStr) {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Gson gson = new Gson();
//		// 将json转换成List
//		List jsonList = gson.fromJson(jsonStr, new TypeToken<List>() {
//		}.getType());
//		for (int i = 0, max = jsonList.size(); i < max; i++) {
//			Map<String, Object> map = json2Map(jsonList.get(i).toString());
//			list.add(map);
//		}
//		return list;
//	}
	
	/**
	 * 将json转为list<T> 对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonStr2List(String jsonStr, Class<T> clazz) {
		Gson gson = new Gson();
		// 将json转换成List(复杂类型的bean),需要使用TypeToken
		List<T> list = gson.fromJson(jsonStr, new TypeToken<List<T>>() {}.getType());
		return list;
	}

}
