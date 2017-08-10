package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
@RequestMapping("/item/cat")
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 分页查询商品类目列表
     * @param page 页号
     * @param rows 页大小
     * @return
     */
    @RequestMapping("/query/{page}")
    public ResponseEntity<List<ItemCat>> queryItemCatListByPage(@PathVariable("page") Integer page, @RequestParam(value = "rows", defaultValue = "10") Integer rows){
        try {
            //根据分页查询
            //List<ItemCat> list = itemCatService.queryItemCatListByPage(page, rows);
            List<ItemCat> list = itemCatService.queryListByPage(page, rows);
            //返回列表；返回的状态码为200
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回http响应状态500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     *  url:'/rest/item/cat',
     method:'GET',
     *  根据父类目id查询子类目
     *  @param parentId 父类目id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        try {
            //调用服务层对象查询数据
            ItemCat itemCat = new ItemCat();
            itemCat.setParentId(parentId);

            List<ItemCat> list = itemCatService.queryListByWhere(itemCat);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据类目id查询商品类目
     * @param itemCatId 商品类目id
     * @return
     */
    @RequestMapping(value = "/{itemCatId}", method = RequestMethod.GET)
    public ResponseEntity<ItemCat> queryItemCatById(@PathVariable("itemCatId") Long itemCatId) {

        try {
            // 根据类目id查询商品类目
            ItemCat itemCat = itemCatService.queryById(itemCatId);
            return ResponseEntity.ok(itemCat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
