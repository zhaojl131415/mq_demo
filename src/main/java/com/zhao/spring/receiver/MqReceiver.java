package com.zhao.spring.receiver;

import com.zhao.spring.config.MqConfig;
import com.zhao.spring.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = MqConfig.ZHAO_MSG_QUEUE)
public class MqReceiver {

//    @RabbitHandler  // 指定对消息的处理方法
    @RabbitListener(queues = MqConfig.ZHAO_MSG_QUEUE)
    public void getMsg(String msg) {
        System.out.println("Receiver_Msg: " + msg);
    }

    @RabbitListener(queues = MqConfig.ZHAO_USER_QUEUE)  //监听器监听指定的Queue
    public void getUser(User user) {    //用User作为参数
        System.out.println("Receive_User: " + user.getName() + " , " + user.getPassword());
    }

    @RabbitListener(queues = MqConfig.ZHAO_TOPIC_MESSAGE_QUEUE)    //监听器监听指定的Queue
    public void getTopicMessage(String message) {
        System.out.println("Receiver_Message: " + message);
    }

    @RabbitListener(queues = MqConfig.ZHAO_TOPIC_ERROR_QUEUE)    //监听器监听指定的Queue
    public void getTopicError(String error) {
        System.out.println("Receiver_Error: " + error);
    }

    @RabbitListener(queues = MqConfig.ZHAO_TOPIC_MESSAGES_QUEUE)    //监听器监听指定的Queue
    public void getTopicMessages(String messages) {
        System.out.println("Receiver_Messages: " + messages);
    }

    @RabbitListener(queues = MqConfig.ZHAO_FANOUT_A_QUEUE)
    public void getFanoutA(String fanout_a) {
        System.out.println("Receiver_A: " + fanout_a);
    }

    @RabbitListener(queues = MqConfig.ZHAO_FANOUT_B_QUEUE)
    public void getFanoutB(String fanout_b) {
        System.out.println("Receiver_B: " + fanout_b);
    }

    @RabbitListener(queues = MqConfig.ZHAO_FANOUT_C_QUEUE)
    public void getFanoutC(String fanout_c) {
        System.out.println("Receiver_C: " + fanout_c);
    }

}