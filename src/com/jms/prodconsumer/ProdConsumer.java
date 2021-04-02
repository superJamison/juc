package com.jms.prodconsumer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{
    int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + ++number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + --number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

//    public synchronized void increment() throws Exception{
//        //while 防止虚假唤醒
//        while (number != 0){
//            wait();
//        }
//        System.out.println(Thread.currentThread().getName() + "\t" + ++number);
//        notifyAll();
//    }
//
//    public synchronized void decrement() throws Exception{
//        while (number == 0){
//            wait();
//        }
//        System.out.println(Thread.currentThread().getName() + "\t" + --number);
//        notifyAll();
//    }
}

/**
 * 消费者/生产者
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 16:04
 *
 *题目：两个线程，可以操作初始值为0的变量，
 *  实现一个线程对改变变量+1，一个线程对该变量-1，
 *  交替实现，来10轮，初始值为0
 *
 *注意：防止虚假唤醒 （while）
 *      像在一个参数版本中，中断和虚假唤醒是可能的，并且该方法应该始终在循环中使用
 */
public class ProdConsumer {
    public static void main(String[] args){
        Aircondition aircondition = new Aircondition();

       new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               try {
//                   TimeUnit.SECONDS.sleep(2);
                   aircondition.increment();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }, "A").start();
       new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               try {
                   aircondition.decrement();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }, "B").start();

       new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               try {
                   aircondition.increment();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }, "C").start();
       new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               try {
                   aircondition.decrement();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }, "D").start();
    }
}
