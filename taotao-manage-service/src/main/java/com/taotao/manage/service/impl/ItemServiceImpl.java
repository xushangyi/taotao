package com.taotao.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService{
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public void saveItem(Item item, String desc) {
        // 保存商品基本信息
        saveSelective(item);

        // 保存商品描述信息
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setCreated(new Date());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(itemDesc.getCreated());
        itemDescMapper.insertSelective(itemDesc);
    }

    @Override
    public void updateItem(Item item, String desc) {
        // 更新商品基本信息
        updateSelective(item);
        // 更新商品描述信息
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
    }

    @Override
    public DataGridResult queryItemList(String title, Integer page, Integer rows) {

        Example example = new Example(Item.class);
        try {
            if (StringUtils.isNotBlank(title)) {
                // 添加查询条件
                Example.Criteria criteria = example.createCriteria();
                // 解码
                title = URLDecoder.decode(title, "utf-8");
                criteria.andLike("title", "%" + title + "%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 根据更新时间降序排序
        example.orderBy("updated").desc();
        // 设置分页信息
        PageHelper.startPage(page, rows);
        // 执行查询
        List<Item> list = itemMapper.selectByExample(example);
        // 转换为分页信息对象
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        // 返回DataGridResult
        return new DataGridResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
