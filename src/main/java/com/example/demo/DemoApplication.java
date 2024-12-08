package com.example.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.nio.file.Paths;

//dynamic-datasource生效需要如此设置
//@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//springsecurity禁用需要如此设置
//@EnableWebSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
//            Page page = browser.newPage();
//            page.navigate("https://playwright.dev/");
//            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
//        }
    }

}
