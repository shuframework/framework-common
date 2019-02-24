package com.shuframework.commontools;

import com.shuframework.commonbase.constant.CharsetConstant;
import com.shuframework.commonbase.util.io.FileUtil;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * bean的工具类,包含的方法有 <br>
 * 	1)map与bean 的互转 <br>
 * 
 * 	11)实例化对象 <br>
 *  12)获得申明的方法和变量  <br>
 *  13)属性赋值
 * 
 * javaBean与Map<String,Object>互转利用到了java的内省（Introspector）和反射（reflect）机制。 其思路为：
 * 通过类 Introspector 来获取某个对象的 BeanInfo 信息， 然后通过 BeanInfo
 * 来获取属性的描述器PropertyDescriptor， 再利用属性描述器获取某个属性对应的 getter/setter 方法，
 * 然后通过反射机制来getter和setter。 org.springframework.beans.BeanUtils
 * 
 * @author shuheng
 */
public class BeanUtil extends org.springframework.beans.BeanUtils {
	private BeanUtil(){}

	/**
	 * 通过getParameterName获得的请求参数转换为map
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getParameterByNames(HttpServletRequest request){
		Map<String, Object> map =new HashMap<>();
		Enumeration<String> paraNames = request.getParameterNames();
		while (paraNames.hasMoreElements()) {
			String key = paraNames.nextElement();
			String value = request.getParameter(key);
		    map.put(key, value);
		}
		return map;
	}

	/**
	 * 通过getInputStream获得的请求参数转换为String
	 * @param request
	 * @return
	 */
	public static String getParameterByStream(HttpServletRequest request) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String bodyStr = FileUtil.readByBinary(inputStream, CharsetConstant.CHARSET_UTF8);
		return bodyStr;
	}
	
	/**
	 * 实例化对象
	 * 
	 * @param clazz	类
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) {
		return (T) instantiate(clazz);
	}

	/**
	 * 实例化对象
	 * 
	 * @param clazzStr	类名
	 * @return 对象
	 */
	public static <T> T newInstance(String clazzStr) {
		try {
			Class<?> clazz = Class.forName(clazzStr);
			return newInstance(clazz);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取Bean的属性
	 * 
	 * @param bean
	 * @param propertyName	属性名
	 * @return 属性值
	 */
	public static Object getProperty(Object bean, String propertyName) {
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
		if (pd == null) {
			throw new RuntimeException(
					"Could not read property '" + propertyName + "' from bean PropertyDescriptor is null");
		}
		Method readMethod = pd.getReadMethod();
		if (readMethod == null) {
			throw new RuntimeException("Could not read property '" + propertyName + "' from bean readMethod is null");
		}
		if (!readMethod.isAccessible()) {
			readMethod.setAccessible(true);
		}
		try {
			return readMethod.invoke(bean);
		} catch (Throwable ex) {
			throw new RuntimeException("Could not read property '" + propertyName + "' from bean", ex);
		}
	}

	/**
	 * 设置Bean属性
	 * 
	 * @param bean
	 * @param propertyName	属性名
	 * @param value		属性值
	 */
	public static void setProperty(Object bean, String propertyName, Object value) {
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
		if (pd == null) {
			throw new RuntimeException(
					"Could not set property '" + propertyName + "' to bean PropertyDescriptor is null");
		}
		Method writeMethod = pd.getWriteMethod();
		if (writeMethod == null) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean writeMethod is null");
		}
		if (!writeMethod.isAccessible()) {
			writeMethod.setAccessible(true);
		}
		try {
			writeMethod.invoke(bean, value);
		} catch (Throwable ex) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean", ex);
		}
	}

	/**
	 * copy 对象属性到另一个对象（类型，名称相同），不使用Convert即不支持类型转换
	 * 
	 * @param src
	 * @param clazz		类名
	 * @return T
	 */
	public static <T> T copy(Object src, Class<T> clazz) {
		BeanCopier copier = BeanCopier.create(src.getClass(), clazz, false);

		T to = newInstance(clazz);
		copier.copy(src, to, null);
		return to;
	}

	/**
	 * 拷贝对象
	 * spring的 BeanUtils.copyProperties(Object src, Object dist) 也能达到一样的效果
	 * 
	 * @param src	源对象
	 * @param dist	需要赋值的对象
	 */
	public static void copy(Object src, Object dist) {
		BeanCopier copier = BeanCopier.create(src.getClass(), dist.getClass(), false);

		copier.copy(src, dist, null);
	}

	/**
	 * 将对象装成map形式 （复杂对象不存在类型转换的问题）
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map beanToMap(Object obj) {
		//出现加不了obj 之外的key, 重新定义map接收即可
//		return BeanMap.create(obj);
		Map map = new HashMap();
		BeanMap beanMap = BeanMap.create(obj);
		map.putAll(beanMap);
		return map;
	}

	/**
	 * map转实体类（复杂对象不存在类型转换的问题）
	 * 
	 * @param map
	 * @param t
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T mapToBean(Map map, T t) {
		if (map == null || map.isEmpty() || t == null) {
			return t;
		}
		BeanMap beanMap = BeanMap.create(t);
		beanMap.putAll(map);
		beanMap.getBean();
		return t;
	}
	
}
