package solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-13 下午 18:01
 */
@Ignore
public class SolrJTest {

    @Test
    public void addDocument() throws Exception{
        //创建一连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.107:8080/solr");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test001");
        document.addField("item_title","测试商品2");
        document.addField("item_price",54321);
        //把文档对象写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    @Test
    public void deleteDocument()throws Exception {
        //创建-连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.107:8080/solr");
//        solrServer.deleteById("test001");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

    @Test
    public void queryDocument()throws Exception{
        //创建-连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.107:8080/solr");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("*:*");//查询全部
        query.setStart(20);//从第20条开始取
        query.setRows(50);//取50条
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("供查詢到記錄:"+solrDocumentList.getNumFound());
        for(SolrDocument solrDocument : solrDocumentList){
            System.out.println(solrDocument.get("id")+",");
            System.out.print(solrDocument.get("item_title")+",");
            System.out.print(solrDocument.get("item_price")+",");
            System.out.print(solrDocument.get("item_item_image"));

        }


    }


}
