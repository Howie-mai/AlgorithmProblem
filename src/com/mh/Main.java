package com.mh;

import com.mh.bean.User;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int i = 0;
    private static final String word = "hello world";

    public static void main(String[] args) throws InterruptedException {

        char[] ch = word.toCharArray();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,1,0, TimeUnit.SECONDS,
                                                                new LinkedBlockingDeque<>(1),new ThreadPoolExecutor.AbortPolicy());

        executor.execute(() -> {
            printf(ch);
        });

        executor.execute(() -> {
            printf(ch);
        });

//        executor.execute(() -> {
//            printf(ch);
//        });
    }

    public static synchronized void  printf(char[] ch){
        for(;i < ch.length;i++){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(ch[i]);
        }
    }
}
