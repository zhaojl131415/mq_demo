package com.zhao.spring.service;

/**
 * 未归档公司
 */
public interface MqService {

    void sendMsg(String msg);

    void sendUser();

    void sendMessage(String message);

    void sendError(String error);

    void sendMessages(String message);

    void sendFanout(String fanout);
}
