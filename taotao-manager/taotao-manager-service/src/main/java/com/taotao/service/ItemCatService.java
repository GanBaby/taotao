package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-9-15 下午 21:18
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);

}
