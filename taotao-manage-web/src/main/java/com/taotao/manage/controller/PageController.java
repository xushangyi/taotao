package com.taotao.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/9.
 */
@RequestMapping("/page")
@Controller
public class PageController {

    /**
     * 跳转到页面：如：/rest/page/index
     * @param pageName 页面名称（在WEB-INF/views/页面名称.jsp）
     * @return
     */
    @RequestMapping("/{pageName}")
    public String toPage(@PathVariable("pageName")String pageName) {
        return pageName;
    }
}
