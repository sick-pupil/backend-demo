package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhy
 * @create 2023-12-04 17:07
 * @description
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "debezium")
public class DebeziumPropertyConfig {

    private String datasource;

    private String host;

    private String port;

    private String username;

    private String password;

    private String tablename;

    private String servertimezone;
}
