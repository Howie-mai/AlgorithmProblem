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
    public static void main(String[] args){
        double sum = 0;
        int i = 10;
        while (true){
            double a = Math.pow(2,i);
            System.out.println(a);
            i++;
            sum += (500/a);
            System.out.println(sum);
        }
    }
}
