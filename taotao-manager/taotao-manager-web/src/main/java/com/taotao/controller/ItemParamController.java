package com.taotao.controller;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 甘银道
 * @Description: 商品规格参数模板管理Controller
 * @Date: 2018-9-19 下午 22:32
 */
@Controller
@RequestMapping(value = "item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping(value = "list")
    @ResponseBody
    public EUDateGridResult selectList(Integer page,Integer rows){
        EUDateGridResult result = itemParamService.selectList(page, rows);
        return result;
    }

    @RequestMapping(value = "/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
        TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insetItemParam(@PathVariable long cid,String paramData){
        //创建pojo对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        TaotaoResult result = itemParamService.insertItemParam(itemParam);
        return result;
    }


}
