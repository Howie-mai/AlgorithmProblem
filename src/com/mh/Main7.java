package com.mh;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName：
 * Description：
 */
public class Main7 {

    private static int count = 0;

    /**
     * 一共要执行多久，单位为ms
     */
    private static int timeTotal = 1000 * 60 * 20;

    /**
     * 隔多长时间增加一次，单位为ms
     */
    private static int sleepTime = 1000 * 5;

    /**
     * 一共要执行多少次
     */
    private static int totalCount = timeTotal / sleepTime;

    /**
     * 要增加的总人数
     */
    private static int UserNum = 5000;

    /**
     * 要增加的总步数
     */
    private static int StepNum = 500000;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2), new ThreadPoolExecutor.AbortPolicy());

        System.out.println("循环多少次===" + totalCount);

        executor.execute(Main7::addNum);

        executor.shutdown();
    }

    static double rand01(double[] r) {
        double base, u, v, p, temp1, temp2, temp3;
        // 基数 2的倍数 可以修改这个来改变取值
        base = 216.0;
        // 两个常数随意取
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

    /**
     * 正态分布的随机数生成，平均数太小，方差太大会取得负数
     * @param avg 平均数
     * @param fc 方差
     * @param r 随机种子
     * @return double
     */
    static double randZT(double avg, double fc, double[] r) {
        double total = 0.0;
        double result;
        /**
         * 方便计算 上式分母中的根号便可以忽略， 实现正态分布
         */
        for (int i = 0; i < 12; i++) {
            total += rand01(r);
        }
        /**
         * 取随机数
         */
        result = avg + fc * (total - 6.0);
        return result;
    }

    private static void addNum() {
        double[] userSeed = {5.0};
        double[] stepSeed = {10.0};
//        Random random = new Random();
        int stepAvg = StepNum / totalCount <= 0 ? 10000 :  StepNum / totalCount;
        int stepMax = stepAvg + 50;
        int stepMin = Math.max(stepAvg - 100, 1);
        System.out.println("步数平均值===" + stepAvg);
        System.out.println("步数左区间===" + stepMin);
        System.out.println("步数右区间===" + stepMax);

        int userAvg = UserNum / totalCount <= 0 ? 50 : UserNum / totalCount;
//        userAvg += 5;
        int userMax = userAvg + 5;
        int userMin = Math.max(userAvg - 10, 1);
        System.out.println("用户平均值===" + userAvg);
        System.out.println("用户左区间===" + userMin);
        System.out.println("用户右区间===" + userMax);

        while (count <= totalCount) {

            int user = (int)randZT(userAvg,10.0,userSeed);
//            int user =  userMin +(int)((userMax - userMin) * Main8.rand01(userSeed));

            int step = (int)randZT(stepAvg,100.0,stepSeed);

//            UserNum -= Math.abs(user);
            UserNum -= user;
            System.out.println("" + new Date() + "===增加人数" + user);

//            StepNum -= Math.abs(step);
            StepNum -= step;
            System.out.println("" + new Date() + "===增加步数" + step);

            count++;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("用户剩余====" + UserNum);
        System.out.println("步数剩余====" + StepNum);

        /**
         * 最后补足步数和用户
         */
        if (UserNum > 0) {
            UserNum -= UserNum;
        }

        if(StepNum > 0){
            StepNum -= StepNum;
        }
    }
}
