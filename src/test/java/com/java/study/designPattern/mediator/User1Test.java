package com.java.study.designPattern.mediator;

import com.java.study.BasicTestMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User1Test extends BasicTestMvc {

    @Test
    public void mediator(){
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }

}