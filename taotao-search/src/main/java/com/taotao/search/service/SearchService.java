package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-19 下午 22:49
 */
public interface SearchService {

    SearchResult search(String queryString,int page,int rows)throws Exception;

}
