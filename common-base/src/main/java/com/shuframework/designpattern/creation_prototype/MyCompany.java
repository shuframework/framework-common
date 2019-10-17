package com.shuframework.designpattern.creation_prototype;

import java.util.Date;

/**
 * @author shuheng
 */
public class MyCompany implements Cloneable {

    /** 名称 */
    private String name;

    /** 创办时间 */
    private Date createTime;

    /** 人数 */
    private Integer counts;

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

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("MyCompany{name=%s,createTime=%s,counts=%s}", name, createTime.toString(), counts);
    }
}
