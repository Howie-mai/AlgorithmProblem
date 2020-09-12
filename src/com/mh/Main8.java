package com.mh;

import java.util.Random;

/**
 * ClassName：
 * Time：20/9/4 下午10:20
 * Description：随机数生成算法
 *
 * @author mh
 */
public class Main8 {

    static double rand01(double[] r) {
        double base, u, v, p, temp1, temp2, temp3;
        base = 216.0;
        u = 17.0;
        v = 139.0;

        // 计算总值
        temp1 = u * r[0] + v;
        // 计算商
        temp2 = (int) (temp1 / base);
        // 计算余数
        temp3 = temp1 - temp2 * base;
        // 更换随机种子
        r[0] = temp3;
        //随机数
        p = r[0] / base;

        return p;
    }

    static double randZT(double avg, double fc, double[] r) {
        int i;
        double total = 0.0;
        double result;
        for (i = 0; i < 12; i++) {
            total += rand01(r);
        }
        result = avg + fc * (total - 6.0);
        return result;
    }

    public static void main(String[] args) {
        int i, j;
        double[] r = {5.0};
        int m = 10, n = 20;
        // 正态分布均值
        double u = 15.0;
        // 方差
        double t = 3.5;
        Random random = new Random(10);
        for (j = 0; j < 10; j++) {
//            for (i = 0;i < 10;i++){
//                System.out.printf("%10.5f    ",m + (n-m)*rand01(r));
//            }
//            System.out.println("");
//            System.out.printf("%10.5f\n",randZT(u,t,r,m,n));
        }
    }
}
