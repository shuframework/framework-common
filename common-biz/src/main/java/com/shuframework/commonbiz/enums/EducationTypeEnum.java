package com.shuframework.commonbiz.enums;


/**
 * 学历类型
 *
 * @author shuheng
 */
public enum EducationTypeEnum {

    PRIMARY_SCHOOL("1", "小学"),
    MIDDLE_SCHOOL("2", "初中"),
    HIGH_SCHOOL("3", "高中"),
    TECHNICAL_SECONDARY("4", "中专"),
    TECHNICAL_VOCATIONAL("5", "高职"),
    JUNIOR_COLLEGE("6", "大专"),
    UNIVERSITY("7", "大学");

    private String code;
    private String name;

    EducationTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return this.name;
    }

}
