package com.mh.bean;

/**
 * ClassName：
 * Time：20/10/23 上午11:09
 * Description：
 *
 * @author mh
 */
public class A {

    {
        System.out.print("(3)");
    }

    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    public A() {
        System.out.print("(2)");
    }


    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}
