package com.zhao.mq_demo.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicSend {

    public static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建交换机声明
        channel.exchangeDeclare(EXCHANGE_NAME, "topic"); // 分发

        String msg = "Hello World! 商品...";
        channel.basicPublish(EXCHANGE_NAME, "goods.update", null, msg.getBytes());

        System.out.println("publish:"+msg);

        channel.close();
        connection.close();
    }
}
