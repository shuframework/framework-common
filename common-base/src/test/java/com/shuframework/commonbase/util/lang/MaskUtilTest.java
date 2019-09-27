package com.shuframework.commonbase.util.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaskUtilTest {

    @Test
    public void maskMobile() {
        String phone = "";
        MaskUtil.maskMobile(phone);
    }

    @Test
    public void maskEmail() {
        System.out.println(MaskUtil.maskMobile("15574307649"));
        System.out.println(MaskUtil.maskIdCardNo("362201199009191811"));
        System.out.println(MaskUtil.maskEmail("coupman@sina.com"));

    }

    @Test
    public void maskIdCardNo() {
        System.out.println(MaskUtil.maskIdCardNo("362201199009191811"));
    }

    @Test
    public void maskAddress() {
        System.out.println(MaskUtil.maskAddress("湖北省武汉市aa区xx街道"));
    }
}