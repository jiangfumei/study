package com.java.study.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanOutRabbitMq {

    @Bean
    public Queue AMessage(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage(){
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //分别给三个队列指定exchange,这里使用了A、B、C三个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略：

    @Bean
    public Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue BMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue CMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

}
