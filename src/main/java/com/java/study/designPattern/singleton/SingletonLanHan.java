package com.java.study.designPattern.singleton;

/**
 * 单例模式的懒汉式【线程不安全，不可用】
 */
public class SingletonLanHan {

    private SingletonLanHan() {
    }

    /**
     * 3.单例模式的懒汉式[线程不安全，不可用]
     */
    private static SingletonLanHan singletonLanHan;

    public static SingletonLanHan getInstance() {
        if (singletonLanHan == null) { // 这里线程是不安全的,可能得到两个不同的实例
            singletonLanHan = new SingletonLanHan();
        }
        return singletonLanHan;
    }


    /**
     * 4. 懒汉式线程安全的:[线程安全，效率低不推荐使用]
     * <p>
     * 缺点：效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。
     * 而其实这个方法只执行一次实例化代码就够了，
     * 后面的想获得该类实例，直接return就行了。方法进行同步效率太低要改进。
     */
    private static SingletonLanHan singletonLanHanTwo;

    public static synchronized SingletonLanHan getSingletonLanHanTwo() {//synchronized关键字锁住的是这个对象,这样的用法,在性能上会有所下降,因为每次调用getInstance(),都要对对象上锁,事实上,只有在第一次创建对象的时候需要加锁,之后就不需要了,所以,这个地方需要改进。
        if (singletonLanHanTwo == null) { // 这里线程是不安全的,可能得到两个不同的实例
            singletonLanHanTwo = new SingletonLanHan();
        }
        return singletonLanHanTwo;
    }

    /**
     * 5. 单例模式懒汉式[线程不安全，不可用]
     * <p>
     * 虽然加了锁，但是等到第一个线程执行完instance=new Singleton()跳出这个锁时，
     * 另一个进入if语句的线程同样会实例化另外一个Singleton对象，线程不安全的原理跟3类似。
     */
    private static SingletonLanHan instanceThree = null;

    public static SingletonLanHan getSingletonLanHanThree() {
        if (instanceThree == null) {
            synchronized (SingletonLanHan.class) {// 线程不安全
                instanceThree = new SingletonLanHan();
            }
        }
        return instanceThree;
    }

    /**
     * 6.单例模式懒汉式双重校验锁[推荐用]
     * 懒汉式变种,属于懒汉式的最好写法,保证了:延迟加载和线程安全
     */
    private static SingletonLanHan singletonLanHanFour;

    public static SingletonLanHan getSingletonLanHanFour() {//将 synchronized 关键字加在了内部，也就是说当调用的时候是不需要加锁的，只有在 instance 为 null, 并创建对象的时候才需要加锁，性能有一定的提升。
        if (singletonLanHanFour == null) {
            synchronized (SingletonLanHan.class) {
                if (singletonLanHanFour == null) {
                    singletonLanHanFour = new SingletonLanHan();
                }
            }
        }
        return singletonLanHanFour;
    }
}
