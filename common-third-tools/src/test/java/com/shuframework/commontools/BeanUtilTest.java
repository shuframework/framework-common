package com.shuframework.commontools;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author shuheng
 */
public class BeanUtilTest {

    @Test
    public void mapToBean() {
        Map<String, Object> map = new HashMap<>();
//        map.put("price", 0.3);
        map.put("price", Double.valueOf("0.3"));
//        map.put("price2", 0.43);//java.lang.Double cannot be cast to java.math.BigDecimal
//        map.put("price2", new BigDecimal("0.43"));
        map.put("createTime", new Date());

        BookInfo bookInfo = new BookInfo();
        BeanUtil.mapToBean(map, bookInfo);
        System.out.println(bookInfo);
    }
}