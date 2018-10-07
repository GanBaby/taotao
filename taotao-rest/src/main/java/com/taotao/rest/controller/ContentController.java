package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 内容管理Controller
 * @Date: 2018-10-1 上午 2:20
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCategoryId){
        try{
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return TaotaoResult.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }


}
