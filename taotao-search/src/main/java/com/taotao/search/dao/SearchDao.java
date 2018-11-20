package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-19 下午 21:59
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;

}
