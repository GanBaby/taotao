package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: 甘银道
 * @Description: 商品的规格参数模板service实现类
 * @Date: 2018-9-19 下午 22:36
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Resource
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 获取商品规格分组
     * @param page 当前页数
     * @param rows 每页显示的数据
     * @return 返回获取的数据封装对象
     */
    public EUDateGridResult selectList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Map<String, String>> list = tbItemParamMapper.selectList();
        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);
        PageInfo<Map<String, String>> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if(list != null && list.size()>0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    public TaotaoResult insertItemParam(TbItemParam itemParam) {

        //补全pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入到规格参数模板表
        tbItemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }
}
