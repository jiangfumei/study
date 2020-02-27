package com.java.study.rabbitmq.topic;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitMqConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";
    /**
     * Topic 交换机模式  可以用通配符
     */

    //创建两个 Queue
    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitMqConfig.message);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitMqConfig.messages);
    }

    //配置 TopicExchange,指定名称为 topicExchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    //给队列绑定 exchange 和 routing_key

    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    public Binding bingingExchangeMessages(Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }




}
