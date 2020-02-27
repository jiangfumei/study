package com.java.study.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender2 {

    @Autowired
    AmqpTemplate amqpTemplate;

    //给hello2发送消息,并接受一个计数参数
    public void send2(int i){
        String context = i+"";
        System.out.println(context+"----send");
        //往名称 hello2 的queue中发送消息
        this.amqpTemplate.convertAndSend("hello2",context);
    }
}
