package com.java.study.designPattern.singleton;
/**
 * 7. 内部类[推荐用]
 * <p>
 * 这种方式跟饿汉式方式采用的机制类似，但又有不同。
 * 两者都是采用了类装载的机制来保证初始化实例时只有一个线程。
 * 不同的地方:
 * 在饿汉式方式是只要Singleton类被装载就会实例化,
 * 内部类是在需要实例化时，调用getInstance方法，才会装载SingletonHolder类
 * <p>
 * * 优点：避免了线程不安全，延迟加载，效率高。
 *  */
 public class SingletonIn {
     //JVM 内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。这样当我们第一次调用 getInstance 的时候，JVM 能够帮我们保证 instance 只被创建一次，并且会保证把赋值给 instance 的内存初始化完毕，这样我们就不用担心上面的问题。同时该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低性能问题。
    private SingletonIn() {
    }
    /* 此处使用一个内部类来维护单例 */
    private static class SingletonInHolder{
        private static SingletonIn singletonIn = new SingletonIn();
    }

    //获取实例
    public static SingletonIn getInstance(){
        return SingletonInHolder.singletonIn;
    }

    /* 如果该对象被用于序列化,可以保证对象在序列化前后保持一致 */
    public Object readReslove(){
        return getInstance();
    }
}
