package com.jms.jvm;

/**
 *
 * Throwable -->Error、Exception
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/6 22:33
 */
public class StackOverFlowDemo {
    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}
