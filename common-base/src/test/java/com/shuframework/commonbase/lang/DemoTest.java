package com.shuframework.commonbase.lang;

import com.shuframework.commonbase.util.lang.StringUtil;
import org.junit.Test;

/**
 * @author shuheng
 */
public class DemoTest {

    @Test
    public void test2() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test1() {
        //java.lang.NullPointerException
        method(null);
    }

    public void method(String param) {
        // 判断 param不为空
        if (StringUtil.isEmpty(param)) {
            System.out.println("参数不正确");
        }

        switch (param) {
            case "sth":
                System.out.println("it's sth");
                break;
            case "null":
                System.out.println("it's null");
                break;
            default:
                System.out.println("default");
        }
    }

}
