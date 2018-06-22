package com.zhao.spring.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    public static final String ZHAO_TEST_QUEUE = "zhao_test_queue";

    @Bean
    public Queue helloQueue() {
        return new Queue(ZHAO_TEST_QUEUE);
    }

}