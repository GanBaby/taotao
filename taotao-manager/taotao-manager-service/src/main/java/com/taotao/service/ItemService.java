package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItem;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-13 下午 22:25
 */
public interface ItemService {

     /**
      * 根据id查询商品
      * @param itemId 商品的id
      * @return 返回获取的商品对象
      */
     TbItem getItemById(long itemId);

     /**
      * 商品列表查询
      * @param page 页数
      * @param rows 每页显示的数量
      * @return 返回获取的数据列表
      */
     EUDateGridResult getItemList(int page,int rows);

}
