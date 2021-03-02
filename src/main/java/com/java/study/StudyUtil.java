package com.java.study;

public class StudyUtil {

    private int a;
    private static int b;

    public static void test(){
        System.out.println(b);
    }

    public StudyUtil(){}

    private static class SingletonHolder{
        private static final StudyUtil Instance = new StudyUtil();
    }

    public static StudyUtil getUniqueInstance() {
        return SingletonHolder.Instance;
    }
}
