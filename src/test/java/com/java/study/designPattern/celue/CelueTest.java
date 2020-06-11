package com.java.study.designPattern.celue;

import com.java.study.BasicTestMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CelueTest extends BasicTestMvc {

    @Test
    public void celue(){
        String exp = "2+8";
        ICalculator calculator = new Plus();
        int result = calculator.calculate(exp);
        System.out.println("result为："+result);
    }

}