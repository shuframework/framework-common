package com.shuframework.commontools.net;

import com.shuframework.commonbase.result.Result;
import com.shuframework.commontools.json.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OkHttpClientUtilTest {

    private String basePath = "http://a1.easemob.com";
    public static String orgname = "1109190503107242";
    public static String appname = "bcim";
    public static Integer pagesize = 20;

    @Test
    public void get() {
        String groupId = "84128939704321";
        Integer pagenum = 1;
        String url = String.format("/%s/%s/chatgroups/%s/users?pagenum=%s&pagesize=%s",
                orgname, appname, groupId, pagenum, pagesize);
        String token = " Bearer YWMtGYnKKogJEem4h5-tP6F29AAAAAAAAAAAAAAAAAAAAAEdiYlAbiwR6ZQJ5Yg139DfAgMAAAFrKsc4VQBPGgBBKuJ3iufc_ddOjqfzMyj1fs6GQPZzJOR6SqG5lpUtjw";
        String s = OkHttpClientUtil.getInstance().get(basePath+url, token);
        System.out.println(s);
    }


    @Test
    public void post() {
        String groupId = "84128939704321";
        String url = String.format("/%s/%s/chatgroups/%s/users",
                orgname, appname, groupId);
        List<String> usernameList = new ArrayList<>();
        usernameList.add("0006052747");
        usernameList.add("0007052851");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("usernames", usernameList);

        String token = " Bearer YWMtGYnKKogJEem4h5-tP6F29AAAAAAAAAAAAAAAAAAAAAEdiYlAbiwR6ZQJ5Yg139DfAgMAAAFrKsc4VQBPGgBBKuJ3iufc_ddOjqfzMyj1fs6GQPZzJOR6SqG5lpUtjw";
        String s = OkHttpClientUtil.getInstance().post(basePath+url, token, JsonUtil.obj2JsonStr(paramMap));
        System.out.println(s);
    }
}