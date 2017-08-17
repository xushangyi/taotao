package com.taotao.manage.service;


import com.taotao.manage.pojo.ContentCategory;

public interface ContentCategoryService extends BaseService<ContentCategory>{
	/**
	 * 保存内容分类
	 * @param contentCategory
	 * @return
	 */
	ContentCategory saveContentCategory(ContentCategory contentCategory);
	
	/**
	 * 删除内容分类及其子分类
	 * @param contentCategory
	 */
	void deleteContentCategory(ContentCategory contentCategory);
	
}
