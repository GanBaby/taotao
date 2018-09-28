package com.taotao.service.Impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 甘银道
 * @Description: 内容分类管理
 * @Date: 2018-9-26 下午 23:06
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Resource
    private TbContentCategoryMapper contentCategoryMapper;

    public List<EUTreeNode> getCategoryList(long parentId) {
        //根据parentid查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");

            resultList.add(node);
        }
        return resultList;
    }

    public TaotaoResult insertContentCategory(long parentId, String name) {

        //创建一个pojo
        TbContentCategory content = new TbContentCategory();
        content.setName(name);
        content.setIsParent(false);
        //状态1是正常，2是删除
        content.setStatus(1);
        content.setSortOrder(1);
        content.setParentId(parentId);
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(content);
        //查看父节点的isParent列是否为true，如果不是true就改成true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return TaotaoResult.ok(content);
    }

    /**
     * 删除节点
     */
    public TaotaoResult deleteContentCategory(long id) {
        //改变父节点的状态
        TbContentCategory parentCat = contentCategoryMapper.selectParentByChildId(id);
        parentCat.setIsParent(false);
        contentCategoryMapper.updateByPrimaryKey(parentCat);
        deleteContentChild(id);
        return TaotaoResult.ok();
    }

    /**
     * 递归删除子节点
     */
    private void deleteContentChild(long id){
        contentCategoryMapper.deleteByPrimaryKey(id);
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            for(TbContentCategory contentCategory:list){
                deleteContentChild(contentCategory.getId());
            }
        }
    }

    /**
     * 修改
     * @param id
     * @param name
     * @return
     */
    public TaotaoResult updateContentCategory(long id, String name) {
        TaotaoResult result = new TaotaoResult();
        try{
            TbContentCategory category = new TbContentCategory();
            category.setId(id);
            category.setName(name);
            contentCategoryMapper.updateByPrimaryKeySelective(category);
            return result.ok();
        }catch(Exception e){
            return result.ok(e);
        }

    }
}
