package com.example.demo.controller;

import com.example.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @create 2023-11-10 10:11
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/log")
    public void log() {
        log.trace("a");
        log.info("b");
        log.warn("c");
        log.debug("d");
        log.error("e");
    }

    @GetMapping("/securityEncode")
    public void securityEncode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeStr = passwordEncoder.encode("123");
        log.info(encodeStr);
    }
}
