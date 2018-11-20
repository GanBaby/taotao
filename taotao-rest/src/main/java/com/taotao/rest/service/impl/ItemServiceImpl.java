package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.pojo.*;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 商品信息管理service
 * @Date: 2018-11-20 下午 16:48
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper itemMapper;
    @Resource
    private TbItemDescMapper tbItemDescMapper;
    @Resource
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private int REDIS_ITEM_EXPIRE;

    /**
     * 获取商品信息
     * @param itemId 商品id
     * @return 返回获取的商品信息
     */
    public TaotaoResult getItemBaseInfo(long itemId) {
        try {
            //添加缓存逻辑

            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            //判断是否有值
            if(!StringUtils.isBlank(json)){
                //把json转换成java对象
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return TaotaoResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据商品id查询商品信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //使用TaotaoResult包装一下


        try {
            //把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base",JsonUtils.objectToJson(item));
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base",REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(item);
    }

    /**
     * 获取商品描述
     * @param itemId 商品id
     * @return 返回获取的商品描述
     */
    public TaotaoResult getItemDesc(long itemId) {

        try {
            //从缓存中取商品描述
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if(!StringUtils.isBlank(json)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        try {
            //添加缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc",JsonUtils.objectToJson(itemDesc));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc",REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(itemDesc);
    }

    /**
     * 获取商品规格
     * @param itemId 商品id
     * @return 返回获取的商品规格信息
     */
    @Override
    public TaotaoResult getItemParam(long itemId) {

        try {
            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if(!StringUtils.isBlank(json)){
                TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return TaotaoResult.ok(itemParamItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据商品id查询规格参数
        //设置查询条件
        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size()>0){
            TbItemParamItem paramItem = list.get(0);
            try {
                //添加缓存
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param",JsonUtils.objectToJson(paramItem));
                //设置过期时间
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param",REDIS_ITEM_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return TaotaoResult.ok(paramItem);
        }
        return TaotaoResult.build(400,"无此商品规格");
    }
}
