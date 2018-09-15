package com.taotao.controller;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 甘银道
 * @Description: 商品Controller
 * @Date: 2018-9-13 下午 23:30
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据商品id查询商品信息
     * @param itemId 商品id
     * @return 返回获取的商品的json数据
     */
    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUDateGridResult getItemList(Integer page,Integer rows){
        EUDateGridResult result = itemService.getItemList(page, rows);
        return result;
    }

}
