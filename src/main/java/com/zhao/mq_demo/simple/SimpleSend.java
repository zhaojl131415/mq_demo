package com.zhao.mq_demo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者发送消息
 */
public class SimpleSend {

    public static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "Hello World! Simple!------3";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        System.out.println("send msg:" + msg);

        channel.close();
        connection.close();

    }
}
