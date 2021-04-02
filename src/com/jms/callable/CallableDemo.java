package com.jms.callable;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "come in call method.....");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 16:57
 *
 * 面试经典：有几种获取Thread的方法？
 *  一共有四种，它们是：
 *      extend Thread
 *      implement Runnable
 *      implement Callable
 *      线程池，ThreadPoolExecutor
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread( new MyThread(), "A");

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        //只会打印一次，因为是同一个futureTask
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        //.get是一个阻塞方法
        System.out.println(futureTask.get());
    }
}
