package com.shuframework.commontools.json;

import java.util.List;

public class RecommendListBean {

    private int count;
    private List<RecommendListItemBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RecommendListItemBean> getList() {
        return list;
    }

    public void setList(List<RecommendListItemBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RecommendListBean{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }
}