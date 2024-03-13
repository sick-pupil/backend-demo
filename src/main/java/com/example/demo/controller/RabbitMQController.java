package com.example.demo.controller;

import com.example.demo.service.RabbitMQService;
import com.rabbitmq.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author lhy
 * @create 2024-03-12 12:01
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor
public class RabbitMQController {

    private final RabbitMQService rabbitMQService;

    private final static String QUEUE_NAME = "test";

    private final static String FANOUT_EXCHANGE_NAME = "fanout-exchange";
    private final static String FANOUT_QUEUE1_NAME = "fanout-queue1";
    private final static String FANOUT_QUEUE2_NAME = "fanout-queue2";
    private final static String FANOUT_ROUTING_KEY = "fanout-routing-key";

    @GetMapping("/test")
    public void test() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        try {
            channel.exchangeDeclare("test-exchange", BuiltinExchangeType.FANOUT);
            channel.queueDeclare("test-queue1", true, false, false, null);
            channel.queueDeclare("test-queue2", true, false, false, null);
            channel.queueBind("test-queue1", "test-exchange", "");
            channel.queueBind("test-queue2", "test-exchange", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/simpleModeProduce")
    public void simpleModeProduce() throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        try {
            /**
             * 声明一个队列
             * 1. 队列名称
             * 2. 队列里的消息是否持久化 默认存储在内存中
             * 3. 队列是否只供一个消费者进行消费 是否共享给多个消费者消费
             * 4. 是否在最后一个消费者断开连接后队列自动删除
             * 5. 其他参数
             */
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            channel.confirmSelect();
            //监听消息确认成功的回调
            ConfirmCallback ackCallback = (deliveryTag, ackSignal) -> {
                log.info("消息发布确认成功");
                if(ackSignal) {
                    log.info("发布确认成功");
                }
            };
            //监听消息确认失败的回调
            ConfirmCallback nackCallback = (deliveryTag, ackSignal) -> {
                log.info("消息发布确认失败");
                if(ackSignal) {
                    log.info("发布确认失败");
                }
            };
            channel.addConfirmListener(ackCallback, nackCallback);

            /**
             * 发送一个消息到队列
             * 1. 发送到哪个交换机
             * 2. 路由key，路由到哪个队列
             * 3. 其他参数
             * 4. 发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, getRandomString(10).getBytes());
            /**
            boolean publishResult = channel.waitForConfirms();
            if(publishResult) {
                log.info("消息持久化完毕");
            }
             **/
            log.info("消息发送完毕");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    @GetMapping("/simpleModeConsume")
    public void simpleModeConsume() throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//        channel.basicQos(5);

        try {
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                log.info(consumerTag);
                log.info(new String(message.getBody()));
                log.info("消费成功");
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);

                log.info(message.getProperties().toString());
//                channel.basicNack(message.getEnvelope().getDeliveryTag(), false, true);
            };

            CancelCallback cancelCallback = consumerTag -> {
                log.info(consumerTag);
                log.info("消费被中断");
            };

            /**
             * 1. 消费哪个队列
             * 2. 消费成功后是否需要自动应答
             * 3. 消息发送过来的回调
             * 4. 被取消消费的回调
             */
            channel.basicConsume(QUEUE_NAME,false, deliverCallback, cancelCallback);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    //length用户要求产生字符串的长度
    private String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < length; i++){
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @GetMapping("/fanoutProduce")
    public void fanoutProduce() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.queueDeclare(FANOUT_QUEUE1_NAME, true, false, false, null);
        channel.queueDeclare(FANOUT_QUEUE2_NAME, true, false, false, null);
        channel.queueBind(FANOUT_QUEUE1_NAME, FANOUT_EXCHANGE_NAME, FANOUT_ROUTING_KEY);
        channel.queueBind(FANOUT_QUEUE2_NAME, FANOUT_EXCHANGE_NAME, FANOUT_ROUTING_KEY);

        channel.basicPublish(FANOUT_EXCHANGE_NAME, FANOUT_ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, getRandomString(10).getBytes());
    }

    @GetMapping("/fanoutConsume")
    public void fanoutConsume() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            log.info(consumerTag);
            log.info(new String(message.getBody()));
            log.info("消费成功");
        };

        CancelCallback cancelCallback = consumerTag -> {
            log.info(consumerTag);
            log.info("消费被中断");
        };

        channel.basicConsume(FANOUT_QUEUE1_NAME, true, deliverCallback, cancelCallback);
        channel.basicConsume(FANOUT_QUEUE2_NAME, true, deliverCallback, cancelCallback);
    }

    @GetMapping("/directProduce")
    public void directProduce() {

    }

    @GetMapping("/directConsume")
    public void directConsume() {

    }
}
