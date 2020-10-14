package com.fuoo.juc.p42;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuoo
 * @description 生产消费模型，生产一个消费一个
 * @date 2020/10/13 14:44
 */
public class Producer {
    private int i = 0;
    private int pie = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    /**
     * @Description:  synchronized   pie为0时生产pie++,pie不为0时则等待消费者消费
     *          虚假唤醒
     *         原因：在java多线程判断时，不能用if，程序出事出在了判断上面，
     *         突然有一添加的线程进到if了，突然中断了交出控制权，
     *         没有进行验证，而是直接走下去了，加了两次，甚至多次
     *         所有多线程判断使用 while
     * @Author: fuoo
     * @Date: 2020/10/14 10:45
     * @return: void
     */
    public synchronized void production() {

        /*
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

    /**
     * @Description: 新版Lock
     * @Author: fuoo
     * @Date: 2020/10/14 10:51
     * @return: void
     */
    public void production2() {
        lock.lock();
        try {
            while (pie != 0) {
                condition.await();
            }
            i++;
            pie++;
            System.out.println(i + "" + Thread.currentThread().getName() + "生产一个pie，剩余pie：" + pie);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void consumption2() {
        lock.lock();
        try {
            while (pie == 0) {
               condition.await();
            }

            i++;
            pie--;
            System.out.println(i + "" + Thread.currentThread().getName() + "消费一个pie，剩余pie：" + pie);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
