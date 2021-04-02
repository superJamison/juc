package com.jms.lock;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail......");
    }

    public synchronized void sendSMS() throws Exception{
        System.out.println("sendSMS.......");
    }

    public void sayHello() throws Exception{
        System.out.println("sayHello.......");
    }
}

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 12:52
 *
 * 8锁：
 *  1. 标准访问，请问先打印邮件还是短信: sendEmail-->sendSMS    锁的是同一个资源对象/对象锁
 *  2. 暂停4钟在邮件方法上，请问先打印邮件还是短信： sendEmail-->sendSMS
 *  3. 新增普通方法，请问先打印邮件还是sayHello: sayHello-->sendEmail
 *  4. 2部手机，请问先打印邮件还是短信：sendSMS-->sendEmail
 *  5. 2个静态同步方法，同一部手机，请问先打印邮件还是短信：sendEmail-->sendSMS
 *  6. 2个静态同步方法，2部手机，请问先打印邮件还是短信：sendEmail-->sendSMS
 *  7. 1个静态同步方法，1个普通同步方法，1部手机，请问先打印邮件还是短信：sendSMS-->sendEmail
 *  8. 1个静态同步方法，1个普通同步方法，2部手机，请问先打印邮件还是短信：sendSMS-->sendEmail
 *
 *
 *  总结：
 *      1.一个对象里面同时有多个synchronized方法，某一时刻内，只要有一个线程调用了其中一个synchronized方法，
 *        其他线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程方法去访问这些synchronized方法
 *        锁的是当前对象this
 *
 *      普通同步方法锁的是this对象
 *      静态同步方法锁的是Class对象，Class全局对象 ，
 *
 *      Class对象，一个类对应一个Class对象
 *
 *     普通和静态是两把不同的锁
 *
 */
public class Lock8 {

    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
//                phone1.sendSMS();
//                phone1.sayHello();
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "").start();

    }
}
