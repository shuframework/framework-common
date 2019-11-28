package com.shuframework.jdkdemo.testmodel;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 普通的pojo
 * 
 * @author shuheng
 *
 */
@Data
public class BookInfo {

	private Integer id;
	private String name;
	private Double price;
	private BigDecimal priceBig;
	private Date createTime;
	
	private BookInfo2 bookInfo2;


	public BookInfo() {
	}

	public BookInfo(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public BookInfo(Integer id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public BookInfo(Integer id, String name, Double price, BigDecimal priceBig) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.priceBig = priceBig;
	}

	public BookInfo(Integer id, String name, Date createTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}

	public BookInfo(Integer id, String name, Double price, Date createTime) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.createTime = createTime;
	}

//	@Override
//	public String toString() {
//		return "BookInfo [id=" + id + ", name=" + name + ", price=" + price + ", priceBig=" + priceBig
//				+ ", createTime=" + createTime + "]";
//	}

}
