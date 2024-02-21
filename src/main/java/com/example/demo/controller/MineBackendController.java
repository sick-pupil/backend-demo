package com.example.demo.controller;

import com.example.demo.bean.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @create 2023-12-26 17:16
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MineBackendController {

    @GetMapping("/abc")
    public void api() {
        log.info("api result {}", "abc");
    }
}
