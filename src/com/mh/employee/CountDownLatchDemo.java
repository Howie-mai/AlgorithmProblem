package com.mh.employee;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ClassName：
 * Time：2021/4/19 4:00 下午
 * Description：火箭发射倒计时，先发射火箭大的，再启动其他小的应用
 *
 * @author mh
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
//        general();
        countDownLatchTest();
    }

    public static void general() {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完自习，离 开教室");
            }, "Thread" + i);
        }
        System.out.println(Thread.activeCount());
        while (Thread.activeCount() > 2) {
            System.out.println(Thread.activeCount());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t=====班长最后关门走人");
    }

    public static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t被灭");
                countDownLatch.countDown();
            }, "Thread" + i).start();
        }
        System.out.println(Thread.activeCount());
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t=====秦统一");
    }
}
