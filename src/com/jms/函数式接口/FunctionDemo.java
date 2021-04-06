package com.jms.函数式接口;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/3 15:30
 *
 *      1.Function 函数式接口
 *      2.Predicate  判断型接口
 *      3.Consumer  消费型接口
 *      4.Supplier  供给型接口
 */
public class FunctionDemo {
    public static void main(String[] args) {
//        Function<String, Integer> function = new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return 1024;
//            }
//        };
//        System.out.println(function.apply("Jamison"));


//        Function<String, Integer> function = s -> {return s.length();};
//        System.out.println(function.apply("Jamison"));

        Predicate<String> predicate = s -> {return s.isEmpty();};
        System.out.println(predicate.test("jamison"));

        Supplier<String> supplier = () -> {return "Jamison";};
        System.out.println(supplier.get());

        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("My name is Jamison");

    }
}
