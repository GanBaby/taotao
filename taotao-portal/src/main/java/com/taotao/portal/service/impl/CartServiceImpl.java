package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-22 下午 23:26
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    /**
     * 添加购物车商品
     * @param itemId 商品id
     * @param num 商品数量
     * @return 返回添加的结果
     */
    @Override
    public TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response,
                                    long itemId, int num) {
        //取商品信息
        CartItem cartItem = null;
        //取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        //判断购物车商品列表中是否存在此商品
        for(CartItem cItem:itemList){
            //如果存在商品
            if(cItem.getId()==itemId){
                //增加商品数量
                cItem.setNum(cItem.getNum()+num);
                cartItem=cItem;
                break;
            }
        }
        if(cartItem==null){
            cartItem = new CartItem();
            //根据商品id查询商品基本信息。
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            //把json转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
            if(taotaoResult.getStatus()==200){
                TbItem item = (TbItem)taotaoResult.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setImage(item.getImage()==null?"":item.getImage().split(",")[0]);
                cartItem.setNum(num);
                cartItem.setPrice(item.getPrice());
            }
            //添加到购物车列表
            itemList.add(cartItem);
        }
        //把购物车列表写入cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList),true);
        return TaotaoResult.ok();
    }

    /**
     * 从cookie中取商品列表
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request){
        //从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if(cartJson==null){
            return new ArrayList<>();
        }
        try {
            //把json转换成商品列表
            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ArrayList<>();
    }

    @Override
    public List<CartItem> getCartItem(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }

    /**
     * 商城购物车商品
     * @param itemId
     * @return
     */
    @Override
    public TaotaoResult delectCartItem(HttpServletRequest request, HttpServletResponse response,long itemId) {
        //从cookie中取商品列表
        List<CartItem> itemList = getCartItemList(request);
        //从列表中找到此商品
        for(CartItem cartItem : itemList){
            if(cartItem.getId()==itemId){
                itemList.remove(cartItem);
                break;
            }
        }
        //把购物车列表重新写入cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList));
        return TaotaoResult.ok();
    }
}
