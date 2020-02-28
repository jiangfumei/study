package com.java.study.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.two")
public class TopicReceiver2 {
    @RabbitHandler
    public void process(String message){

        System.out.println("Receiver2 topic.two :"+ message);

    }
}
