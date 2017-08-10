package com.taotao.manage.service.impl;

import com.taotao.manage.mapper.TestMapper;
import com.taotao.manage.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/8.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String queryCurrentDate() {
        return testMapper.queryCurrentDate();
    }
}
