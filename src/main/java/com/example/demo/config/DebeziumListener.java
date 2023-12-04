package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;


/**
 * @author lhy
 * @create 2023-12-04 17:12
 * @description
 **/
@Slf4j
@Component
public class DebeziumListener {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    public DebeziumListener(Configuration customerConnectorConfiguration) {
        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(customerConnectorConfiguration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();
        log.info("Key = {}, Value = {}", sourceRecord.key(), sourceRecord.value());
        Struct sourceRecordChangeValue= (Struct) sourceRecord.value();
        log.info("SourceRecordChangeValue = '{}'", sourceRecordChangeValue);
    }

    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (Objects.nonNull(this.debeziumEngine)) {
            this.debeziumEngine.close();
        }
    }
}
