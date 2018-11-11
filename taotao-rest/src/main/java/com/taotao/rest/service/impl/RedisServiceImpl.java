package com.taotao.rest.service.impl;

import com.taotao.common.global.Global;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-11 下午 19:06
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaotaoResult syncContent(long contentCid) {

        try {
            jedisClient.hdel(Global.INDEX_CONTENT_REDIS_KEY,String.valueOf(contentCid));
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
