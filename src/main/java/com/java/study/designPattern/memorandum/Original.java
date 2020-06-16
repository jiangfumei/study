package com.java.study.designPattern.memorandum;

public class Original {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Original(String value) {
        this.value = value;
    }

    public Memmento createMemmento(){
        return new Memmento(value);
    }

    public void restore(Memmento memmento){
       this.value = memmento.getValue();
    }

}
