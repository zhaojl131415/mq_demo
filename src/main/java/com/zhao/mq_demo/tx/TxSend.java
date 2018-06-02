package com.zhao.mq_demo.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者发送消息
 */
public class TxSend {

    public static final String QUEUE_NAME = "test_simple_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "Hello World! TX!";

        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
//            int i = 1/0;
            channel.txCommit();
            System.out.println("send msg:" + msg);
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("tx msg rollback!");
        }

        channel.close();
        connection.close();

    }
}
