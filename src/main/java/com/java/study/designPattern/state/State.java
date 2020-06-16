package com.java.study.designPattern.state;

public class State {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1(){
        System.out.println("execuate the first pot");
    }

    public void method2(){
        System.out.println("execuate the second pot");
    }
}
