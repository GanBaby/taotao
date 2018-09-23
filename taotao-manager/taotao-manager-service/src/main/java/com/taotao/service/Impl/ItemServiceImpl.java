package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
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

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

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

    /**
     * 添加商品
     * @param item 添加的商品对象信息
     * @param desc 添加商品描述
     * @param itemParam 添加商品规格
     * @return 返回结果对象
     */
    public TaotaoResult createItem(TbItem item ,String desc, String itemParam)throws Exception {
        Date nowDate = new Date();
        //item补全
        //生成商品ID
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte)1);
        item.setCreated(nowDate);
        item.setUpdated(nowDate);
        //插入到数据库
        itemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId, desc);
        //添加规格参数
        if(result.getStatus() != 200){
            throw new Exception();
        }
        result = insertItemParamItem(itemId, itemParam);
        if(result.getStatus() != 200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    /**
     * 添加商品描述
     * @param desc 商品描述
     * @return
     */
    private TaotaoResult insertItemDesc(long itemId, String desc){
        Date nowDate = new Date();
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(nowDate);
        itemDesc.setUpdated(nowDate);
        tbItemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    /**
     * 添加规格参数
     * @param itemId
     * @param itemParam
     * @return
     */
    private TaotaoResult insertItemParamItem(Long itemId, String itemParam){
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        tbItemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();




    }

}
