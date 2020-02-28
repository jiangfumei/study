package com.java.study;

import com.java.study.modules.domain.User;
import com.java.study.rabbitmq.HelloSender;
import com.java.study.rabbitmq.HelloSender2;
import com.java.study.rabbitmq.ObjectSender;
import com.java.study.rabbitmq.topic.FanoutSender;
import com.java.study.rabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqdemoApplicationTest {

    @Autowired
    HelloSender helloSender;

    @Autowired
    HelloSender2 helloSender2;

    @Autowired
    ObjectSender objectSender;

    @Autowired
    TopicSender topicSender;

    @Autowired
    FanoutSender fanoutSender;

    @Test
    public void contextLoads() {
        helloSender.send();

    }

    @Test
    public void manyReceiver(){
        for (int i=0;i<100;i++){
            helloSender.send2(i);
        }

    }

    @Test
    public void many2many(){
        for (int i=0;i<100;i++){
            helloSender.send2(i);
            helloSender2.send2(i);

        }
    }

    @Test
    public void usertest(){
        User user = new User();
        user.setUsername("jiangfumei");
        user.setPassword("dahaoren");
        objectSender.sendUser(user);
    }

    @Test
    public void topic1(){
        topicSender.send1();
        topicSender.send2();
    }
    @Test
    public void topic2(){
        topicSender.send1();
        topicSender.send2();
    }

    @Test
    public void fanout(){
        fanoutSender.send();
    }


}
