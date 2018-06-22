package com.zhao.spring.service.impl;

import com.zhao.spring.config.MqConfig;
import com.zhao.spring.service.MqService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
@Service
public class MqServiceImpl implements MqService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMsg(String msg) {
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(() -> amqpTemplate.convertAndSend(MqConfig.ZHAO_TEST_QUEUE, "hello, " + msg + "!"));
//        executorService.shutdown();
//
//
        String context = "hello, " + msg + " !" + new Date();
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_TEST_QUEUE, context);
    }
}
