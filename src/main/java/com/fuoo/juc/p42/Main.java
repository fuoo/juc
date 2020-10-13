package com.fuoo.juc.p42;

import com.fuoo.juc.p39.Ticket;

/**
 * @author fuoo
 * @description
 * @date 2020/10/13 14:01
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Producer producer = new Producer();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                producer.production();
            }
        }, "生产___").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                producer.consumption();
            }
        }, "消费___").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                producer.production();
            }
        }, "生产___").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                producer.consumption();
            }
        }, "消费___").start();

    }
}
