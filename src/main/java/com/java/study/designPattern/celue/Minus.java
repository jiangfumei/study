package com.java.study.designPattern.celue;

public class Minus extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int arrayInt[] = spilt(exp,"-");
        return arrayInt[0]-arrayInt[1];
    }
}
