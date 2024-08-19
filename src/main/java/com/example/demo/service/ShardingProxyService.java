package com.example.demo.service;

import com.baomidou.dynamic.datasource.annotation.DS;

public interface ShardingProxyService {

    @DS("mysql-shardingproxy")
    void insertData();
}
