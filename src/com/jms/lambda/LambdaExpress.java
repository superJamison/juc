package com.jms.lambda;

@FunctionalInterface
abstract interface Foo{
//    void sayHello();
    int add(int x, int y);

    default int mul(int x, int y){
        return x * y;
    }

    static int div(int x, int y){
        return x / y;
    }
}
/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 11:44
 */
public class LambdaExpress {
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("foo say hello");
//            }
//        };
//        foo.sayHello();

//        Foo foo = () -> {
//            System.out.println("lambdaExpress foo say hello");
//        };
//        foo.sayHello();

        Foo foo = (x, y) -> {return x + y;};
        System.out.println(foo.add(1, 2));

        System.out.println(foo.mul(2, 4));

        System.out.println(Foo.div(8, 4));
    }
}
