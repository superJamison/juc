package com.jms.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 17:32
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        closeDoor();

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t关门走人");
    }

    private static void closeDoor() {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
            }, String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t离开教室");
    }
}
