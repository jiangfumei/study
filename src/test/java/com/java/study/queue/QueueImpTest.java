package com.java.study.queue;

import com.java.study.BasicTestMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueImpTest extends BasicTestMvc {

    // 定义一个队列
    java.util.Queue<String> queue;

    @Before
    public void before(){
        queue = new LinkedList<String>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
    }

    @Test
    public void poll(){
        String pollEm = queue.poll();
        System.out.println(pollEm);
        System.out.println(queue);
    }

    @Test
    public void element(){
        String element = queue.element();
        System.out.println(element);
    }

    @Test
    public void peek(){
        String peek = queue.peek();
        System.out.println(peek);
    }

    @Test
    public void remove(){
        String remove = queue.remove();
        System.out.println(remove);
    }

}
