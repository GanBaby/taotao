package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;

/**
 * @Author: 甘银道
 * @Description: 内容管理Service
 * @Date: 2018-9-28 下午 23:45
 */
public interface ContentService {

    EUDateGridResult getContentList(Long categoryId,int page,int rows);

}
