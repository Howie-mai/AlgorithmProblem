package com.mh.bean;

/**
 * ClassName：
 * Time：20/10/23 上午11:09
 * Description：
 *
 * @author mh
 */
public class C {
    public C() {
        System.out.println("C class");
    }

    public static void main(String[] args) {
        A b = new A();
        System.out.println();
        B b1 = new B();
    }
}
