package com.java.study.designPattern.memorandum;

public class Memmento {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Memmento(String value) {
        this.value = value;
    }
}
