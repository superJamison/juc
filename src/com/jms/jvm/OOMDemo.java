package com.jms.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;



/**
 * OOM ---> OutOfMemoryError
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 22:31
 */
public class OOMDemo {

    static class OOMTest {}

    public static void main(String[] args) {
//        gcOverHeadMethod();
        metaSpaceTest(args);
    }

    //-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
    private static void metaSpaceTest(String[] args) {
        int i = 0;

        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable throwable){
            System.out.println("***********第多少次后发生异常：" + i);
            throwable.printStackTrace();
        }
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     * GC时间过长，但是效果并不明显，进行多次GC的效果不明显，就会报GC overhead limit exceeded错误
     *
     * direct buffer memory
     * 在GC垃圾回收管辖范围之外的内存创建过多的对象，则会抛出次异常
     *
     * unable to create new native thread
     * 一个应用（进程）中创建了太多的线程，
     * ps -ef | grep java
     * kill -9 进程号
     * vim /etc/security/limits.d/90-nproc.conf
     *
     * java.lang.OurOfMemoryError:Metaspace
     * jinfo -flags 进程号
     * Metaspace 存放了以下信息：
     *      1.虚拟机加载的类信息
     *      2.常量池
     *      3.静态变量
     *      4.即时编译后的代码
     */

    private static void gcOverHeadMethod() {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Exception e){
            System.out.println("**********************");
            e.printStackTrace();
            throw e;
        }
    }
}
