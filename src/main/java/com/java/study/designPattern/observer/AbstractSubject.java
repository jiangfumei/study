package com.java.study.designPattern.observer;

import java.util.Enumeration;
import java.util.Vector;

public class AbstractSubject implements Subject{
    private Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        vector.notify();
    }

    @Override
    public void operation() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }
}
