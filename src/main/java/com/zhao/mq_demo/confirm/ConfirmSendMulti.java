package com.zhao.mq_demo.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者发送消息
 */
public class ConfirmSendMulti {

    public static final String QUEUE_NAME = "test_queue_confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect  将channel设置为confirm模式
        channel.confirmSelect();

        String msg = "Hello World! confirm!";
        // 批量发送
        for (int i = 0; i < 10; i++) {
            msg += i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        // 确认
        if(!channel.waitForConfirms()){
            System.out.println("confirm msg send failed!");
        }
        System.out.println("confirm msg send OK!");

        channel.close();
        connection.close();

    }
}
