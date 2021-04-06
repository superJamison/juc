package com.jms.jmm;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    //添加了volatile关键字
    volatile int number = 0;

    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void atomicAdd(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 13:38
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.atomicAdd();
                }
            }, String.valueOf(i)).start();
        }

        //等待前面的20个线程执行完成
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        //预期结果是20000，但是实际上的结果是小于20000，是不确定的。这就验证了volatile不保证原子性
        System.out.println(Thread.currentThread().getName() + "addPlusPlus finally add " + myData.number);
        System.out.println(Thread.currentThread().getName() + "atomicAdd finally add " + myData.atomicInteger);
    }
}
