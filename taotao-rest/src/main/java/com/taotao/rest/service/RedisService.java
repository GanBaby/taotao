package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-11 下午 19:05
 */
public interface RedisService {

    TaotaoResult syncContent(long contentCid);

}
