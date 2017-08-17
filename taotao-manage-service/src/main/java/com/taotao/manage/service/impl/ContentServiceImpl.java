package com.taotao.manage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;
import com.taotao.manage.service.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11.
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private ContentMapper contentMapper;
    @Value("${CONTENT_CATEGORY_BIG_AD_ID}")
    private Long CONTENT_CATEGORY_BIG_AD_ID;
    @Value("${TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER}")
    private Integer TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER;
    @Autowired
    private RedisService redisSevice;
    private static final String REDIS_BIG_AD_KEY="TAOTAO_PORTAL_BIG_AD_DATA";
    private static final int REDIS_BIG_AD_EXPIRE_TIME = 60*60*24;


    @Override
    public DataGridResult queryContentListByCategoryId(Long categoryId, Integer page, Integer rows) {
        Example example = new Example(Content.class);

        example.createCriteria().andEqualTo("categoryId", categoryId);

        //根据创建时间降序排序
        example.orderBy("updated").desc();

        //设置分页
        PageHelper.startPage(page, rows);
        //查询
        List<Content> list = contentMapper.selectByExample(example);
        //包装为分页信息对象
        PageInfo<Content> pageInfo = new PageInfo<>(list);
        //返回表格对象
        return new DataGridResult(pageInfo.getTotal(), pageInfo.getList());

    }

    @Override
    public String queryPortalBigAdData() throws Exception {
        // 先从缓存中查找数据

        try {
            String jsonStr = redisSevice.get(REDIS_BIG_AD_KEY);
            // 如果从缓存中找到数据则直接返回
            if (StringUtils.isNoneBlank(jsonStr)) {
                return jsonStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 查询大广告数据
        DataGridResult dataGridResult = queryContentListByCategoryId(CONTENT_CATEGORY_BIG_AD_ID, 1,TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER);
        List<Content> contentList = (List<Content>) dataGridResult.getRows();
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> map = null;
        if (contentList != null) {
            for (Content c : contentList) {
                map = new HashMap<>();
                map.put("alt", c.getTitle());
                map.put("height", 240);
                map.put("height", 240);
                map.put("href", c.getUrl());
                map.put("src", c.getPic());
                map.put("srcB", c.getPic());
                map.put("width", 670);
                map.put("widthB", 550);
                resultList.add(map);
            }
        }
        // 转化为字符串
        String resultJsonStr = MAPPER.writeValueAsString(resultList);
        try {
            // 将数据写入缓存，过期时间为1天
            redisSevice.setex(REDIS_BIG_AD_KEY,REDIS_BIG_AD_EXPIRE_TIME,resultJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultJsonStr;
    }

}
