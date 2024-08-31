package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @GetMapping("/asyncProduct")
    public void asyncProduct() {
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

    @GetMapping("/syncProduct")
    public void syncProduct() {
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

    @GetMapping("/transactionalProduct")
    public void transactionalProduct() {
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

    @GetMapping("/consumeTopic")
    public void consumeTopic() {
        //配置
        Properties properties = new Properties();
        //连接bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "ip:port,ip:port");
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        //创建连接
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //订阅主题
        ArrayList<String> topics = new ArrayList<>();
        topics.add("test-topic");
        kafkaConsumer.subscribe(topics);
        //消费数据
        while(true) {
            //消费间隔时间为1秒
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                log.info("{}", consumerRecord);
            }
        }
    }

    @GetMapping("/consumePartition")
    public void consumePartition() {
        //配置
        Properties properties = new Properties();
        //连接bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "ip:port,ip:port");
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        //创建连接
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //订阅主题对应的分区
        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition("test-topic", 0));
        kafkaConsumer.assign(topicPartitions);
        //消费数据
        while(true) {
            //消费间隔时间为1秒
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                log.info("{}", consumerRecord);
            }
        }
    }

    @GetMapping("/commitOffset")
    public void commitOffset() {
        //配置
        Properties properties = new Properties();
        //连接bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "ip:port,ip:port");
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        //手动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        //创建连接
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //订阅主题对应的分区
        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition("test-topic", 0));
        kafkaConsumer.assign(topicPartitions);
        //消费数据
        while(true) {
            //消费间隔时间为1秒
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                log.info("{}", consumerRecord);
            }

            //同步提交offset
            kafkaConsumer.commitSync();
            //异步提交offset
            kafkaConsumer.commitAsync();
        }
    }
}
