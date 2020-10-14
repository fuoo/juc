package com.fuoo.juc.p39;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuoo
 * @description 买票资源类
 * @date 2020/10/13 13:57
 */
public class Ticket {
    private  int num = 30;
    private Lock lock = new ReentrantLock();

    /**
     * @Description: 使用 synchronized
     * @Author: fuoo
     * @Date: 2020/10/14 10:44
     * @return: void
     */
    public synchronized void buyTicket(){
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "  卖出票数号：" + num);
            num--;
        } else {
            System.out.println("票已买完");
        }
    }

    /**
     * @Description: 使用Lock
     * @Author: fuoo
     * @Date: 2020/10/14 10:44
     * @return: void
     */
    public void buyTicket2() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "  卖出票数号：" + num);
                num--;
            } else {
                System.out.println("票已买完");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
