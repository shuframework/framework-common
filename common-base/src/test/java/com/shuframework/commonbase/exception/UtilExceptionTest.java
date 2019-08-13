package com.shuframework.commonbase.exception;

import org.junit.Test;

public class UtilExceptionTest {

    @Test
    public void test1() {
        try {
        test_exception();
        }catch (UtilException e) {
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("出错");
        }
    }

    public void test_exception() {
        try {
            int i = 1/0;
        }catch (Exception e) {
            throw new UtilException("算术异常："+e.getMessage());
        }

    }

}