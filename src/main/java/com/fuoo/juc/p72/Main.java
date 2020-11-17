package com.fuoo.juc.p72;

import java.util.concurrent.CompletableFuture;

/**
 * @author fuoo
 * @date 2020/11/17 10:14
 * @description: 异步回调
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + "异步调用");
        });

        System.out.println(Thread.currentThread());
        voidCompletableFuture.get();


        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("可返还的异步调用");
            int i = 10/0;
            return 1024;
        });
        CompletableFuture<Integer> exceptionally = supplyAsync.whenComplete((r, e) -> {
            System.out.println("结果：" + r);
            System.out.println("异常：" + e);
        }).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return 0;
        });

        System.out.println(exceptionally.get());
    }
}
