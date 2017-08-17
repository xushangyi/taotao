package com.taotao.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.remoting.exchange.Request;
import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;

@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 删除内容分类节点
	 * @param contentCategory 内容分类
	 * @return
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteContentCategory(ContentCategory contentCategory){
		try {
			contentCategoryService.deleteContentCategory(contentCategory);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 重命名内容分类节点
	 * @param contentCategory 内容分类
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateContentCategory(ContentCategory contentCategory){
		try {
			contentCategoryService.updateSelective(contentCategory);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 新增内容分类节点
	 * @param contentCategory 内容分类
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory){
		try {
			ContentCategory result = contentCategoryService.saveContentCategory(contentCategory);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 根据节点查询该节点下的所有子节点
	 * @param parentId 节点id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryContentCategoryListByParentId(
			@RequestParam(value = "id", defaultValue = "0")Long parentId){
		try {
			//根据父id查询其所有子节点
			ContentCategory contentCategory = new ContentCategory();
			contentCategory.setParentId(parentId);
			
			List<ContentCategory> list = contentCategoryService.queryListByWhere(contentCategory);
			
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
