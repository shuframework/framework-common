package com.shuframework.commontools;

import com.shuframework.commonbase.enums.NumberSystemEnum;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author shuheng
 */
public class BeanUtilTest {

    public final static int PACKET_TYPE = NumberSystemEnum.BINARY_2.getCode();
    public static final String BEAN_NAME_PREFIX = "in";

    @Test
    public void add() {
        String s = BEAN_NAME_PREFIX + PACKET_TYPE;
        System.out.println(s);
    }

    @Test
    public void beanToMap1() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(1);
        bookInfo.setName("aa");
        bookInfo.setPrice(Double.valueOf("0.3"));
        bookInfo.setPrice2(new BigDecimal("2"));
        bookInfo.setCreateTime(new Date());

//        Map map = BeanUtil.beanToMap(bookInfo);
        Map map = new HashMap();
        BeanUtils.copyProperties(bookInfo, map);
        System.out.println(map);
    }

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