package com.mh;

import com.mh.niuke.Edge;

import java.util.*;

/**
 * ClassName：
 * Time：20/9/4 下午10:20
 * Description：随机数生成算法
 *
 * @author mh
 */
public class Main8 {
    public static void main(String[] args) {
        double sum = 250;
        for (int i = 1; i <= 9; i++) {
            double a = Math.pow(1.5,i);
            double b = 250 / a;
            sum += b;
        }
        System.out.println(sum);
    }

}
