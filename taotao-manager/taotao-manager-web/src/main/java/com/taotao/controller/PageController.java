package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 甘银道
 * @Description: 页面跳转
 * @Date: 2018-9-15 下午 15:43
 */
@Controller
public class PageController {

    /**
     * 打开首页
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * 展示其他页面
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}
