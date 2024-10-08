package com.example.demo.controller;

import com.example.demo.service.ShardingProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/shardingproxy")
public class ShardingProxyController {

    @Autowired
    private ShardingProxyService shardingProxyService;

    @GetMapping("/migrateData")
    public void migrateData() {
        try {
            shardingProxyService.migrateData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
