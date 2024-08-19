package com.example.demo.controller;

import com.example.demo.service.RabbitMQService;
import com.rabbitmq.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lhy
 * @create 2024-03-12 12:01
 * @description
 **/
//@Slf4j
//@RestController
//@RequestMapping("/rabbitmq")
//@RequiredArgsConstructor
//public class RabbitMQController {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    private final RabbitMQService rabbitMQService;
//
//    private final static String QUEUE_NAME = "test";
//
//    //扇出交换机
//    private final static String FANOUT_EXCHANGE_NAME = "fanout-exchange";
//    private final static String FANOUT_QUEUE1_NAME = "fanout-queue1";
//    private final static String FANOUT_QUEUE2_NAME = "fanout-queue2";
//    private final static String FANOUT_ROUTING_KEY = "fanout-routing-key";
//
//    //死信队列
//    private final static String NORMAL_QUEUE_NAME = "normal-queue";
//    private final static String NORMAL_EXCHANGE_NAME = "normal-exchange";
//    private final static String DEAD_QUEUE_NAME = "dead-queue";
//    private final static String DEAD_EXCHANGE_NAME = "dead-exchange";
//    private final static String NORMAL_EXCHANGE_ROUTING_KEY = "normal-exchange-routing-key";
//    private final static String DEAD_EXCHANGE_ROUTING_KEY = "dead-exchange-routing-key";
//
//    @GetMapping("/test")
//    public void test() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare("test-exchange", BuiltinExchangeType.FANOUT);
//        channel.queueDeclare("test-queue1", true, false, false, null);
//        channel.queueDeclare("test-queue2", true, false, false, null);
//        channel.queueBind("test-queue1", "test-exchange", "");
//        channel.queueBind("test-queue2", "test-exchange", "");
//    }
//
//    @GetMapping("/simpleModeProduce")
//    public void simpleModeProduce() throws Exception {
//        //创建连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        /**
//         * 声明一个队列
//         * 1. 队列名称
//         * 2. 队列里的消息是否持久化 默认存储在内存中
//         * 3. 队列是否只供一个消费者进行消费 是否共享给多个消费者消费
//         * 4. 是否在最后一个消费者断开连接后队列自动删除
//         * 5. 其他参数
//         */
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//
//        channel.confirmSelect();
//        //监听消息确认成功的回调
//        ConfirmCallback ackCallback = (deliveryTag, ackSignal) -> {
//            log.info("消息发布确认成功");
//            if(ackSignal) {
//                log.info("发布确认成功");
//            }
//        };
//        //监听消息确认失败的回调
//        ConfirmCallback nackCallback = (deliveryTag, ackSignal) -> {
//            log.info("消息发布确认失败");
//            if(ackSignal) {
//                log.info("发布确认失败");
//            }
//        };
//        channel.addConfirmListener(ackCallback, nackCallback);
//
//        /**
//         * 发送一个消息到队列
//         * 1. 发送到哪个交换机
//         * 2. 路由key，路由到哪个队列
//         * 3. 其他参数
//         * 4. 发送消息的消息体
//         */
//        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, getRandomString(10).getBytes());
//        /**
//         boolean publishResult = channel.waitForConfirms();
//         if(publishResult) {
//         log.info("消息持久化完毕");
//         }
//         **/
//        log.info("消息发送完毕");
//    }
//
//
//    @GetMapping("/simpleModeConsume")
//    public void simpleModeConsume() throws Exception {
//        //创建连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
////        channel.basicQos(5);
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            log.info(consumerTag);
//            log.info(new String(message.getBody()));
//            log.info("消费成功");
//            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
//
//            log.info(message.getProperties().toString());
////                channel.basicNack(message.getEnvelope().getDeliveryTag(), false, true);
//        };
//
//        CancelCallback cancelCallback = consumerTag -> {
//            log.info(consumerTag);
//            log.info("消费被中断");
//        };
//
//        /**
//         * 1. 消费哪个队列
//         * 2. 消费成功后是否需要自动应答
//         * 3. 消息发送过来的回调
//         * 4. 被取消消费的回调
//         */
//        channel.basicConsume(QUEUE_NAME,false, deliverCallback, cancelCallback);
//    }
//
//    //length用户要求产生字符串的长度
//    private String getRandomString(int length){
//        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        Random random = new Random();
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0;i < length; i++){
//            int number = random.nextInt(62);
//            sb.append(str.charAt(number));
//        }
//        return sb.toString();
//    }
//
//    @GetMapping("/fanoutProduce")
//    public void fanoutProduce() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
//        channel.queueDeclare(FANOUT_QUEUE1_NAME, true, false, false, null);
//        channel.queueDeclare(FANOUT_QUEUE2_NAME, true, false, false, null);
//        channel.queueBind(FANOUT_QUEUE1_NAME, FANOUT_EXCHANGE_NAME, FANOUT_ROUTING_KEY);
//        channel.queueBind(FANOUT_QUEUE2_NAME, FANOUT_EXCHANGE_NAME, FANOUT_ROUTING_KEY);
//
//        channel.basicPublish(FANOUT_EXCHANGE_NAME, FANOUT_ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, getRandomString(10).getBytes());
//    }
//
//    @GetMapping("/fanoutConsume")
//    public void fanoutConsume() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            log.info(consumerTag);
//            log.info(new String(message.getBody()));
//            log.info("消费成功");
//        };
//
//        CancelCallback cancelCallback = consumerTag -> {
//            log.info(consumerTag);
//            log.info("消费被中断");
//        };
//
//        channel.basicConsume(FANOUT_QUEUE1_NAME, true, deliverCallback, cancelCallback);
//        channel.basicConsume(FANOUT_QUEUE2_NAME, true, deliverCallback, cancelCallback);
//    }
//
//    @GetMapping("/directProduce")
//    public void directProduce() {
//
//    }
//
//    @GetMapping("/directConsume")
//    public void directConsume() {
//
//    }
//
//    @GetMapping("/deadLetterConfig")
//    public void deadLetterConfig() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        Map<String, Object> arguments = new HashMap<>();
//        //消息过期TTL，设置整个消息的过期时间
//        arguments.put("x-message-ttl", 1000 * 60 * 30);
//        //设置队列最大容量长度，数量超出的消息则进入死信队列
//        arguments.put("x-max-length", 10);
//        //正常队列设置死信交换机
//        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
//        //设置死信routingKey
//        arguments.put("x-dead-letter-routing-key", DEAD_EXCHANGE_ROUTING_KEY);
//
//        //声明普通交换机与死信交换机
//        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
//        channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
//        //声明普通队列与死信队列
//        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, arguments);
//        channel.queueDeclare(DEAD_QUEUE_NAME, false, false, false, null);
//        //绑定交换机与队列
//        channel.queueBind(NORMAL_QUEUE_NAME, NORMAL_EXCHANGE_NAME, NORMAL_EXCHANGE_ROUTING_KEY);
//        channel.queueBind(DEAD_QUEUE_NAME, DEAD_EXCHANGE_NAME, DEAD_EXCHANGE_ROUTING_KEY);
//
//        channel.close();
//        connection.close();
//    }
//
//    @GetMapping("/deadLetterProduce")
//    public void deadLetterProduce() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.basicPublish(NORMAL_EXCHANGE_NAME, NORMAL_EXCHANGE_ROUTING_KEY, null, getRandomString(10).getBytes());
//
//        channel.close();
//        connection.close();
//    }
//
//    @GetMapping("/deadLetterConsume")
//    public void deadLetterConsume() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.basicQos(10);
//
//        DeliverCallback deliverCallback = (consumerTag, message) -> {
//            log.info(consumerTag);
//            log.info(new String(message.getBody()));
//            log.info("消费成功");
//            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
////            channel.basicNack();
////            channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
////            log.info("消费拒绝");
//        };
//
//        CancelCallback cancelCallback = consumerTag -> {
//            log.info(consumerTag);
//            log.info("消费被中断");
//        };
//
//        channel.basicConsume(NORMAL_QUEUE_NAME, false, deliverCallback, cancelCallback);
//    }
//
//    @GetMapping("/ttlProduce")
//    public void ttlProduce() {
//        rabbitTemplate.convertAndSend("X", "XA", getRandomString(10));
//        rabbitTemplate.convertAndSend("X", "XB", getRandomString(10));
//        rabbitTemplate.convertAndSend("X", "XC", getRandomString(10));
//
//        org.springframework.amqp.core.MessageProperties messageProperties = new org.springframework.amqp.core.MessageProperties();
//        Message message = new Message("hello word ".getBytes(), messageProperties);
//        rabbitTemplate.convertAndSend("X", "XA", message);
//
//        rabbitTemplate.convertAndSend("Z", "", getRandomString(10));
//    }
//
//    @RabbitListener(queues = "QA")
//    @RabbitHandler
//    @GetMapping("/ttlConsumeA")
//    public void ttlConsumeA(String message) {
//        log.info("consume a {}", message);
//    }
//
//    @RabbitListener(queues = "QB")
//    @RabbitHandler
//    @GetMapping("/ttlConsumeB")
//    public void ttlConsumeB(String message) {
//        log.info("consume b {}", message);
//    }
//
//    @RabbitListener(queues = "QC")
//    @RabbitHandler
//    @GetMapping("/ttlConsumeC")
//    public void ttlConsumeC(String message) {
//        log.info("consume c {}", message);
//    }
//}
