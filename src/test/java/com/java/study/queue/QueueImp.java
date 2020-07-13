package com.java.study.queue;

import org.junit.Before;

import java.util.LinkedList;
import java.util.Queue;

public class QueueImp {
    //add()和remove()方法在失败的时候会抛出异常(不推荐)
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


}
