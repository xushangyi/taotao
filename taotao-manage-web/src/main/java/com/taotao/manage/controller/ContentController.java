package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;

@RequestMapping("/content")
@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	//TODO 删除内容
	
	/**
	 * 更新内容
	 * @param content 内容
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Void> updateContent(Content content){
		try {
			//更新内容
			contentService.updateSelective(content);
			
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 新增内容
	 * @param content 内容
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveContent(Content content){
		try {
			//保存内容
			contentService.saveSelective(content);
			
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	
	/**
	 * 根据内容分类id查询该分类的内容列表
	 * @param categoryId 分类id
	 * @param page 页号
	 * @param rows 页大小
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<DataGridResult> queryContentListByPage(
			@RequestParam(value = "categoryId", defaultValue = "0")Long categoryId,
			@RequestParam(value = "page", defaultValue="1")Integer page,
			@RequestParam(value = "rows", defaultValue="20")Integer rows){
		try {
			//根据内容分类id查询该分类的内容列表
			DataGridResult dataGridResult = contentService.queryContentListByCategoryId(categoryId, page, rows);
			
			return ResponseEntity.ok(dataGridResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
