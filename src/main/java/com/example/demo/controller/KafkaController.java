package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @GetMapping("/asyncSend")
    public void asyncSend() {
        //配置
        Properties prop = new Properties();
        //连接地址
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.131:9090");
        //key value序列化
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //创建客户端对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(prop);

        for(int i = 0; i < 10; i++) {
            //回调函数异步发送
            kafkaProducer.send(new ProducerRecord<>("test-topic", "value-" + i, "value-" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null) {
                        System.out.println("主题: " + metadata.topic() + " 分区: " + metadata.partition());
                    }
                }
            });
        }

        kafkaProducer.close();
    }

    @GetMapping("/syncSend")
    public void syncSend() {
        //配置
        Properties prop = new Properties();
        //连接地址
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.131:9090");
        //key value序列化
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //创建客户端对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(prop);

        for(int i = 0; i < 10; i++) {
            //同步发布
            try {
                kafkaProducer.send(new ProducerRecord<>("topic-1", "value-" + i)).get();
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        kafkaProducer.close();
    }

    @GetMapping("/transactionalSend")
    public void transactionalSend() {
        //配置
        Properties prop = new Properties();
        //连接地址
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.131:9090");
        //key value序列化
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //幂等
        //幂等阻止单分区单会话数据重复，根据<pid, partition, seq>判断，pid为生产者会话id，partition为分区序号，seq为消息序号
        prop.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        //配置事务ID
        prop.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional_id_01");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(prop);
        kafkaProducer.initTransactions();
        kafkaProducer.beginTransaction();

        try {
            for(int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("first", String.valueOf(i)), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if(exception == null) {
                            System.out.println("主题: " + metadata.topic() + " 分区: " + metadata.partition());
                        }
                    }
                });
            }
            kafkaProducer.send(new ProducerRecord<>("first", String.valueOf(0)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null) {
                        System.out.println("主题: " + metadata.topic() + " 分区: " + metadata.partition());
                    }
                }
            });
            kafkaProducer.commitTransaction();
        } catch(Exception e) {
            kafkaProducer.abortTransaction();
        } finally {
            kafkaProducer.close();
        }
    }
}
