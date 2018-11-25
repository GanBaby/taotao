package com.taotao.order.service.impl;

import com.taotao.common.global.Global;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-25 上午 9:54
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private TbOrderMapper tbOrderMapper;
    @Resource
    private TbOrderItemMapper tbOrderItemMapper;
    @Resource
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;  
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        //向订单表中插入记录
        //获得订单号
        String str = jedisClient.get(ORDER_GEN_KEY);
        //判空设置初值
        if(StringUtils.isBlank(str)){
            jedisClient.set(ORDER_GEN_KEY,ORDER_INIT_ID);
        }
        //创建订单id
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        //补全pojo的属性
        order.setOrderId(String.valueOf(orderId));
        order.setStatus(Global.NO_PAYMENT);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setBuyerRate(Global.NO_EVALUATION);
        //向订单表插入数据
        tbOrderMapper.insert(order);
        //插入订单明细
        for (TbOrderItem orderItem:itemList){
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(String.valueOf(orderDetailId));
            orderItem.setOrderId(String.valueOf(orderId));
            //向订单明细插入记录
            tbOrderItemMapper.insert(orderItem);
        }
        //插入物流表
        //补全物流表的属性
        orderShipping.setOrderId(String.valueOf(orderId));
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        tbOrderShippingMapper.insert(orderShipping);
        return TaotaoResult.ok(orderId);
    }
}
