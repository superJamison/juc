package com.jms.noSafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 12:21
 *
 * Collection
 * Collections 集合接口的工具类
 *
 * 1.故障现象：
 *  java.util.ConcurrentModificationException 并发修改异常
 *
 * 2.导致原因；
 *
 * 3.故障解决：
 *  3.1、new Vector<>();
 *  3.2、Collections.synchronizedList(new ArrayList())
 *  3.3、new CopyOnWriteArrayList<>();
 *      写时复制   读写分离   在添加数据的时候，先新建一个数组，待数据添加到新建的数组后
 *      ，再将list的引用指向新建的数组，那么原先的数组将会失去引用，jvm就会回收掉旧的数组
 *
 * 4.优化建议：
 *
 *
 */
public class NoSafe {

    public static void main(String[] args) {
//        listNoSafe();

//        setNoSafe();

//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

    private static void setNoSafe() {
        //        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNoSafe() {
        //        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.forEach(System.out::println);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
