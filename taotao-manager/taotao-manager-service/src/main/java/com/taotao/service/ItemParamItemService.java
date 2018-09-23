package com.taotao.service;

/**
 * @Author: 甘银道
 * @Description: 商品规格参数service
 * @Date: 2018-9-23 下午 15:45
 */
public interface ItemParamItemService {

    /**
     * 根据商品id获取商品的规格参数列表
     * @param itemId 商品id
     * @return 获取的规格参数列表
     */
    String getItemParamByItemId(Long itemId);

}
