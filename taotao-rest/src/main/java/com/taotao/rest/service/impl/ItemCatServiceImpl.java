package com.taotao.rest.service.impl;

import com.taotao.common.global.Global;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-24 下午 22:14
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private JedisClient jedisClient;


    public CatResult getItemCatList() {

        //redis缓存中查询商品分类
        try{
           String resultItemCat = jedisClient.hget(Global.INDEX_CONTENT_REDIS_KEY,Global.ITEM_CAT);
           if(!StringUtils.isBlank(resultItemCat)){
               CatResult result = JsonUtils.jsonToPojo(resultItemCat, CatResult.class);
               return result;
           }
        }catch(Exception e){

        }

        CatResult catResult = new CatResult();
        //查询分类列表
        catResult.setData(getCatList(0));

        //将分类信息添加进缓存
        try{
            String catResultJson =  JsonUtils.objectToJson(catResult);
            jedisClient.hset(Global.INDEX_CONTENT_REDIS_KEY,Global.ITEM_CAT,catResultJson);
        }catch(Exception e){
            e.printStackTrace();
        }

        return catResult;
    }

    /**
     * 查询分类列表
     * @param parentId 父级分类id
     * @return 返回获取的结果
     */
    private List<?> getCatList(long parentId){

        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        int count = 0;
        for(TbItemCat tbItemCat : list){
            //判断是否为父节点
            if(tbItemCat.getIsParent()){
                CatNode catNode = new CatNode();
                if(parentId == 0){
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else{
                    catNode.setName(tbItemCat.getName());

                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);
                count++;
                //第一层只取14条记录
                if(parentId == 0 && count >=14){
                    break;
                }
            }else{
                resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }
        return resultList;
    }

}
