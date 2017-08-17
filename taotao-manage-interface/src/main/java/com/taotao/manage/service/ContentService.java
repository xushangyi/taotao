package com.taotao.manage.service;

import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.pojo.Content;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface ContentService extends BaseService<Content> {
    DataGridResult queryContentListByCategoryId(Long categoryId, Integer page, Integer rows);

    /**
     * 获取门户系统首页的大广告数据
     * @return
     * @throws Exception
     */
    String queryPortalBigAdData() throws Exception;
}
