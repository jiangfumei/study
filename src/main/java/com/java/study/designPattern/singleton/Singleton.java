package com.java.study.designPattern.singleton;

public class Singleton {

    /*public static class SingletonFactory{
        private static Singleton ins = new Singleton();
    }

    public static Singleton getInstence(){
        return SingletonFactory.ins;
    }*/

    public static Singleton ins = null;

    public Singleton() {
    }

    private static synchronized void syncint(){
        if (ins == null){
            ins = new Singleton();
        }
    }

    public static Singleton getInstance() {
        if (ins == null) {
            syncint();
        }
        return ins;
    }
}
