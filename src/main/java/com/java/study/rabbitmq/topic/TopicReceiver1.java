package com.java.study.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.one")
public class TopicReceiver1 {

    @RabbitHandler
    public void process(String message){

        System.out.println("Receiver1 topic.one :"+ message);

    }
}
