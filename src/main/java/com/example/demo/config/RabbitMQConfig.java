package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhy
 * @create 2024-03-14 10:59
 * @description
 **/
@Configuration
public class RabbitMQConfig {

    //普通交换机与死信交换机
    public static final String X_EXCHANGE = "X";
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String Z_EXCHANGE = "Z";

    //普通延迟队列
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String QUEUE_C = "QC";

    //死信队列
    public static final String DEAD_LETTER_QUEUE = "QD";

    @Bean
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public FanoutExchange zExchange() {
        return new FanoutExchange(Z_EXCHANGE);
    }

    @Bean
    public Queue queueA() {
        Map<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        //设置整个队列的TTL过期时间
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.nonDurable(QUEUE_A).withArguments(arguments).build();
    }

    @Bean
    public Queue queueB() {
        Map<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        //设置整个队列的TTL过期时间
        arguments.put("x-message-ttl", 40000);
        return QueueBuilder.nonDurable(QUEUE_B).withArguments(arguments).build();
    }

    @Bean
    public Queue queueC() {
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.nonDurable(QUEUE_C).withArguments(arguments).build();
    }

    @Bean
    public Queue queueD() {
        return QueueBuilder.nonDurable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    @Bean
    public Binding queueCBindingX(@Qualifier("queueC") Queue queueC, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }

    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }

    @Bean
    public Binding queueABindingZ(@Qualifier("queueA") Queue queueA, @Qualifier("zExchange") FanoutExchange zExchange) {
        return BindingBuilder.bind(queueA).to(zExchange);
    }

    @Bean
    public Binding queueBBindingZ(@Qualifier("queueB") Queue queueB, @Qualifier("zExchange") FanoutExchange zExchange) {
        return BindingBuilder.bind(queueB).to(zExchange);
    }

    @Bean
    public Binding queueCBindingZ(@Qualifier("queueC") Queue queueC, @Qualifier("zExchange") FanoutExchange zExchange) {
        return BindingBuilder.bind(queueC).to(zExchange);
    }
}
