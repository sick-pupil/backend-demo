package com.example.demo.service;

import org.springframework.transaction.annotation.Transactional;

//@Transactional(rollbackFor = Exception.class)
public interface ShardingProxyService {

    void migrateData() throws Exception;
}
