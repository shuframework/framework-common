package com.shuframework.commontools.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shuframework.commontools.BookInfo;
import com.shuframework.commontools.BookInfoList;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void obj2JsonStr2() {
        String checkAppId = "wxcbd032341f7648c3,wx46fe2d54a90b9784,wxfe6747561243dd19,wx7944899c8f547e66";
        String appId = "wxfe6747561243dd19";
        Assert.isTrue(!(StringUtils.isBlank(appId) || checkAppId.contains(appId)), "微信支付暂不可用，请使用支付宝支付或在电脑网站上【www.xubei.com】进行微信充值");
    }

    @Test
    public void obj2JsonStr() {

        BookInfo bookInfo = new BookInfo();
        bookInfo.setName("aa");
        bookInfo.setPrice(3.56);
        bookInfo.setPrice2(new BigDecimal("5.93"));
        bookInfo.setCreateTime(new Date());
        String str = JsonUtil.obj2JsonStr(bookInfo);
        System.out.println(str);
    }

    @Test
    public void jsonStr2Obj_2() {

        RecommendListItemBean itemBean = new RecommendListItemBean();
        itemBean.setNickname("aa");

        List<RecommendListItemBean> itemList = new ArrayList<>();
        itemList.add(itemBean);

        RecommendListBean list = new RecommendListBean();
        list.setCount(1);
        list.setList(itemList);

        RecommendBean<RecommendListBean> recommendBean = new RecommendBean<>();
        recommendBean.setData(list);

        String jsonStr = JSON.toJSONString(recommendBean);
        System.out.println(jsonStr);
    }

    @Test
    public void test2() {
        String jsonString = "{\"data\":{\"count\":1,\"list\":[{\"age\":0,\"nickname\":\"aa\",\"sex\":0,\"uid\":0}]},\"status\":0}";
//        // java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.shuframework.commontools.json.RecommendListBean
//        RecommendBean<RecommendListBean> obj = JSON.parseObject(jsonString, RecommendBean.class);
        RecommendBean<RecommendListBean> obj = JSON.parseObject(jsonString, new TypeReference<RecommendBean<RecommendListBean>>(){});
        RecommendListBean recommendListBean = obj.getData();
        System.out.println(recommendListBean);
    }

    @Test
    public void test3() {
        String jsonString = "{\"data\":{\"count\":1,\"list\":[{\"age\":0,\"nickname\":\"aa\",\"sex\":0,\"uid\":0}]},\"status\":0}";
//        // java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.shuframework.commontools.json.RecommendListBean
//        RecommendBean<RecommendListBean> obj = JSON.parseObject(jsonString, RecommendBean.class);
        RecommendBean<RecommendListBean> obj = JsonUtil.jsonStr2Obj(jsonString, new TypeReference<RecommendBean<RecommendListBean>>(){});
        RecommendListBean recommendListBean = obj.getData();
        System.out.println(recommendListBean);
    }
}