package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-20 下午 16:46
 */
public interface ItemService{

    /**
     * 获取商品信息
     * @param itemId 商品id
     * @return 返回获取的商品信息
     */
    TaotaoResult getItemBaseInfo(long itemId);

    /**
     * 获取商品描述
     * @param itemId 商品id
     * @return 返回获取的商品描述
     */
    TaotaoResult getItemDesc(long itemId);

    /**
     * 获取商品规格
     * @param itemId 商品id
     * @return 返回获取的商品规格信息
     */
    TaotaoResult getItemParam(long itemId);

}
