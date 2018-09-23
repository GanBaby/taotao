package com.taotao.controller;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 查询商品时获取商品列表对象
     * @param page 当前页
     * @param rows 每页显示的数量
     * @return 返回商品列表对象
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDateGridResult getItemList(Integer page,Integer rows){
        EUDateGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    /**
     * 添加商品
     * @param item 商品信息对象
     * @return 返回添加的结果信息对象
     */
    @RequestMapping(value="/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item, String desc, String itemParams)throws Exception{
        TaotaoResult result = itemService.createItem(item, desc, itemParams);
        return result;
    }

}
