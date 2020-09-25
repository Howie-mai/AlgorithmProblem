package com.mh;

import com.mh.bean.User;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Integer i = 0;
    private static final String word = "hello world";

    public static void main(String[] args) throws InterruptedException {
        char[] ch = word.toCharArray();

        Main main = new Main();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,0, TimeUnit.SECONDS,
                                                                new LinkedBlockingDeque<>(5),new ThreadPoolExecutor.AbortPolicy());

        executor.execute(() -> {
            System.out.println("哈哈哈哈哈哈1");
            main.printf(ch);
            System.out.println("哈哈哈哈哈哈12");
        });

        executor.execute(() -> {
            System.out.println("哈哈哈哈哈哈2");
            main.printf(ch);
            System.out.println("哈哈哈哈哈哈22");
        });

//        executor.execute(() -> {
//            printf(ch);
//        });
    }

    public synchronized void printf(char[] ch){
//        for(;i < ch.length;i++){
//            this.notify();
//            try {
//                this.wait();
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + ch[i]);
//        }
        System.out.println("123");
    }

}
