package com.example.demo.service.impl;

import com.example.demo.bean.TestDictTb;
import com.example.demo.bean.TestTb;
import com.example.demo.mapper.TestDictTbMapper;
import com.example.demo.mapper.TestTbMapper;
import com.example.demo.service.ShardingProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ShardingProxyServiceImpl implements ShardingProxyService {

    @Resource
    private TestDictTbMapper testDictTbMapper;

    @Resource
    private TestTbMapper testTbMapper;

    @Override
    public void insertData() {
        TestDictTb testDictTb = new TestDictTb();
        testDictTb.setDictKey("a");
        testDictTb.setDictType("b");
        testDictTb.setDictValue("c");

        TestTb testTb = new TestTb();
        testTb.setDbId(1L);
        testTb.setTbId(2L);

        testDictTbMapper.insert(testDictTb);
        testTbMapper.insert(testTb);
    }
}
