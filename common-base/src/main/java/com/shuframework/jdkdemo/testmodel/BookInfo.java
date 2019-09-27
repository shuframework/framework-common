package com.shuframework.jdkdemo.testmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 普通的pojo
 * 
 * @author shuheng
 *
 */
public class BookInfo {

	private Integer id;
	private String name;
	private Double price;
	private BigDecimal priceBig;
	private Date createTime;
	
	private BookInfo2 bookInfo2;
	public BookInfo2 getBookInfo2() {
		return bookInfo2;
	}

	public void setBookInfo2(BookInfo2 bookInfo2) {
		this.bookInfo2 = bookInfo2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BigDecimal getPriceBig() {
		return priceBig;
	}

	public void setPriceBig(BigDecimal priceBig) {
		this.priceBig = priceBig;
	}

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

	@Override
	public String toString() {
		return "BookInfo [id=" + id + ", name=" + name + ", price=" + price + ", priceBig=" + priceBig
				+ ", createTime=" + createTime + "]";
	}

}
