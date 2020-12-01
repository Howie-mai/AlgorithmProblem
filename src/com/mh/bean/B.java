package com.mh.bean;

/**
 * ClassName：
 * Time：20/10/23 上午11:09
 * Description：
 *
 * @author mh
 */
public class B extends A {
    static {
        System.out.print("(6)");
    }

    private int i = test();
    private static int j = method();


    public B() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method(){
        System.out.print("(10)");
        return 1;
    }
}
