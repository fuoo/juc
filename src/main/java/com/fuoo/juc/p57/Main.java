package com.fuoo.juc.p57;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author fuoo
 * @date 2020/11/13 11:19
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                try {
                    while (true) {
                        // 获取执行限权
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "正在执行");
                        TimeUnit.SECONDS.sleep(5);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程" + i).start();
        }
    }
}
