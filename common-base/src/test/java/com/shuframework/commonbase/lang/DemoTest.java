package com.shuframework.commonbase.lang;

import com.shuframework.commonbase.util.lang.StringUtil;
import org.junit.Test;

/**
 * @author shuheng
 */
public class DemoTest {

    @Test
    public void test2() {
        String s = "6436201911201658305779055860";
        String y = s.substring(4, 8);
        String m = s.substring(8, 10);
        String d = s.substring(10, 12);
        String ymd = String.format("%s-%s-%s", y,m,d);
        System.out.println(ymd);
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
