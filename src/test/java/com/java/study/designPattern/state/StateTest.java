package com.java.study.designPattern.state;

import com.java.study.BasicTestMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StateTest extends BasicTestMvc {

    @Test
    public void state(){
        State state = new State();
        Context context = new Context(state);
        state.setValue("state1");
        context.method();
        state.setValue("state2");
        context.method();

    }

}