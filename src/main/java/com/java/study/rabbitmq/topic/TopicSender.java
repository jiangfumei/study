package com.java.study.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send_one(){
        String context = "Hi,I am message one";
        this.amqpTemplate.convertAndSend(TopicRabbitMqConfig.TOPIC_EXCHANGE,"topic.one",context);
    }
    public void send_two(){
        String context = "Hi,I am message two";
        this.amqpTemplate.convertAndSend(TopicRabbitMqConfig.TOPIC_EXCHANGE,"topic.two",context);
    }

}
