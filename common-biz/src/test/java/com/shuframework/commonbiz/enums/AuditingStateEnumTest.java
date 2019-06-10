package com.shuframework.commonbiz.enums;

import org.junit.Test;

/**
 * @author shuheng
 */
public class AuditingStateEnumTest {

    @Test
    public void getNameByCode() {

        String name1 = AuditingStateEnum.getNameByCode("0");
        System.out.println(name1);

        String name2 = AuditingStateEnum.getNameByCode("1");
        System.out.println(name2);

        String name3 = AuditingStateEnum.getNameByCode("2");
        System.out.println(name3);
    }
}