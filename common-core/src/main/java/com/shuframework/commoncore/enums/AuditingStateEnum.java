package com.shuframework.commoncore.enums;


/**
 * 审核流程 类型
 *
 * @author shuheng
 */
public enum AuditingStateEnum {

    COMMIT("0", "提交/待审核"),
    DEALING("1", "处理中"),
    REBUT("2", "驳回（可修改）"),
    PASS("3", "通过"),
    REFUSE("4", "拒绝（重新提交）"),
    CANCEL("5", "取消");

    private String code;
    private String name;

    AuditingStateEnum(String code, String name) {
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
