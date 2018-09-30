package com.taotao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 内容管理
 * @Date: 2018-9-28 下午 23:47
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper tbContentMapper;

    public EUDateGridResult getContentList(Long categoryId, int page, int rows) {

        //根据categoryId查询数据
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //分页管理
        PageHelper.startPage(page,rows);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        EUDateGridResult result = new EUDateGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    public TaotaoResult insertContent(TbContent content) {
        //补全content
        content.setCreated(new Date());
        content.setUpdated(new Date());
        tbContentMapper.insert(content);
        return TaotaoResult.ok();
    }

    public TaotaoResult deleteContent(List<Long> ids) {
        if(ids != null && ids.size()>0){
            tbContentMapper.deleteByIdBath(ids);
        }

        return TaotaoResult.ok();
    }

    public TaotaoResult editContent(TbContent content) {
        tbContentMapper.updateByPrimaryKeyWithBLOBs(content);
        return TaotaoResult.ok();
    }
}
