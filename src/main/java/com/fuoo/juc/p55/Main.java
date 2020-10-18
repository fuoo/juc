package com.fuoo.juc.p55;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 */
public class Main {
    public static void main(String[] args) {
        // 模拟6个同学都出门后才可以关门
/*        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println("同学xx" + "出门了");
            }).start();
        }
        System.out.println("关门");*/

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println("同学xx" + "出门了");
                // 计数减一
                countDownLatch.countDown();
            }).start();
        }

        try {
            // 等待计数减为零前都阻塞
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("关门");
    }
}
