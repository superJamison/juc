package com.jms.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 17:37
 */
public class NonFairLockDemo {
    public static void main(String[] args) {
        //非公平锁
        Lock lock = new ReentrantLock();
    }
}
