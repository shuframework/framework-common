package com.shuframework.commontools.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shuframework.commontools.BookInfoList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void jsonStr2Obj() {

//        BookInfoList<Map<String, Object>> list = new BookInfoList<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("a", 1);
//        list.setResult(map);
//
//        String jsonStr = JSON.toJSONString(list);
//        System.out.println(jsonStr);
        String jsonStr = "{\"result\":{\"a\":1}}";
        BookInfoList<Map<String, Object>> list2 = JSON.parseObject(jsonStr, BookInfoList.class);
        Map<String, Object> map2 = list2.getResult();
        System.out.println(map2);
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