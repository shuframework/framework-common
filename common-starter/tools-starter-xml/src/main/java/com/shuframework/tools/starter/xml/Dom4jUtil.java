package com.shuframework.tools.starter.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.TreeMap;


/**
 * 基于dom4j的xml工具类
 * 
 * @author	shu
 */
public class Dom4jUtil {
	//http://developer.51cto.com/art/200903/117512.html
	//https://www.cnblogs.com/liuchaogege/p/5869044.html
	
	/**
	 * document 转 xmlStr
	 * @param document
	 */
	public static String document2XmlStr(Document document) {
		return document.asXML();
	}
	//document.asXML()底层实现，通过io流来实现
//	public static String asXML(Document document, String encoding) {
//        OutputFormat format = new OutputFormat();
//        format.setEncoding(encoding);
//        
//        try {
//            StringWriter out = new StringWriter();
//            XMLWriter writer = new XMLWriter(out, format);
//            writer.write(document);
//            writer.flush();
//
//            return out.toString();
//        } catch (IOException e) {
//            throw new RuntimeException("IOException while generating textual "
//                    + "representation: " + e.getMessage());
//        }
//    }
	
	
	/**
	 * xmlStr 转 document
	 * @param xmlStr
	 * @throws DocumentException
	 */
	public static Document xmlStr2Document(String xmlStr) throws DocumentException {
		return DocumentHelper.parseText(xmlStr);
	}
	
	/**
	 * 通过文件路径获得document
	 * @param filePath	文件路径
	 * @throws DocumentException
	 */
	public static Document getDocument(String filePath) throws DocumentException {
		//获得解析流
		SAXReader reader = new SAXReader();
		//xml文件的解析
		Document document = reader.read(filePath);
		return document;
	}

	/**
	 * 获得根节点
	 * @param document
	 */
	public static Element getRoot(Document document){
		//获得根元素
		Element root = document.getRootElement();
		return root;
	}

	
	/**
	 * 获得根节点下所有子节点, 如果是有多级需要多次调用
	 * @param document
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChild(Document document){
		//获得根元素
		Element root = document.getRootElement();
		//获得子节点
		List<Element> childList = root.elements();
		return childList;
	}
	
	/**
	 * 获得根节点下指定子节点
	 * @param document
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChild(Document document, String childName){
		//获得根元素
		Element root = document.getRootElement();
		//获得子节点
		List<Element> childList = root.elements(childName);
		return childList;
	}

	
	/**
	 * 读取xml文件, 获得根节点下所有子节点, 如果是有多级需要多次调用
	 * 
	 * @param filePath
	 * @throws DocumentException
	 */
	public static List<Element> getChildByFilePath(String filePath) throws DocumentException{
		Document document = getDocument(filePath);
		return getChild(document);
	}
	
	/**
	 * 读取xml文件, 获得根节点下childName节点
	 * 
	 * @param filePath
	 * @param childName
	 * @throws DocumentException
	 */
	public static List<Element> getChildByFilePath(String filePath, String childName) throws DocumentException{
		Document document = getDocument(filePath);
		return getChild(document, childName);
	}
	
	/**
	 * 读取xmlStr, 获得根节点下所有子节点, 如果是有多级需要多次调用
	 * 
	 * @param xmlStr
	 * @throws DocumentException
	 */
	public static List<Element> getChildByXmlStr(String xmlStr) throws DocumentException{
		Document document = xmlStr2Document(xmlStr);
		return getChild(document);
	}
	
	/**
	 * 读取xmlStr, 获得根节点下childName节点
	 * 
	 * @param xmlStr
	 * @param childName
	 * @throws DocumentException
	 */
	public static List<Element> getChildByXmlStr(String xmlStr, String childName) throws DocumentException{
		Document document = xmlStr2Document(xmlStr);
		return getChild(document, childName);
	}
	
	
	/**
	 * 将xmlStr转成Map
	 * (需要注意这种是对应一个对象转成的xml 即一个根节点，多个没子节点的子节点)
	 * <book id="b001">
	 *     <title>Java 核心技术</title>
	 *     <price>98000</price>
	 * </book>
	 * 
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static TreeMap<String, String> xmlStrToMap(String xmlStr) throws DocumentException {
		TreeMap<String, String> map = new TreeMap<>();
		List<Element> list = getChildByXmlStr(xmlStr);
		for (Element element : list) {
			map.put(element.getName(), element.getTextTrim());
		}
		return map;
	}
	
	/**
	 * 将xmlStr转成Map
	 * (需要注意这种是对应一个对象转成的xml 即一个根节点，多个没子节点的子节点)
	 * <book id="b001">
	 *     <title>Java 核心技术</title>
	 *     <price>98000</price>
	 * </book>
	 *
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static TreeMap<String, String> xmlStrToMapHasAttr(String xmlStr) throws DocumentException {
		Document document = xmlStr2Document(xmlStr);
		// 获得根元素
		Element root = document.getRootElement();
		TreeMap<String, String> map = xmlStrToMapHasAttr(root);

		return map;
	}
	
	/**
	 * 将xmlStr转成Map
	 * (需要注意这种是对应一个对象转成的xml)
	 * 
	 * @param element
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static TreeMap<String, String> xmlStrToMapHasAttr(Element element) throws DocumentException {
		TreeMap<String, String> map = new TreeMap<>();
		//获得属性
		List<Attribute> attrList = element.attributes();
		for (Attribute attr : attrList) {
			map.put(attr.getName(), attr.getValue());
		}
		// 获得子节点
		List<Element> childList = element.elements();
		for (Element childEle : childList) {
			//有多个子元素时，放入map key重复了，导致只有最后一个
			map.put(childEle.getName(), childEle.getTextTrim());
		}
		
		return map;
	}

	
	/**
	 * 通过xpath 获得值
	 * 
	 * @param document
	 * @param xpath
	 * @return
	 */
	public static String getValueByXpath(Document document, String xpath){
		return document.selectSingleNode(xpath).getText();
	}
	
	/**
	 * 通过xpath 获得值
	 * <pre>
	 * xmlStr: "<books>javase的书籍清单</books>"
	 * getValueByXpath(xmlStr, "books"); 返回结果是 "javase的书籍清单"
	 * </pre>
	 * 
	 * @param xmlStr
	 * @param xpath
	 * @return
	 * @throws DocumentException
	 */
	public static String getValueByXpath(String xmlStr, String xpath) throws DocumentException{
		Document document = xmlStr2Document(xmlStr);
		return document.selectSingleNode(xpath).getText();
	}

}
