package com.jms.jmm;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 14:10
 */
public class SingletonDemo {
    public static SingletonDemo singletonDemo = null;

    public SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t我是构造方法");
    }

    //DCL （Double Check Lock）双端检索机制，不一定线程安全，原因是有指令重排机制。加volatile禁止指令重排。
    public static SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.singletonDemo);
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.singletonDemo);
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.singletonDemo);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
