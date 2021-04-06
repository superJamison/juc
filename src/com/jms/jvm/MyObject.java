package com.jms.jvm;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/3 17:16
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader().getParent());//Exception in thread "main" java.lang.NullPointerException
        System.out.println(object.getClass().getClassLoader());//null  (bootstrapClassLoader)

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());//null   (bootstrapClassLoader)
        System.out.println(myObject.getClass().getClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@1b6d3586 (ExtClassLoader)
        System.out.println(myObject.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2  (AppClassLoader)
    }
}
