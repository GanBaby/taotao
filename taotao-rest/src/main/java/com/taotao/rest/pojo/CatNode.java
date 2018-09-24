package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-24 下午 22:01
 */
public class CatNode {

    @JsonProperty("n")
    private String name;
    @JsonProperty("u")
    private String url;
    @JsonProperty("i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
