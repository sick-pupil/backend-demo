package com.example.demo.controller;

import com.example.demo.bean.UserInfo;
import com.example.demo.service.DynamicDsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @create 2023-11-07 13:59
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/dynamicDs")
@RequiredArgsConstructor
public class DynamicDsController {

    private final DynamicDsService dynamicDsService;

    @GetMapping("/selectMysqlDs")
    public void selectMysqlDs() {
        UserInfo userInfo = dynamicDsService.selectUserInfoFromMysql();
        log.info("mysql result {}", userInfo.toString());
    }

    @GetMapping("/selectPostgresDs")
    public void selectPostgresDs() {
        UserInfo userInfo = dynamicDsService.selectUserInfoFromPostgres();
        log.info("postgresql result {}", userInfo.toString());
    }
}
