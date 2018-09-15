package com.taotao.service.Impl;

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
}
