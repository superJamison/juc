package com.jms.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{ //资源类：实例变量+实例方法
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + "\t卖出第：" + number-- + "\t还剩下；" + number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 *  题目：三个售票员    卖出         30张票
 *
 *  思路：在高内聚低耦合的前提下：线程   操作   资源类
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 11:07
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale();}, "A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale();}, "B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale();}, "C").start();


//        new Thread(() -> {
//            for (int i = 0; i < 40; i++) {
//                ticket.sale();
//            }
//        }, "A").start();
//        new Thread(() -> {
//            for (int i = 0; i < 40; i++) {
//                ticket.sale();
//            }
//        }, "B").start();
//        new Thread(() -> {
//            for (int i = 0; i < 40; i++) {
//                ticket.sale();
//            }
//        }, "C").start();
    }
}
