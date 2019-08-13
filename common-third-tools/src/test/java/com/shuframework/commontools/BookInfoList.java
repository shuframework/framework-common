package com.shuframework.commontools;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 普通的pojo
 * 
 * @author shuheng
 *
 */
public class BookInfoList<T> {

	private Integer id;
	private String name;
	private Double price;
	private BigDecimal price2;
	private Date createTime;
	private T result;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
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

	public BigDecimal getPrice2() {
		return price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

	public BookInfoList() {
	}

	public BookInfoList(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public BookInfoList(Integer id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public BookInfoList(Integer id, String name, Date createTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
//		return "BookInfo [id=" + id + ", name=" + name + ", price=" + price + ", price2=" + price2 + ", createTime=" + createTime + "]";
		return String.format("BookInfo [id=%s, name=%s, price=%s, price2=%s, createTime=%s]",
				id, name, price, price2, createTime);
	}

}
