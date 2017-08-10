package com.taotao.manage.service;

import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.pojo.Item;

/**
 * Created by Administrator on 2017/8/10.
 */
public interface ItemService extends BaseService<Item> {
    void saveItem(Item item, String desc);

    void updateItem(Item item, String desc);

    DataGridResult queryItemList(String title, Integer page, Integer rows);
}
