package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;

/**
 * @author lhy
 * @create 2023-12-04 17:05
 * @description
 **/
@Configuration
public class DebeziumConfig {

    /**
    @Autowired
    private DebeziumPropertyConfig debeziumPropertyConfig;

    @Bean
    public io.debezium.config.Configuration customerConnector(Environment env) throws IOException {
        return io.debezium.config.Configuration.create()
                .with("name", debeziumPropertyConfig.getName())
                .with("connector.class", debeziumPropertyConfig.getConnectorClass())
                .with("offset.storage", debeziumPropertyConfig.getOffsetStorage())
                .with("offset.storage.file.filename", debeziumPropertyConfig.getOffsetStorageFileFilename())
                .with("offset.flush.interval.ms", debeziumPropertyConfig.getOffsetFlushIntervalMs())
                .with("database.hostname", debeziumPropertyConfig.getHost())
                .with("database.port", debeziumPropertyConfig.getPort())
                .with("database.user", debeziumPropertyConfig.getUsername())
                .with("database.password", debeziumPropertyConfig.getPassword())
                .with("database-include-list", debeziumPropertyConfig.getDatabaseIncludeList())
                .with("table-include-list", debeziumPropertyConfig.getTableIncludeList())
                .with("database.serverTimezone", debeziumPropertyConfig.getServerTimeZone())
                .with("include.schema.changes", debeziumPropertyConfig.getIncludeSchemaChanges())
                .with("database.allowPublicKeyRetrieval", debeziumPropertyConfig.getDatabaseAllowPublicKeyRetrieval())
//                .with("database.server.id", debeziumPropertyConfig.getDatabaseServerId())
                .with("database.server.name", debeziumPropertyConfig.getDatabaseServerName())
                .with("database.history", debeziumPropertyConfig.getDatabaseHistory())
                .with("database.history.file.filename", debeziumPropertyConfig.getDatabaseHistoryFileFilename())
                .build();
    }
    **/
}
