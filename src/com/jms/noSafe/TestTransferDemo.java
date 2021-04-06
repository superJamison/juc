package com.jms.noSafe;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 17:33
 */
public class TestTransferDemo {

    public void changeValue(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferDemo testTransferDemo = new TestTransferDemo();

        String str = "abc";
        testTransferDemo.changeValue(str);
        System.out.println(str);
    }
}
