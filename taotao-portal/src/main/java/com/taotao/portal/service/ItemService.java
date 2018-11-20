package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-20 下午 21:18
 */
public interface ItemService {

    ItemInfo getItemById(long itemId);
    String getItemDescById(long itemId);
    String getItemParam(long itemId);

}
