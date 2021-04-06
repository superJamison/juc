package com.jms.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 22:22
 */
public class PhantomReferenceDemo
{
    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o, queue);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());

        System.out.println("============");
        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());

    }
}
