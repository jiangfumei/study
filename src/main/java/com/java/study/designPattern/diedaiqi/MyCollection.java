package com.java.study.designPattern.diedaiqi;

import java.util.Iterator;

public class MyCollection implements Collection{

    public String string[] = {"A","B","C","D","E"};

    @Override
    public Iterator iterator() {
        return (Iterator) new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return string[i];
    }

    @Override
    public int size() {
        return string.length;
    }
}
