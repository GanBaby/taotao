package com.taotao.order.dao;

/**
 * @Author: 甘银道
 * @Description: jiedis接口
 * @Date: 2018-11-11 下午 17:26
 */
public interface JedisClient {

    String get(String key);
    String set(String key, String value);
    String hget(String hkey, String key);
    long hset(String hkey, String key, String value);
    long incr(String key);
    long expire(String key, int second);
    long ttl(String key);
    long del(String key);
    long hdel(String hkey, String key);
}