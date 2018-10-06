package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 内容管理
 * @Date: 2018-10-1 上午 2:13
 */
public interface ContentService {

    List<TbContent> getContentList(long contentId);


}
