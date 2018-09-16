package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: 甘银道
 * @Description: 图片上传服务接口类
 * @Date: 2018-9-16 下午 20:56
 */
public interface PictureService {

    Map uploadPicture(MultipartFile uploadFile);

}
