package com.java.study;

import com.java.study.rabbitmq.HelloSender;
import com.java.study.rabbitmq.HelloSender2;
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

}
