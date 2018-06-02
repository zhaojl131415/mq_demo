package com.zhao.mq_demo.tx;

import com.rabbitmq.client.*;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者获取消息
 */
public class TxReceive {
    public static final String QUEUE_NAME = "test_simple_tx";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("tx recv:" + msg);
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
