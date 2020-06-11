package com.java.study.designPattern.celue;

public abstract class AbstractCalculator {

    public int[] spilt(String exp,String opt){
        String array[] = exp.split(opt);
        int arr[] = new int[2];
        arr[0] = Integer.parseInt(array[0]);
        arr[1] = Integer.parseInt(array[1]);
        return arr;
    }
}
