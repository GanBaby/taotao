package com.taotao.common.global;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-25 上午 10:09
 */
public interface OrderGlobal {

    //订单付款状态:1.未付款，2.已付款。3未发货。4已发货。5.交易成功。6交易关闭
    int NO_PAYMENT=1;
    int YES_PAYMENT=2;
    int NO_SHIPMENTS=3;
    int YES_SHIPMENTS=4;
    int DEAL_SUCCESSFULLY=5;
    int DEAL_CLOSED=6;

    //订单评价状态:0.未评价，1.已评价
    int NO_EVALUATION=0;
    int YES_EVALUATION=1;

}
