package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-20 下午 13:08
 */
public interface SearchService {

    SearchResult search(String queryString,int page);

}
