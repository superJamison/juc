package com.jms.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA：狸猫换太子
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 14:48
 */
public class ABADemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);
        System.out.println("============一下是ABA问题的产生=================");

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                //睡眠2秒，让t1线程完成一次ABA操作
                TimeUnit.SECONDS.sleep(2);
                boolean b = atomicReference.compareAndSet(100, 2021);
                System.out.println(Thread.currentThread().getName() + "\t 修改成功否：" + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("============一下是ABA问题的解决=================");

        new Thread(() -> {
            try {
                int stamp = stampedReference.getStamp();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
                stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\t第二次版本号：" + stampedReference.getStamp());
                stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\t第三次版本号：" + stampedReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();

        new Thread(() -> {
            try {
                int stamp = stampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() + "\t第一次版本号：" + stamp);
                TimeUnit.SECONDS.sleep(3);
                boolean result = stampedReference.compareAndSet(100, 2021, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName() +"\t修改成功否：" + result + "\t最新的版本号：" + stampedReference.getStamp());
                System.out.println(Thread.currentThread().getName() + "\t修改后的最新值：" + stampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4").start();
    }
}
