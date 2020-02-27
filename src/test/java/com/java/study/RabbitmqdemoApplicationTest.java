package com.java.study;

import com.java.study.rabbitmq.HelloSender;
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

    @Test
    public void contextLoads() {
        helloSender.send();

    }
}
