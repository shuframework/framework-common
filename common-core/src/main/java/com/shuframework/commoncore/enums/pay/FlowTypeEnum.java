package com.shuframework.commoncore.enums.pay;


/**
 * 流水类型
 *
 * @author shuheng
 */
public enum FlowTypeEnum {

    INCOME("1", "进账/收入"),
    EXPEND("2", "出账/支出");
//    //冻结 解冻是其他状态， 这个状态可能放入其他位置
//    FREEZE("冻结", "3"),
//    UNFREEZE("解冻", "4");

    private String code;
    private String name;

    FlowTypeEnum(String code, String name) {
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
