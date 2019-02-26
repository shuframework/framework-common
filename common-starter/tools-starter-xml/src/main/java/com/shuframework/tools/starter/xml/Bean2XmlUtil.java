package com.shuframework.tools.starter.xml;

import com.shuframework.commonbase.constant.CharsetConstant;
import com.shuframework.commonbase.util.lang.StringUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 基于xstream 解析xml的工具类 
 * 
 * @author: shu
 */
public class Bean2XmlUtil{
	
	private static final String XML_SUFFER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	/**
	 * java对象 转换成xml, 节点别名默认是类名小写
	 * 如果是list时里面有多个对象时，其节点是类全名
     *
	 * @param obj
	 * @return
	 */
	public static String toXmlStr(Object obj) {
		String aliasName = StringUtil.uncapitalize(obj.getClass());
		return toXmlStr(obj, aliasName);
	}
	
    /**
     * java对象 转换成xml
     * 
     * @param obj 对象实例
     * @param aliasName 节点别名
     */
    public static String toXmlStr(Object obj, String aliasName){
//        XStream xstream=new XStream();
        XStream xstream=new XStream(new DomDriver()); //直接用jaxp dom来解释
//        XStream xstream=new XStream(new DomDriver("utf-8")); //指定编码解析器,直接用jaxp dom来解释
        
        //如果没有这句，xml中的根元素会是<包.类名>
        xstream.alias(aliasName, obj.getClass());
        return XML_SUFFER + "\n" + xstream.toXML(obj);
    }

    
    /**
     * 将传入xml文本转换成Java对象, 别名默认是类名的首字母小写
     * @param xmlStr
     * @param clazz
     * 
     * 调用的方法实例：PersonBean person = Bean2XmlUtil.toBean(xmlStr, PersonBean.class);
     */
    public static <T> T toBean(String xmlStr, Class<T> clazz) {
    	String aliasName = StringUtil.uncapitalize(clazz);
		return toBean(xmlStr, clazz, aliasName);
	}
    
    
	/**
     * 将传入xml文本转换成Java对象
     * @param xmlStr
     * @param clazz  xml对应的class类
     * @param aliasName  别名
     * 
     */
    @SuppressWarnings("unchecked")
	public static <T> T toBean(String xmlStr, Class<T> clazz, String aliasName) {
		// 注意：不是new Xstream(); 否则报错：java.lang.NoClassDefFoundError:
		XStream xstream = new XStream(new DomDriver());
		xstream.alias(aliasName, clazz);
		T obj = (T) xstream.fromXML(xmlStr);
		return obj;
	}

    
    /**
     * 写到xml文件中去
     * @param obj 对象
     * @param filePath 绝对路径
     */
	public static boolean toFile(Object obj, String filePath) throws IOException{
		String strXml = toXmlStr(obj);
//		String filePath = absPath + fileName;
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("创建{" + filePath + "}文件失败！", e);
			}
		}
		
		OutputStream ous = null;
		try {
			ous = new FileOutputStream(file);
			ous.write(strXml.getBytes());
			ous.flush();
		} catch (IOException e) {
			return false;
		} finally {
			if (ous != null){
				ous.close();
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param filePath
	 * @param clazz
	 * @return
	 */
	public static <T> T toBeanFromFile(String filePath, Class<T> clazz){
		String aliasName = StringUtil.uncapitalize(clazz);
		return toBeanFromFile(filePath, clazz, aliasName);
	}
	
    /**
     * 从xml文件读取报文
     * @param filePath 绝对路径
     * @param clazz
     * @param aliasName 别名
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static <T> T toBeanFromFile(String filePath, Class<T> clazz, String aliasName) {
//		String filePath = absPath + fileName;
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(filePath));
		} catch (IOException e) {
			throw new RuntimeException("读{" + filePath + "}文件失败！", e);
		}

//		String encode = "utf-8";
		XStream xstream = new XStream(new DomDriver(CharsetConstant.CHARSET_UTF8));
		xstream.alias(aliasName, clazz);
		T obj = null;
		try {
			obj = (T) xstream.fromXML(ins);
		} catch (Exception e) {
			throw new RuntimeException("解析{" + filePath + "}文件失败！", e);
		}finally {
			if (ins != null){
				try {
					ins.close();
				} catch (IOException e) {
					throw new RuntimeException("关闭流失败！", e);
				}
			}
		}
		
		return obj;
	}

}
