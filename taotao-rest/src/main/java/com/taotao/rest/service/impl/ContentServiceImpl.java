package com.taotao.rest.service.impl;

import com.taotao.common.global.Global;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 内容管理
 * @Date: 2018-10-1 上午 2:14
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;

    public List<TbContent> getContentList(long contentId) {

        //从缓存中取内容
        try{
            String result = jedisClient.hget(Global.INDEX_CONTENT_REDIS_KEY,String.valueOf(contentId));
            if(!StringUtils.isBlank(result)){
                //把字符串转换成list
                List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
                return resultList;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentId);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);

        //想缓存中添加内容
        try{
            //把list转换成字符串
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(Global.INDEX_CONTENT_REDIS_KEY,String.valueOf(contentId),cacheString);
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
