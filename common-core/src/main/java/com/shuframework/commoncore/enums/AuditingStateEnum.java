package com.shuframework.commoncore.enums;


/**
 * 审核流程 类型
 *
 * @author shuheng
 */
public enum AuditingStateEnum {

    COMMIT("0", "提交/待审核"),
    DEALING("1", "处理中"),
    FAIL("2", "驳回/失败"),
    PASS("3", "通过"),
    REFUSE("4", "拒绝"),
    CANCEL("5", "取消");
    //FAIL 与 REFUSE的区别是 FAIL的记录可以进行修改后再次提交审核

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
