package com.zhao.spring.service.impl;

import com.zhao.spring.config.MqConfig;
import com.zhao.spring.domain.User;
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
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_MSG_QUEUE, context);
    }

    @Override
    public void sendUser() {
        User user = new User();    //实现Serializable接口
        user.setName("zhao");
        user.setPassword("123");
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_USER_QUEUE, user);
    }

    @Override
    public void sendMessage(String message) {
        String context = "hello, " + message + " !";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_TOPIC_EXCHANGE, MqConfig.ZHAO_TOPIC_MESSAGE_ROUTINGKEY, context);
    }

    @Override
    public void sendError(String error) {
        String context = "hello, " + error + " !";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_TOPIC_EXCHANGE, MqConfig.ZHAO_TOPIC_ERROR_ROUTINGKEY, context);
    }

    @Override
    public void sendMessages(String message) {
        String context = "hello, " + message + " !";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_TOPIC_EXCHANGE, MqConfig.ZHAO_TOPIC_MESSAGES_ROUTINGKEY, context);
    }

    @Override
    public void sendFanout(String fanout) {
        String context = "hello, " + fanout + " !";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(MqConfig.ZHAO_FANOUT_EXCHANGE, "", context);
    }
}
