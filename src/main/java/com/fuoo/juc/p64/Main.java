package com.fuoo.juc.p64;

import java.util.concurrent.*;

/**
 * @author fuoo
 * @date 2020/11/13 16:52
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 线程池的创建
        */
        // 创建固定数的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 创建一个线程的线程池
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        // 创建可扩容的线程池
        ExecutorService executorService2 = Executors.newCachedThreadPool();


        for (int i = 0; i < 10; i++) {
            executorService2.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }

        // 手动创建线程池
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(2,
                5, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(()->{
                System.out.println("手写" + Thread.currentThread().getName());
            });
        }

    }
}
