package com.java.study.designPattern.zerenlian;

public class MyHandler extends AbstractHandler implements Handler{

    private String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operate() {
        System.out.println(name+"deal");
        if (getHandler()!=null){
            getHandler().operate();
        }
    }
}
