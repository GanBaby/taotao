package com.taotao.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 测试类
 * @Date: 2018-9-15 下午 18:08
 */
public class TestPageHelper {

    @Test
    public void testPageHelper(){
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从spring容器中获得Mapper的代理对象
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        //执行查询，并分页
        TbItemExample example = new TbItemExample();
        //分页处理，第一个参数是页数，第二个参数是每页显示的数据条数
        PageHelper.startPage(1, 10);
        //排序，数据库中必须要有price字段
        PageHelper.orderBy("price asc");
        List<TbItem> list = mapper.selectByExample(example);
        //取商品列表
        for(TbItem tbItem:list){
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("共有商品:"+total);



    }



}
