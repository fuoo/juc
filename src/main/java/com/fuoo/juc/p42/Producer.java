package com.fuoo.juc.p42;

/**
 * @author fuoo
 * @description 生产消费模型，生产一个消费一个
 * @date 2020/10/13 14:44
 */
public class Producer {
    private int i = 0;
    private int pie = 0;

    // synchronized   pie为0时生产pie++,pie不为0时则等待消费者消费
    public synchronized void production() {

        /* 虚假唤醒
        原因：在java多线程判断时，不能用if，程序出事出在了判断上面，
        突然有一添加的线程进到if了，突然中断了交出控制权，
        没有进行验证，而是直接走下去了，加了两次，甚至多次
        */
        while (pie != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        i++;
        pie++;
        System.out.println(i + "" + Thread.currentThread().getName() + "生产一个pie，剩余pie：" + pie);
        notifyAll();
    }

    public synchronized void consumption() {
        while (pie == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i++;
        pie--;
        System.out.println(i + "" + Thread.currentThread().getName() + "消费一个pie，剩余pie：" + pie);
        notifyAll();
    }
}
