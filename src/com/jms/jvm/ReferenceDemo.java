package com.jms.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 1.强引用：不会回收
 * 2.软引用：只要内存够用，就不会回收，但是当内存不够用的话就会被回收
 * 3.弱引用：不管内存够不够用都会回收
 * 4.虚引用：
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 21:54
 */
public class ReferenceDemo {
    public static void main(String[] args) {
//        soft_memory_enough();
//        soft_memory_unEnough();

        Object o = new Object();
        WeakReference weakReference = new WeakReference(o);
        System.out.println(o);
        System.out.println(weakReference.get());

        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(weakReference.get());
    }


    private static void soft_memory_enough() {
        Object o = new Object();
        SoftReference softReference = new SoftReference<>(o);

        System.out.println(o);
        System.out.println(softReference.get());

        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(softReference.get());
    }

    //-Xmx5m -Xms5m -XX:+PrintGCDetails
    private static void soft_memory_unEnough() {
        Object o = new Object();
        SoftReference softReference = new SoftReference<>(o);

        System.out.println(o);
        System.out.println(softReference.get());

        o = null;

        try {
            Byte[] bytes = new Byte[30 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o);
            System.out.println(softReference.get());
        }
    }
}
