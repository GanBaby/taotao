package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-22 下午 18:00
 */
public interface UserService {

    TbUser getUserByToken(String token);

}
