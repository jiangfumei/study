package com.java.study.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //队列主题名称
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmqDemoTopic";
    //DIRECT交换机名称
    public static final  String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";
    //rabbitmq的direct交换机和队列绑定的匹配键 directrouting
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";

    @Bean
    public Queue queue(){
        return new Queue("hello");
    }

    @Bean
    public Queue queue2(){
        return new Queue("hello2");
    }

    @Bean
    public Queue queue3(){
        return new Queue("object_queue");
    }


}
