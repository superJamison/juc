package com.jms.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 22:19
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o, queue);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());

        System.out.println("========================");
        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());

    }
}
