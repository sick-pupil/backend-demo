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

    private String name;

    private String connectorClass;

    private String offsetStorage;

    private String offsetStorageFileFilename;

    private String offsetFlushIntervalMs;

    private String includeSchemaChanges;

    //private String datasource;

    private String tableWhitelist;

    private String host;

    private String port;

    private String username;

    private String password;

    private String serverTimeZone;

    private String databaseAllowPublicKeyRetrieval;

    private String databaseServerId;

    private String databaseServerName;

    private String databaseHistory;

    private String databaseHistoryFileFilename;
}
