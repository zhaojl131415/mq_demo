package com.zhao.mq_demo.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkSend {
    public static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 50; i++) {
            String msg = "Hello World! Work"+i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(i * 20);
        }
        channel.close();
        connection.close();
    }

}
