package com.fuoo.juc.p53;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 多线程的实现有三种方法
 */
public class Main {
    public static void main(String[] args) throws Exception{
        MyThread3 myThread3 = new MyThread3();
        FutureTask futureTask = new FutureTask(myThread3);
        new Thread(futureTask, "123").start();
        System.out.println(futureTask.get());
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("使用callable实现多线程");
        return 1024;
    }
}
