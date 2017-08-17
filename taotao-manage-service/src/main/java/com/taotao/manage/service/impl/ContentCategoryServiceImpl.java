package com.taotao.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.taotao.manage.mapper.ContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {
	
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public ContentCategory saveContentCategory(ContentCategory contentCategory) {
		//1、保存内容分类
		contentCategory.setSortOrder(100);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		saveSelective(contentCategory);
		
		//2、更新父节点的是否父节点的属性值为true
		ContentCategory parent = new ContentCategory();
		parent.setId(contentCategory.getParentId());
		parent.setIsParent(true);
		updateSelective(parent);
		
		return contentCategory;
	}

	@Override
	public void deleteContentCategory(ContentCategory contentCategory) {
		List<Long> ids = new ArrayList<>();
		ids.add(contentCategory.getId());
		//1、删除该节点及其子孙节点
		//1.1、获取该节点的所有子孙节点
		getCategoryIds(ids, contentCategory.getId());
		//1.2、删除该节点及其子孙节点
		deleteByIds(ids.toArray(new Long[]{}));
		
		//2、更新父节点
		//如果父节点还有其它子节点则不需要改动，如果父节点没有其它子节点了则需要将父节点更新为叶子节点（isParent = false）
		ContentCategory param = new ContentCategory();
		param.setParentId(contentCategory.getParentId());
		long count = queryCountByWhere(param);
		if(count == 0){//父节点已无其它子节点，需要更新为叶子节点
			ContentCategory parent = new ContentCategory();
			parent.setId(contentCategory.getParentId());
			parent.setIsParent(false);
			updateSelective(parent);
		}
	}

	/**
	 * 获取categoryId对应的所有子孙节点id，添加到ids集合中
	 * @param ids id集合
	 * @param categoryId 类目id
	 */
	private void getCategoryIds(List<Long> ids, Long categoryId) {
		ContentCategory param = new ContentCategory();
		param.setParentId(categoryId);
		List<ContentCategory> list = queryListByWhere(param);
		if(list != null && list.size() > 0){
			for (ContentCategory cc : list) {
				ids.add(cc.getId());
				getCategoryIds(ids, cc.getId());
			}
		}
	}

}
