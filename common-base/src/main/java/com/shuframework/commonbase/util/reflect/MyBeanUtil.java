package com.shuframework.commonbase.util.reflect;

import com.shuframework.commonbase.util.io.FileUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象反射
 * 线上环境不推荐用这个，推荐用 org.springframework.beans.BeanUtils
 * @author shuheng
 *
 */
@SuppressWarnings("rawtypes")
public class MyBeanUtil {

	/**
	 * 实例化对象
	 * @param clazz
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) {
		try {
			T obj = (T) clazz.newInstance();
			return obj;
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
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
	 * 获得内省PropertyDescriptor对象
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	protected static PropertyDescriptor getPropertyDescriptor(final Class clazz, String propertyName) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz);
			return pd;
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	protected static PropertyDescriptor[] getPropertyDescriptorList(final Class clazz) {
		try {
			//获得所有
//			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			//获得除掉Object
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			PropertyDescriptor[] pdList = beanInfo.getPropertyDescriptors();
			return pdList;
		} catch (IntrospectionException e) {
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
	public static Object getProperty(Object bean, String propertyName){
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
		Method readMethod = pd.getReadMethod();
		if (!readMethod.isAccessible()) {
			readMethod.setAccessible(true);
		}
		try {
			return readMethod.invoke(bean);
		} catch (Exception e) {
			throw new RuntimeException("Could not read property '" + propertyName + "' to bean", e);
		}
	}
	
	/**
	 * 获取Bean的属性（建议不用这个,因为大部分都是获得对象属性）
	 * 
	 * @param clazz
	 * @param propertyName 	属性名
	 * @return
	 */
	public static Object getProperty(final Class clazz, String propertyName){
//		try {
//			return getProperty(clazz.newInstance(), propertyName);
//		} catch (Exception e) {
//			throw new RuntimeException("Could not read property '" + propertyName + "' to bean", e);
//		}
		return getProperty(newInstance(clazz), propertyName);
	}
	
	/**
	 * 获取Bean的属性(过时的方法)
	 * 
	 * @param bean
	 * @param propertyName	属性名
	 * @return 属性值
	 */
	@Deprecated
	public static Object getPropertyByField(Object bean, String propertyName){
		try {
            Field field = getDeclaredField(bean.getClass(), propertyName);
			return field.get(bean);
		} catch (Exception e) {
			throw new RuntimeException("Could not read property '" + propertyName + "' to bean", e);
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
		Method writeMethod = pd.getWriteMethod();
		if (!writeMethod.isAccessible()) {
			writeMethod.setAccessible(true);
		}
		try {
			writeMethod.invoke(bean, value);
		} catch (Exception e) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean", e);
		}
	}
	
	/**
	 * 设置Bean属性
	 * 
	 * @param clazz
	 * @param propertyName	属性名
	 * @param value		属性值
	 */
	public static void setProperty(final Class clazz, String propertyName, Object value) {
//		try {
//			setProperty(clazz.newInstance(), propertyName, value);
//		} catch (Exception e) {
//			throw new RuntimeException("Could not set property '" + propertyName + "' to bean", e);
//		}
		setProperty(newInstance(clazz), propertyName, value);
	}
	
	/**
	 * 获得属性的值
	 * 
	 * @param bean
	 * @param property
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object getProperty(Object bean, PropertyDescriptor property)
			throws IllegalAccessException, InvocationTargetException {
		Method readMethod = property.getReadMethod();
		if (!readMethod.isAccessible()) {
			readMethod.setAccessible(true);
		}
		Object value = readMethod.invoke(bean);
		return value;
	}
	
	/**
	 * 设置属性的值
	 * 
	 * @param bean
	 * @param property
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setProperty(Object bean, PropertyDescriptor property, Object value)
			throws IllegalAccessException, InvocationTargetException {
		Method writeMethod = property.getWriteMethod();
		if (!writeMethod.isAccessible()) {
			writeMethod.setAccessible(true);
		}
		//类型转换未处理
		writeMethod.invoke(bean, value);
	}
	
	/**
	 * 设置Bean属性
	 * 
	 * @param bean
	 * @param propertyName	属性名
	 * @param value		属性值
	 */
	@Deprecated
	public static void setPropertyByField(Object bean, String propertyName, Object value) {
		try {
            Field field = getDeclaredField(bean.getClass(), propertyName);
			field.set(bean, value);
		} catch (Exception e) {
			throw new RuntimeException("Could not set property '" + propertyName + "' to bean", e);
		}
	}
	
	/**
	 * 获得申明的字段, 如果有继承关系, 子类拿不到会去父类获取
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public static Field getDeclaredField(final Class clazz, final String propertyName){
        Field field = null;
        try {
            field = clazz.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Could not find field [" + propertyName + "] on target [" + clazz + "]");
        }
        if(!field.isAccessible()){
            field.setAccessible(true);
        }
        return field;
    }

    public static Field getDeclaredFieldHasSuper(final Class clazz, final String fieldName) {
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
                continue;
            }
        }
        return null;
    }

	
	/**
	 * 拷贝对象, 跳过null
	 * source对象的属性 不能比target对象 多
	 * 
	 * @param source	源对象
	 * @param target	需要赋值的对象
	 */
	@Deprecated
	public static void copyByField(Object source, Object target) {
		PropertyDescriptor[] pdList = getPropertyDescriptorList(source.getClass());
		try {
			for (PropertyDescriptor property : pdList) {
                String name = property.getName();
                Object value = getPropertyByField(source, name);
				//这里存在找不到的情况, 不判断则会null 进行覆盖
				if (value != null) {
					setPropertyByField(target, name, value);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 拷贝对象, src对象的属性 与dist对象属性可以对不上, 那么则赋值不成功, 且不报错<br>
	 * src对象的属性 比dist对象 多, 那么多的属性dist 不会被赋值<br>
	 * src对象的属性 比dist对象 少, 那么多的属性dist 不会被赋值<br>
	 * 
	 * @param source	源对象
	 * @param target	需要赋值的对象
	 */
	public static void copyIgnoreException(Object source, Object target) {
		PropertyDescriptor[] pdList = getPropertyDescriptorList(source.getClass());
		for (PropertyDescriptor property : pdList) {
//			String key = property.getName();
//			// 过滤class属性(由于在getPropertyDescriptorList获取的时候已经排除了所有可用不判断)
//			if (key.equals("class")) {
//				System.out.println(property);
//				continue;
//			}
			try {
				// 得到property对应的getter方法
				Object value = getProperty(source, property);
				if (value != null) {
					setProperty(target, property, value);
				}
			} catch (Exception e) {
				//不推荐这样处理
				continue;
			}
		}
	}

	
	/**
	 * 深度复制, 利用流的 序列化和反序列化, 返回的结果与参数类型一致，注意类型转换异常的问题
	 * 注意obj 必须实现Serializable接口
	 *
	 * @param src
	 */
	public static Object deepCopy(Object src) {
		Object dist = null;
		try {
			dist = FileUtil.deepCopy(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return dist;
	}
	
	/**
	 * 对象转为map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> map = new HashMap<>();
		PropertyDescriptor[] pdList = getPropertyDescriptorList(bean.getClass());
		try {
			for (PropertyDescriptor property : pdList) {
				String key = property.getName();
//				// 过滤class属性(由于在getPropertyDescriptorList获取的时候已经排除了所有可用不判断)
//				if (key.equals("class")) {
//					System.out.println(property);
//					continue;
//				}
				Object value = getProperty(bean, property);
				map.put(key, value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	
	/**
	 * map转为bean
	 * 
	 * @param map
	 * @param bean
	 */
	public static void mapToBean(Map<String, Object> map, Object bean) {
		PropertyDescriptor[] pdList = getPropertyDescriptorList(bean.getClass());
		System.out.println(Arrays.toString(pdList));
		try {
			for (PropertyDescriptor property : pdList) {
				String key = property.getName();
				Object value = map.get(key);
				// 跳过空值或者不存在的key
				if (value == null) {
					continue;
				}
				setProperty(bean, property, value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
