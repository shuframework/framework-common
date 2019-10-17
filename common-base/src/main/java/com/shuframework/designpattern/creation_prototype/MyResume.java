package com.shuframework.designpattern.creation_prototype;

import java.util.Date;

/**
 * 个人简历
 */
public class MyResume implements Cloneable {

    /** 姓名 */
    private String name;
//    /** 年龄 */
//    private String age;
    /** 出生日期 */
    private Date birthday;
    /** 性别 */
    private String sex;
    /** 工作年限 */
    private String timeArea;
    /** 公司 */
//    private String company;
    private MyCompany company;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 直接调用object对象的clone()方法！（浅克隆）
        // 深克隆 建议采用io流的对象读写来实现
//        return super.clone();
        //这样的克隆很麻烦，如果引用对象里面的引用对象修改了就要递归修改
        //一个方便的深克隆 可以采用流的读写来实现
        MyResume myResume = (MyResume) super.clone();
        myResume.setCompany((MyCompany) this.company.clone());
        return myResume;
    }

    public MyResume(String name, Date birthday, String sex) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
    }

    public void setWork(String timeArea, MyCompany company){
        this.timeArea = timeArea;
        this.company = company;
    }

    @Override
    public String toString() {
        return "MyResume{" +
                "name='" + name + '\'' +
//                ", age='" + age + '\'' +
                ", birthday='" + birthday.toString() + '\'' +
                ", sex='" + sex + '\'' +
                ", timeArea='" + timeArea + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTimeArea() {
        return timeArea;
    }

    public void setTimeArea(String timeArea) {
        this.timeArea = timeArea;
    }

    public MyCompany getCompany() {
        return company;
    }

    public void setCompany(MyCompany company) {
        this.company = company;
    }
}
