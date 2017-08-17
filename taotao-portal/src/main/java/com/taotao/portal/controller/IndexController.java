package com.taotao.portal.controller;

import com.taotao.manage.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/8/11.
 */

@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        try {
            // 获取首页打广告数据
            mv.addObject("bigAdData", contentService.queryPortalBigAdData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

}
