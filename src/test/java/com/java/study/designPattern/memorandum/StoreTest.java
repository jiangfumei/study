package com.java.study.designPattern.memorandum;

import com.java.study.BasicTestMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreTest extends BasicTestMvc {

    @Test
    public void store(){
        Original original = new Original("egg");
        Store store = new Store(original.createMemmento());

        // 修改原始类的状态
        System.out.println("初始化状态为:" + original.getValue());
        original.setValue("niu");
        System.out.println("修改后的状态为:" + original.getValue());

        // 回复原始类的状态
        original.restore(store.getMemmento());
        System.out.println("恢复后的状态为:" + original.getValue());
    }
}