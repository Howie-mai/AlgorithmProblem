package com.mh.leetcode.thread;

/**
 * ClassName：
 * Time：20/9/14 下午2:42
 * 三个不同的线程将会共用一个 Foo 实例。
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * @author mh
 */
public class Foo {

    private int i = 1;
    Object lock = new Object();

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock){
            while (i != 1){
                lock.wait();
            }
            printFirst.run();
            i++;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock){
            while (i != 2){
                lock.wait();
            }
            printSecond.run();
            i++;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock){
            while (i != 3){
                lock.wait();
            };
            printThird.run();
            i++;
            lock.notifyAll();
        }
    }
}
