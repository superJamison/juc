package com.jms.jvm;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * WeakHashMap   只要key置为空，那么WeakHashMap的对应key和value都会被GC
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 22:08
 */
public class weakHashMapDemo {
    public static void main(String[] args) {
//        myHashmap();
        myWeakHashmap();
    }

    private static void myWeakHashmap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer("2");
        String value = "s";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    private static void myHashmap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer("1");
        String value = "abc";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
