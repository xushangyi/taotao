package com.taotao.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    /*@Override
    public List<ItemCat> queryItemCatListByPage(Integer page, Integer rows) {
        // 设置分页
        PageHelper.startPage(page, rows);
        // 查询
        List<ItemCat> list = itemCatMapper.selectAll();
        return list;
    }*/
}
