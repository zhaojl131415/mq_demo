package com.zhao.mq_demo.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {
    /**
     * 获取MQ的连接
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException{
        /** 定义一个连接工厂 */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1"); // ip
        factory.setPort(5672); // 端口
        factory.setVirtualHost("/vhost_zhao"); // vhost
        factory.setUsername("zhao"); // 用户名
        factory.setPassword("zhao"); // 密码
        return factory.newConnection();
    }
}
