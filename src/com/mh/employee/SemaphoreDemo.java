package com.mh.employee;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ClassName：
 * Time：2021/4/19 4:09 下午
 * Description：可以代替Synchronize和Lock
 *
 * @author mh
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 有几个位置可以抢
        Semaphore semaphore = new Semaphore(3);
        // 模拟有多少个进程来抢
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车 位");
                    try {
                        TimeUnit.SECONDS.sleep(3);//停车3s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停车3s 后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },"Thread" + i).start();
        }
    }
}
