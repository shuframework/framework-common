package com.shuframework.commontools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * APP 上下文件信息
 *
 * @author shuheng
 */
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取Spring 注入的bean
     *
     * @param clasz
     */
    public static <T> T getBean(Class<T> clasz) {
        return applicationContext.getBean(clasz);
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clasz) {
        return applicationContext.getBean(beanName, clasz);
    }

    /**
     * 按类型获取Spring注入的 所有 实例
     * @param clazz
     * @param <T>
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 获取Spring 容器上下文对象
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
    }

}
