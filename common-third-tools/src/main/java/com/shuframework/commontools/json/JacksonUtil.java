package com.shuframework.commontools.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * jackson 解析json工具类
 * @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
 * @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")。
 * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")。
 * 
 * @author shuheng
 *
 */
public class JacksonUtil {

    // 定义jackson对象
	// 这个比较耗时所有定义为静态的
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串(注意如果有null值 会被序列化)<br>
     * 时间默认处理为日期戳, @JsonFormat使用该注解可以改变格式<br>
     * 
     * @param obj
     * @return
     */
    public static String obj2JsonStr(Object obj) {
    	try {
			String string = MAPPER.writeValueAsString(obj);
			return string;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * 将json转为T对象
     * 
     * @param jsonStr json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonStr2Obj(String jsonStr, Class<T> beanType) {
        try {
        	T t = MAPPER.readValue(jsonStr, beanType);
            return t;
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    /**
	 * 将json转为map
	 * 
	 * @param jsonStr
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	public static Map jsonStr2Map(String jsonStr) {
    	return jsonStr2Obj(jsonStr, Map.class);
	}
    
    /**
     * 将json数据转换成pojo对象list
     * 
     * @param jsonStr
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonStr2List(String jsonStr, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonStr, javaType);
    		return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
	 * 将json转为list<Map>
	 * 
	 * @param jsonStr
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	public static List<Map> jsonStr2List(String jsonStr) {
    	return jsonStr2List(jsonStr, Map.class);
    }
}
