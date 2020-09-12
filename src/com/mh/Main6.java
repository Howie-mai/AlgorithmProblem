package com.mh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class Main6 {

    private static int stepCount = 0;

    private static int userCount = 0;

    /**
     * 一共要执行多久，单位为ms
     */
    private static int timeTotal = 1000 * 30;

    /**
     * 隔多长时间增加一次，单位为ms
     */
    private static int sleepTime = 1000 * 1;

    /**
     * 一共要执行多少次
     */
    private static int totalCount = timeTotal / sleepTime;

    /**
     * 要增加的总人数
     */
    private static long UserNum = 1000;

    /**
     * 要增加的总步数
     */
    private static long StepNum = 500000;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2), new ThreadPoolExecutor.AbortPolicy());

        System.out.println("循环多少次===" + totalCount);

        executor.execute(Main6::addUserNum);

        executor.execute(Main6::addStepNum);

        executor.shutdown();
    }

    private static void addStepNum() {
        Random random = new Random();
        int stepAvg = (int) (StepNum / totalCount) <= 0 ? 10000 : (int) (StepNum / totalCount);
        int min = Math.max(stepAvg - 500, 1);
        System.out.println("步数左区间===" + min);
        System.out.println("步数右区间===" + stepAvg);
        while (stepCount <= totalCount) {
            long s = random.nextInt(stepAvg - min + 1) + min;
            if (stepCount == totalCount) {
                s = StepNum;
            }
            System.out.println("" + new Date() + "===增加步数" + s);
            StepNum -= s;
            stepCount++;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("步数剩余====" + StepNum);

    }

    private static void addUserNum() {
        Random random = new Random();
        int userAvg = (int) (UserNum / totalCount) <= 0 ? 50 : (int) (UserNum / totalCount);
        int min = Math.max(userAvg - 20, 1);
        System.out.println("用户左区间===" + min);
        System.out.println("用户右区间===" + userAvg);
        while (userCount <= totalCount) {
            long s = random.nextInt(userAvg - min + 1) + min;
            System.out.println("" + new Date() + "===增加人数" + s);
            if (userCount == totalCount) {
                s = UserNum;
            }
            UserNum -= s;
            userCount++;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("用户剩余====" + UserNum);
    }

}
