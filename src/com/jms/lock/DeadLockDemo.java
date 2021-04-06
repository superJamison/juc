package com.jms.lock;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockA + "\t尝试获得" + lockB);

            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t获得了" + lockB);
            }
        }
    }
}

/**
 * jps -l           查看线程
 * jstack 线程号     查看线程死锁情况
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 17:00
 */
public class DeadLockDemo {
    public static void main(String[] args) {

        new Thread(new HoldLockThread("lockA", "lockB"), "AA").start();
        new Thread(new HoldLockThread("lockB", "lockA"), "BB").start();
    }
}
