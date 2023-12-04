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

    @Autowired
    private DebeziumPropertyConfig debeziumPropertyConfig;

    @Bean
    public io.debezium.config.Configuration customerConnector(Environment env) throws IOException {
        return io.debezium.config.Configuration.create()
                .with("name", "customer_mysql_connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "D:\\offsets.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", debeziumPropertyConfig.getHost())
                .with("database.port", debeziumPropertyConfig.getPort())
                .with("database.user", debeziumPropertyConfig.getUsername())
                .with("database.password", debeziumPropertyConfig.getPassword())
                .with("database.dbname", debeziumPropertyConfig.getDatasource())
                .with("database.include.list", debeziumPropertyConfig.getDatasource())
                .with("database.serverTimezone", debeziumPropertyConfig.getServertimezone())
                .with("include.schema.changes", "true")
                .with("database.allowPublicKeyRetrieval", "true")
                .with("database.server.id", "1")
                .with("database.server.name", "test-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "D:\\history_offsets.dat")
                .build();
    }
}
