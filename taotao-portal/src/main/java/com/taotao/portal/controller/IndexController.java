package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-24 下午 14:28
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        String adJson = contentService.getContentList();
        model.addAttribute("ad1",adJson);

        return "index";
    }

    /**
     * httpclient的post测试用的
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/httpclient/post",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult testPost(String username,String password){
        String result = "username:"+username+",password:"+password;
        System.out.println(result);
        return TaotaoResult.ok();
    }
    /**
     * httpclient的post测试用的
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/httpclient/post2",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String testPost2(String username,String password){
        String result = "{username:"+username+"\tpassword:"+password+"}";
        System.out.println(result);
        return result;
    }

}
