package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-22 下午 23:14
 */
public interface CartService {
    TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response, long itemId, int num);
    List<CartItem> getCartItem(HttpServletRequest request, HttpServletResponse response);
    TaotaoResult delectCartItem(HttpServletRequest request, HttpServletResponse response,long itemId);

}
