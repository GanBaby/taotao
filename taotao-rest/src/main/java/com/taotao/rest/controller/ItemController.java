package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 甘银道
 * @Description: 商品信息Controller
 * @Date: 2018-11-20 下午 16:52
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 获取商品信息
     * @param itemId
     * @return
     */
    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public TaotaoResult getItemBaseInfo(@PathVariable long itemId){
        TaotaoResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }

    /**
     * 获取商品的描述
     * @param itemId
     * @return
     */
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDescInfo(@PathVariable long itemId){
        TaotaoResult result = itemService.getItemDesc(itemId);
        return result;
    }

    /**
     * 获取商品的规格
     * @param itemId
     * @return
     */
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable long itemId){
        TaotaoResult result = itemService.getItemParam(itemId);
        return result;
    }

}
