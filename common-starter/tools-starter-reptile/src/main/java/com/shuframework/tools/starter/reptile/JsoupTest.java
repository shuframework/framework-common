package com.shuframework.tools.starter.reptile;

import com.shuframework.commontools.json.JsonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author shuheng
 */
public class JsoupTest {


    @Test
    public void jijin_test() {
//        Rule rule = new Rule("http://fund.eastmoney.com/f10/jjjz_519961.html", "w782 comm lsjz",Rule.CLASS, Rule.GET);
        Rule rule = new Rule("http://fund.eastmoney.com/f10/jjjz_519961.html", "lsjzTable",Rule.ID, Rule.GET);
//        List<LinkTypeData> extracts = ExtractService.extract(rule);
//        printf(extracts);
        Elements elements = ExtractService.getElements(rule);

        String resultStr = elements.get(0).data().toString().trim();
        String[] tempArr = resultStr.split("&& ");
        String listStr = tempArr[1].substring(0, 13);
        System.out.println("listStr:"+listStr);
        List<Map> maps = JsonUtil.jsonStr2List(listStr);
        System.out.println(maps);

//        System.out.println("listStr0:"+tempArr[0]);
//        System.out.println("listStr1:"+tempArr[1]);
//        System.out.println("listStr2:"+tempArr[2]);
//        for (Element e : elements){
//            System.out.println(e);
////            String[] vars = e.data().toString().split("Data");
////            System.out.println(Arrays.toString(vars));
////            for (String s : vars){
////                if ("")
////            }
//        }
//        System.out.println(elements);
    }

    @Test
    public void getDatasByCssQueryUserBaidu() {
        Rule rule = new Rule("http://news.baidu.com/ns", new String[] { "wd" }, new String[] { "云平台建设方案" }, null, -1,
                Rule.GET);
        List<LinkTypeData> extracts = ExtractService.extract(rule);
        System.out.println(extracts);
    }

    public void printf(List<LinkTypeData> datas) {
        for (LinkTypeData data : datas) {
            System.out.println(data);
            // System.out.println(data.getLinkText());
            // System.out.println(data.getLinkHref());
            System.out.println("***********************************");
        }

    }
}