package com.shuframework.tools.starter.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import java.util.List;
import java.util.TreeMap;

public class Dom4jUtilTest {

    String filePath = "D:/autotemp/books.xml";

    @Test
    public void document2XmlStr_test() throws DocumentException {
        Document document = Dom4jUtil.getDocument(filePath);
        String xmlStr = Dom4jUtil.document2XmlStr(document);
        System.out.println(xmlStr);
    }

    @Test
    public void getChild_test() throws DocumentException {
        Document document = Dom4jUtil.getDocument(filePath);
        List<Element> list = Dom4jUtil.getChild(document);
        System.out.println(list);
        TreeMap<String, String> map = new TreeMap<>();
        for (Element element : list) {
            List<Element> elements = element.elements();
            for (Element childEle : elements) {
                System.out.println("name:" + childEle.getName() + ", value:" + childEle.getText());
                //有多个子元素时，放入map key重复了，导致只有最后一个
                map.put(childEle.getName(), childEle.getText());
            }
        }
        System.out.println(map);
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void getChild_test2() throws DocumentException {
        Document document = Dom4jUtil.getDocument(filePath);
        List selectNodes = document.selectNodes("book");
        System.out.println(selectNodes);

        List<Element> list = Dom4jUtil.getChild(document, "book");
//		System.out.println("list-size:"+list.size());
        System.out.println(list);
    }


    @Test
    public void xpath_test3() throws DocumentException {
        Document document = Dom4jUtil.getDocument(filePath);
        List selectNodes = document.selectNodes("books");
        System.out.println(selectNodes);

        Node selectSingleNode = document.selectSingleNode("books");
        System.out.println(selectSingleNode.getText());
    }

    @Test
    public void test() throws DocumentException {
        List<Element> list = Dom4jUtil.getChildByFilePath(filePath);
        System.out.println("list-size:" + list.size());
        System.out.println(list);
    }

    @Test
    public void xmlStrToMap_test() throws DocumentException {
        Document document = Dom4jUtil.getDocument(filePath);
        String xmlStr = Dom4jUtil.document2XmlStr(document);
        System.out.println(xmlStr);
//		<book id="b001">
//			<title>Java 核心技术</title>
//			<price>98000</price>
//		</book>
        //转换成功,但是没拿到属性,只有下级节点的值 {price=98000, title=Java 核心技术}
        TreeMap<String, String> treeMap = Dom4jUtil.xmlStrToMap(xmlStr);
        System.out.println(treeMap);
        //这样的格式转换失败
//		<books>
//			<book id="b001">
//				<title>Java 核心技术</title>
//			 	<price>98000</price>
//			</book>
//			<book id="b002">
//				<title>Thinking in Java</title>
//			 	<price>22000</price>
//			</book>
//		</books>
    }

    @Test
    public void xmlStrToMapHasAttr_test() throws DocumentException {
        Document document = Dom4jUtil.getDocument(filePath);
        String xmlStr = Dom4jUtil.document2XmlStr(document);
		System.out.println(xmlStr);
//		<book id="b001">
//			<title>Java 核心技术</title>
//			<price>98000</price>
//		</book>
        //转换成功 {id=b001, price=98000, title=Java 核心技术}
        TreeMap<String, String> treeMap = Dom4jUtil.xmlStrToMapHasAttr(xmlStr);
        System.out.println(treeMap);
    }

}
