package com.mh;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName：
 * Time：20/9/3 上午9:00
 * Description：
 *
 * @author mh
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

            int user = (int)Main8.randZT(userAvg,10.0,userSeed);
//            int user =  userMin +(int)((userMax - userMin) * Main8.rand01(userSeed));
//            if(user <= userAvg){
////                user += userAvg;
//                userMax *= 2;
//                userMin += userAvg/2;
//            }else {
//                userMax /= 2;
//                userMin -= userAvg/2;
//            }

            int step = (int)Main8.randZT(stepAvg,100.0,stepSeed);
//            int step = stepMin +(int)((stepMax - stepMin) * Main8.rand01(stepSeed));
//            if(step < stepAvg){
////                step += stepAvg;
//                stepMax *= 2;
//                stepMin += userAvg/2;
//            }else {
//                stepMax /= 2;
//                stepMin -= userAvg/2;
//            }

//            UserNum -= Math.abs(user);
            UserNum -= user;
            System.out.println("" + new Date() + "===增加人数" + user);

//            StepNum -= Math.abs(step);
            StepNum -= step;
            System.out.println("" + new Date() + "===增加步数" + step);

            count++;
//            try {
//                Thread.sleep(sleepTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.out.println("用户剩余====" + UserNum);
        System.out.println("步数剩余====" + StepNum);

        if (UserNum > 0) {
            UserNum -= UserNum;
        }

        if(StepNum > 0){
            StepNum -= StepNum;
        }
    }

}
