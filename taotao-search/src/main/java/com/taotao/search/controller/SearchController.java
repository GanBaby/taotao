package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-19 下午 23:17
 */
@Controller
public class SearchController {

   @Autowired
   private SearchService searchService;

   @RequestMapping(value="/q",method=RequestMethod.GET)
   @ResponseBody
   public TaotaoResult search(@RequestParam(defaultValue="")String keyword,
                              @RequestParam(defaultValue="1")Integer page,
                              @RequestParam(defaultValue="30")Integer rows){
       //查询条件不能为空
       if(StringUtils.isBlank(keyword)){
           return TaotaoResult.build(400,"查询条件不能为空");
       }
       SearchResult searchResult = null;
       try {
           //转换字符集
           keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
           searchResult = searchService.search(keyword, page, rows);
       } catch (Exception e) {
           e.printStackTrace();
           return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
       }
       return TaotaoResult.ok(searchResult);
   }

}
