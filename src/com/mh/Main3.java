package com.mh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName：
 * Time：20/7/21 上午9:07
 * Description：死锁
 * Author： mh
 */
public class Main3 {
    public static void main(String[] args) {
       deadLine();
    }

    public static void deadLine(){
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + "获取lock1成功");
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "获取lock2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "获取lock2成功");
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "获取lock1");
                }
            }
        }).start();
    }
}
