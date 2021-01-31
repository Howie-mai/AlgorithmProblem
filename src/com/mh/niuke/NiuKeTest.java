package com.mh.niuke;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

/**
 * ClassName：
 * Time：2021/1/5 2:30 下午
 * Description：
 *
 * @author mh
 */
public class NiuKeTest {
    private static void test(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 == 0) {
                    throw new NullPointerException();
                } else {
                    System.out.print(i);
                }
            } finally {
                System.out.print("e");
            }
        }
    }

    public static void main(String[]args) {
        try {
            test(new int[] {0, 1, 2, 3, 4, 5});
        } catch (Exception e) {
            System.out.print("E");
        }
    }
}
