package com.shuframework.tools.starter.xml;

import com.shuframework.commonbase.util.lang.StringUtil;
import com.shuframework.testmodel.BookInfo;
import com.thoughtworks.xstream.XStream;
import org.junit.Before;
import org.junit.Test;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bean2XmlUtilTest {

	private BookInfo book;
	private List<BookInfo> list;

	@Before
	public void init(){
		book = new BookInfo(1, "作者1", 50.0);
		BookInfo book2 = new BookInfo(1, "作者1", 50.0);

		list = new ArrayList<>();
		list.add(book);
		list.add(book2);
	}
	
	@Test
	public void toXmlStr_map() {
		String str = Bean2XmlUtil.toXmlStr(book);
		System.out.println(str);
		//节点别名默认是类名小写
//		<bookInfo>
//		  <id>1</id>
//		  <title>title1</title>
//		  <price>作者1</price>
//		</bookInfo>
	}

	@Test
	public void toXmlStr_map2() {
		String str = Bean2XmlUtil.toXmlStr(book, "book");
		System.out.println(str);
	}
//	//toXmlStr_test2的底层实现
//	@Test
//	public void toXml_test2() {
//		XStream xstream = new XStream();
//		//添加别名
//		xstream.alias("book", BookInfo.class);
//		String str = xstream.toXML(book);
//		System.out.println(str);
//	}

	@Test
	public void toXmlStr_list() {
		String str = Bean2XmlUtil.toXmlStr(list);
		System.out.println(str);
//		<list>
//		  <com.shuframework.testmodel.BookInfo>
//			<id>1</id>
//			<name>作者1</name>
//			<price>50.0</price>
//		  </com.shuframework.testmodel.BookInfo>
//		  <com.shuframework.testmodel.BookInfo>
//			<id>1</id>
//			<name>作者1</name>
//			<price>50.0</price>
//		  </com.shuframework.testmodel.BookInfo>
//		</list>
	}
	//别名没起作用
	@Test
	public void toXmlStr_list2() {
		String str = Bean2XmlUtil.toXmlStr(list, "book");
		System.out.println(str);
	}

	
	@Test
	public void toBean_test() {
		//2种效果是一样的
//		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<book><id>1</id><title>title1</title><price>作者1</price></book>";
		String xmlStr = "<book><id>1</id><title>title1</title><price>作者1</price></book>";
		XStream xstream = new XStream();
		//添加别名
		xstream.alias("book", Book.class);
		Book book = (Book) xstream.fromXML(xmlStr);
		System.out.println(book);
	}
	
	@Test
	public void toBean_test2() {
		String xmlStr = "<book>"+
				"<id>1</id>"+
				"<title>title1</title>"+
				"<price>作者1</price>"+
				"</book>";
		XStream xstream = new XStream();
		//添加别名
		xstream.alias("book", Book.class);
		Book book = (Book) xstream.fromXML(xmlStr);
		System.out.println(book);
	}
	
	@Test
	public void toBean_test3() {
		String xmlStr = "<book>"+
				"<id>1</id>"+
				"<title>title1</title>"+
				"<price>作者1</price>"+
				"</book>";
		XStream xstream = new XStream();
		//添加别名
		xstream.alias("book", Map.class);
		Map book = (Map) xstream.fromXML(xmlStr);
		System.out.println(book);
	}

}
