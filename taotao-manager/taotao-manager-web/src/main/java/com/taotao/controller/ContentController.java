package com.taotao.controller;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: 甘银道
 * @Description: 内容管理Controller
 * @Date: 2018-9-28 下午 23:41
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/query/list")
    @ResponseBody
    public EUDateGridResult getContentList(Long categoryId,int page,int rows){
        EUDateGridResult result = contentService.getContentList(categoryId, page, rows);
        return result;
    }


    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content){
        TaotaoResult result = contentService.insertContent(content);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(@RequestParam(value="ids") List<Long> ids){
        TaotaoResult result = contentService.deleteContent(ids);
        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult editContent(TbContent content){
        TaotaoResult result = contentService.editContent(content);
        return result;
    };

}
