package com.java.study.designPattern.mingling;

import com.java.study.BasicTestMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCommandTest extends BasicTestMvc {


    @Test
    public void operate(){
        Receiver receiver = new Receiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}