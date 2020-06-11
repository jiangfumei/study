package com.java.study.designPattern.singleton;

/**
 * 1.单例模式的饿汉式
 *（1）私有化该类的构造函数
 * (2)通过new在本类中创建一个本类对象
 *（3）定义一个公有的方法，将在该类中所创建的对象返回
 * 线程安全，一个应用中只有一个单例
 */
public class SingletonEHan {

    private SingletonEHan() { /* 私有构造方法,防止被实例化 */
    }

    private static SingletonEHan singletonEHan = new SingletonEHan();

    /**
     * 1.单例模式的饿汉式[可用]
     */
    public static SingletonEHan getInstance(){//静态代码块 1一般情况下,如果有些代码必须在项目启动的时候就执行的时候,这时就需要使用静态代码块,这种代码是主动执行的。一个类可以使用不包含在任何方法体中的静态代码块，当类被载入时，静态代码块被执行，且只被执行一次，静态块常用来执行类属性的初始化
        return singletonEHan;
    }

    //     SingletonEHan instance= SingletonEHan.getInstance();

    /**
     * 2. 单例模式的饿汉式变换写法[可用]
     * 基本没区别
     */

    private static SingletonEHan singletonEHanTwo = null;/* 持有私有静态实例,防止被引用,此处赋值为null,目的是实现延迟加载 */

    static {
        singletonEHan = new SingletonEHan();
    }

    public static SingletonEHan getSingletonEHanTwo(){
        if (singletonEHanTwo == null){
            singletonEHanTwo = new SingletonEHan();
        }
        return singletonEHanTwo;
    }

    //SingletonEHan ehaninstance = SingletonEHan.getInstance();
}
