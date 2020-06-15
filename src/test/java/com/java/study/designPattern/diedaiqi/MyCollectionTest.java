package com.java.study.designPattern.diedaiqi;

import com.java.study.BasicTestMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCollectionTest extends BasicTestMvc {

    @Test
    public void collect(){
        Collection collection = new MyCollection();
        Iterator it = (Iterator) collection.iterator();
        while(it.hasNext()){
            System.out.println(it.hasNext());
        }

    }

}