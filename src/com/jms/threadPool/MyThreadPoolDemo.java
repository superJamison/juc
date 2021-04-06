package com.jms.threadPool;

import java.util.concurrent.*;

/**
 * 实际生产中如何配置线程池的线程数：
 *      1.CPU密集型：尽可能少的线程数，建议是线程数：CPU核数+1个线程的线程池
 *      2.IO密集型：参考公式：CPU核数/(1-阻塞系数)      阻塞系数在0.8-0.9之间
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/3 14:34
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        //获取线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

//        ExecutorService executorService = Executors.newFixedThreadPool(5); //1池固定5线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); //1池1线程
//        ExecutorService executorService = Executors.newCachedThreadPool(); //1池N线程,可伸缩

        ExecutorService executorService =
                new ThreadPoolExecutor(2,
                        5,
                        2,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.CallerRunsPolicy()
                        );

        try{
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

    }
}
