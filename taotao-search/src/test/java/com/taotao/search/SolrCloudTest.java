package com.taotao.search;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-28 下午 23:30
 */
@Ignore
public class SolrCloudTest {

    @Test
    public void testAddDocument()throws Exception{
        //创建一个和solr集群的连接
        //参数就是zookeeper的地址列表，使用逗号分隔
        String zkHost = "192.168.0.108:2181,192.168.0.108:2182,192.168.0.108:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);
        //设置默认的collection
        solrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域
        document.addField("id","test001");
        document.addField("item_title","测试商品");
        //把文档添加到索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    @Test
    public void deleteDocument()throws Exception{
        //创建一个和solr集群的连接
        //参数就是zookeeper的地址列表，使用逗号分隔
        String zkHost = "192.168.0.108:2181,192.168.0.108:2182,192.168.0.108:2183";
        CloudSolrServer solrServer = new CloudSolrServer(zkHost);
        //设置默认的collection
        solrServer.setDefaultCollection("collection2");

        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

}
