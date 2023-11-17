package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//dynamic-datasource生效需要如此设置
//@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//springsecurity禁用需要如此设置
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
@EnableWebSecurity
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
