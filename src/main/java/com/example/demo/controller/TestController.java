package com.example.demo.controller;

import com.example.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

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

    @GetMapping("/streamFilter")
    public void streamFilter() {
        List<String> intArr = new ArrayList<>();
        intArr.add("1");
        intArr.add("2");
        intArr.add("3");
        intArr.add("4");
        intArr.add("5");
        intArr.add("6");

        List<Integer> result = intArr.stream().map(Integer::valueOf).filter(n -> n % 2 == 0).collect(Collectors.toList());

        log.info(result.toString());
    }

    @GetMapping("/collectionUtilsUnion")
    public void collectionUtilsUnion() {
        List<Long> a = new ArrayList<>();
        a.add(1L);
        a.add(2L);
        List<Long> b = new ArrayList<>();
        b.add(3L);
        b.add(2L);
        log.info(CollectionUtils.union(a, b).toString());
    }

    @GetMapping("/collectionUtilsSubtract")
    public void collectionUtilsSubtract() {
        List<Long> a = new ArrayList<>();
        a.add(1L);
        a.add(2L);
        List<Long> b = new ArrayList<>();
        b.add(1L);
        b.add(2L);
        log.info(CollectionUtils.subtract(a, b).toString());
    }

    @GetMapping("/thread")
    public void thread() {
        new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
    }

    @GetMapping("/io")
    public void io() {
        try(InputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\ASUS\\Desktop\\doc-list"))) {
            log.info("");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
