package com.zhao.spring.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    /** ===================Direct=================== */
    /** 普通消息队列， 发送字符 */
    public static final String ZHAO_MSG_QUEUE = "zhao_msg_queue";
    @Bean
    public Queue msgQueue() {
        return new Queue(ZHAO_MSG_QUEUE);
    }

    /** 普通消息队列， 发送对象 */
    public static final String ZHAO_USER_QUEUE = "zhao_user_queue";
    @Bean
    public Queue userQueue() {
        return new Queue(ZHAO_USER_QUEUE);
    }
    /** ===================Direct=================== */

    /** ===================Topic转发:message队列和error队列收到的消息除了监听自身的队列会收到以外，还会全部同时转发给监听了 messages 的队列=================== */
    /** 交换机 */
    public static final String ZHAO_TOPIC_EXCHANGE = "zhao_topic_exchange";
    // 配置交换机(Exchange)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ZHAO_TOPIC_EXCHANGE);
    }
    /** message */
    public static final String ZHAO_TOPIC_MESSAGE_QUEUE = "zhao_topic_message_queue"; // 消息队列
    // 配置队列(Queue)
    @Bean(name="message")
    public Queue queueMessage() {
        return new Queue(ZHAO_TOPIC_MESSAGE_QUEUE);
    }
    // 把队列按照相应的规则绑定到交换机上
    public static final String ZHAO_TOPIC_MESSAGE_ROUTINGKEY = "topic.message"; // 路由
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ZHAO_TOPIC_MESSAGE_ROUTINGKEY);
    }
    /** error */
    public static final String ZHAO_TOPIC_ERROR_QUEUE = "zhao_topic_error_queue"; // 消息队列
    @Bean(name="error")
    public Queue errorMessage() {
        return new Queue(ZHAO_TOPIC_ERROR_QUEUE);
    }
    public static final String ZHAO_TOPIC_ERROR_ROUTINGKEY = "topic.error"; // 路由
    @Bean
    public Binding bindingExchangeError(@Qualifier("error") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ZHAO_TOPIC_ERROR_ROUTINGKEY);
    }

    public static final String ZHAO_TOPIC_MESSAGES_QUEUE = "zhao_topic_messages_queue"; // 消息队列
    @Bean(name="messages")
    public Queue queueMessages() {
        return new Queue(ZHAO_TOPIC_MESSAGES_QUEUE);
    }
    public static final String ZHAO_TOPIC_MESSAGES_ROUTINGKEY = "topic.#"; // 路由
    @Bean
    public Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with(ZHAO_TOPIC_MESSAGES_ROUTINGKEY);//*表示一个词,#表示零个或多个词
    }
    /** ===================Topic转发=================== */



    /** ===================Fanout Exchange模式又叫广播(订阅)模式=================== */

    /** 交换机 */
    public static final String ZHAO_FANOUT_EXCHANGE = "zhao_fanout_exchange";
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(ZHAO_FANOUT_EXCHANGE);//配置广播路由器
    }


    public static final String ZHAO_FANOUT_A_QUEUE = "zhao_fanout_a_queue"; // 消息队列
    @Bean(name="fanout_a")
    public Queue fanoutA() {
        return new Queue(ZHAO_FANOUT_A_QUEUE);
    }
    @Bean
    Binding bindingExchangeA(@Qualifier("fanout_a") Queue fanoutA,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutA).to(fanoutExchange);
    }

    public static final String ZHAO_FANOUT_B_QUEUE = "zhao_fanout_b_queue"; // 消息队列
    @Bean(name="fanout_b")
    public Queue fanoutB() {
        return new Queue(ZHAO_FANOUT_B_QUEUE);
    }
    @Bean
    Binding bindingExchangeB(@Qualifier("fanout_b") Queue fanoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutB).to(fanoutExchange);
    }

    public static final String ZHAO_FANOUT_C_QUEUE = "zhao_fanout_c_queue"; // 消息队列
    @Bean(name="fanout_c")
    public Queue fanoutC() {
        return new Queue(ZHAO_FANOUT_C_QUEUE);
    }
    @Bean
    Binding bindingExchangeC(@Qualifier("fanout_c") Queue fanoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutC).to(fanoutExchange);
    }
}