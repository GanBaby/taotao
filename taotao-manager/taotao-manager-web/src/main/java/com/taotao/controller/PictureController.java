package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: 甘银道
 * @Description: 上传图片处理
 * @Date: 2018-9-16 下午 21:40
 */

@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String picureUpload(MultipartFile uploadFile){
        Map map = pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把result转换成json格式的字符串
        String result = JsonUtils.objectToJson(map);
        return result;
    }

}