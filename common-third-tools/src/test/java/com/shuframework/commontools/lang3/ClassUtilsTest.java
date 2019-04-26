package com.shuframework.commontools.lang3;

import com.shuframework.commontools.codec.DigestUtil;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;

/**
 * @author shuheng
 */
public class ClassUtilsTest {

    @Test
    public void test() {
        Class clz = String.class;
        System.out.println(ClassUtils.getSimpleName(clz));
        //与getSimpleName 效果类似
//        System.out.println(ClassUtils.getShortClassName(clz));
        System.out.println(ClassUtils.getName(clz));
        System.out.println(ClassUtils.getPackageName(clz));

        System.out.println("======");
        System.out.println(clz.getSimpleName());
        System.out.println(clz.getName());
        System.out.println(clz.getCanonicalName());
        System.out.println(clz.getPackage().getName());
    }

    //数组
    @Test
    public void test2() {
        Class clz = String[].class;
        System.out.println(ClassUtils.getSimpleName(clz));
        //与getSimpleName 效果类似
//        System.out.println(ClassUtils.getShortClassName(clz));
        System.out.println(ClassUtils.getName(clz));
        System.out.println(ClassUtils.getCanonicalName(clz));
        System.out.println(ClassUtils.getPackageName(clz));

        System.out.println("======");
        System.out.println(clz.getSimpleName());
        System.out.println(clz.getName());//[Ljava.lang.String;
        System.out.println(clz.getCanonicalName());//java.lang.String[]
        System.out.println(clz.getPackage().getName()); //java.lang.NullPointerException
    }

    @Test
    public void class_test2() {
        Class clz = DigestUtil.class;
        System.out.println(ClassUtils.getSimpleName(clz));
//        //与getSimpleName 效果类似
//        System.out.println(ClassUtils.getShortClassName(clz));
        System.out.println(ClassUtils.getName(clz));
        System.out.println(ClassUtils.getPackageName(clz));

        System.out.println("======");
        System.out.println(clz.getSimpleName());
        System.out.println(clz.getName());
        System.out.println(clz.getPackage().getName());
    }

}