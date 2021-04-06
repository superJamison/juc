package com.jms.jvm;

import java.lang.ref.Reference;
import java.util.concurrent.TimeUnit;

/**
 * jsp -l
 * jstack java进程号
 * jinfo -flag 具体参数     java进程号
 * jinfo -flags            java进程号
 *
 * java -XX:+PrintFlagsInitial
 * java -XX:+PrintFlagsFinal -version  (:=   =)
 * java -XX:+PrintCommandLineFlags -version
 *
 * 题目：做过JVM调优的常用参数
 *
 * -Xss == -XX:ThreadStackSize  单个线程的栈空间大小
 *
 *
 * https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html#BABDJJFI
 *
 * 典型配置：
 *  -Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 * -XX:SurvivorRatio=8   ==  Eden:S0:S1=8:1:1
 *
 *-XX:NewRation=2       ==   新生代：老年代=1:2
 *
 * -XX:MaxTenuringThreshold=15
 *
 * 题目：谈谈你对强引用，软引用，弱应用，虚引用
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 18:01
 *
 * 用进废退
 */
public class JvmParaDemo {
    public static void main(String[] args) {
        Reference reference;
        System.out.println("JVM");
        try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }

        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println(maxMemory / (double) 1024 / 1024);
        System.out.println(totalMemory / (double) 1024 / 1024);
    }
}
