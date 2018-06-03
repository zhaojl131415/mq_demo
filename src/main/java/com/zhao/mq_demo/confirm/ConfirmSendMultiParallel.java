package com.zhao.mq_demo.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.zhao.mq_demo.utils.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * 生产者发送消息
 */
public class ConfirmSendMultiParallel {

    public static final String QUEUE_NAME = "test_queue_confirm_p";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取一个连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取一个通道
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect  将channel设置为confirm模式
        channel.confirmSelect();

        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        // 通道添加监听
        channel.addConfirmListener(new ConfirmListener() {
            // 没有问题的handleAck
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("--------handleAck--------multiple--------true");
                    confirmSet.headSet(deliveryTag + 1).clear();
                }
                System.out.println("--------handleAck--------multiple--------false");
                confirmSet.remove(deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("--------handleNack--------multiple--------true");
                    confirmSet.headSet(deliveryTag + 1).clear();
                }
                System.out.println("--------handleNack--------multiple--------false");
                confirmSet.remove(deliveryTag);
            }
        });

        String msg = "Hello World! confirm! multiple! parallel!";

        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);
        }

//        channel.close();
//        connection.close();

    }
}
