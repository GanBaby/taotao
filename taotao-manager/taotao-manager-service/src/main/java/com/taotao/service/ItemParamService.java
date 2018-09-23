package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * @Author: 甘银道
 * @Description: 商品的规格参数模板service
 * @Date: 2018-9-19 下午 22:34
 */
public interface ItemParamService {

    /**
     * 获取商品规格分组
     * @param page 当前页数
     * @param rows 每页显示的数据
     * @return 返回获取的数据封装对象
     */
    EUDateGridResult selectList(Integer page,Integer rows);

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam itemParam);


}
