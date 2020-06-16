package com.java.study.designPattern.memorandum;

public class Store {

    private Memmento memmento;

    public Store(Memmento memmento) {
        this.memmento = memmento;
    }

    public Memmento getMemmento() {
        return memmento;
    }

    public void setMemmento(Memmento memmento) {
        this.memmento = memmento;
    }
}
