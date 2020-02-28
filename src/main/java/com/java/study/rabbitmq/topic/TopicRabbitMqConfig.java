package com.java.study.rabbitmq.topic;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitMqConfig {

    final static String TOPIC_ONE = "topic.one";
    final static String TOPIC_TWO = "topic.two";
    final static String TOPIC_EXCHANGE = "topicExchange";
    /**
     * Topic 交换机模式  可以用通配符
     */

    //创建两个 Queue
    @Bean
    public Queue queue_one(){
        return new Queue(TOPIC_ONE);
    }

    @Bean
    public Queue queue_two(){
        return new Queue(TOPIC_TWO);
    }

    //配置 TopicExchange,指定名称为 topicExchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    //给队列绑定 exchange 和 routing_key

    @Bean
    public Binding bindingExchangeMessage(Queue queue_one, TopicExchange exchange){
        return BindingBuilder.bind(queue_one).to(exchange).with("topic.one");
    }

    @Bean
    public Binding bingingExchangeMessages(Queue queue_two,TopicExchange exchange){
        return BindingBuilder.bind(queue_two).to(exchange).with("topic.#");
    }




}
