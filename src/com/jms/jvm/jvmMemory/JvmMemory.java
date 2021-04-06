package com.jms.jvm.jvmMemory;

import java.util.Random;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/3 23:19
 */
public class JvmMemory {
    public static void main(String[] args) {
//        test();
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        String str = "My name is Jamison";
        while (true){
            str += str + new Random().nextInt(88888888) + new Random().nextInt(99999999);
        }

//        Byte[] bytes = new Byte[10 * 1023 * 1024];
    }

    private static void test() {
        long maxMemory = Runtime.getRuntime().maxMemory(); //返回Java虚拟机试图使用的最大内存量，-Xmx  MAX_MEMORY:3784310784字节、3609.0MB
        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java中内存总量，-Xms  TOTAL_MEMORY:255328256字节、243.5MB
        System.out.println("-Xmx  MAX_MEMORY:" + maxMemory + "字节、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("-Xms  TOTAL_MEMORY:" + totalMemory + "字节、" + (totalMemory / (double)1024 / 1024) + "MB");
    }
}
