package com.taotao.controller;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 甘银道
 * @Description: 内容管理Controller
 * @Date: 2018-9-28 下午 23:41
 */
@Controller
@RequestMapping("/content/query")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/list")
    @ResponseBody
    public EUDateGridResult getContentList(Long categoryId,int page,int rows){
        EUDateGridResult result = contentService.getContentList(categoryId, page, rows);
        return result;
    }


}
