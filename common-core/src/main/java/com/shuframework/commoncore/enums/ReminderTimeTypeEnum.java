package com.shuframework.commoncore.enums;


/**
 * 工作提醒时间类型
 *
 * @author shuheng
 */
public enum ReminderTimeTypeEnum {

    TODAY("1", "今日"),
    WEEK("2", "本周(周一 至 今天)"),
    MONTH("3", "本月(月初 至 今天)"),
    YEAR("4", "今年(年初 至 今天)"),
    ALL("5", "全部");

    private String code;
    private String name;

    ReminderTimeTypeEnum(String code, String name) {
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
