package com.jms.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 14:22
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // compareAndSet(int 期望值, int 更新值)
        System.out.println(atomicInteger.compareAndSet(5, 1024));//true
        System.out.println(atomicInteger.get());//1024
        System.out.println(atomicInteger.compareAndSet(5, 99)); //false
        System.out.println(atomicInteger.get());//1024

    }
}
