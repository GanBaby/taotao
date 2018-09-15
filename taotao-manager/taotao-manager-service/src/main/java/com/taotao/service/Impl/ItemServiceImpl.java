package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 商品管理service
 * @Date: 2018-9-13 下午 23:06
 */
@Service(value="itemService")
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper itemMapper;


    /**
     * 根据id查询商品
     * @param itemId 商品的id
     * @return 返回获取的商品对象
     */
    public TbItem getItemById(long itemId) {
//        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        // 添加查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if(list != null && list.size()>0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 商品列表查询
     * @param page 页数
     * @param rows 每页显示的数量
     * @return 返回获取的数据列表
     */
    public EUDateGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);
        //去记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
