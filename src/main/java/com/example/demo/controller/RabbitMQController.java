package com.example.demo.controller;

import com.example.demo.service.RabbitMQService;
import com.rabbitmq.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final static String QUEUE_NAME = "hello";

    @GetMapping("/simpleUse")
    public void simpleUse() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println(new String(message.getBody()));
            };

            CancelCallback cancelCallback = consumerTag -> {
                System.out.println("消费被中断");
            };

            /**
             * 1. 消费哪个队列
             * 2. 消费成功后是否需要自动应答
             * 3. 消息发送过来的回调
             * 4. 被取消消费的回调
             */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
