package com.fuoo.juc.p46;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuoo
 * @date 2020/10/14 16:00
 * @description: 排序运行资源类
 */
public class SortRun {
    // 标志位
    private int signNum = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public synchronized void synchronizedPrint(int sign, int time){
        String threadName = Thread.currentThread().getName();
        while (true) {
            while (signNum != sign) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 1; j <= time; j++) {
                System.out.println(threadName + "--->" + j);
            }
            System.out.println();

            if (signNum < 3) {
                signNum++;
            } else {
                signNum = 1;
            }
            notifyAll();
        }
    }

    public void lockPrint(int sign, int time){
        while (true) {
            lock.lock();
            try {
                String threadName = Thread.currentThread().getName();
                while (signNum != sign) {
                    //condition.await();//this.wait();
                    // 精准唤醒
                    switch (threadName) {
                        case "A":
                            conditionA.await();
                            break;
                        case "B":
                            conditionB.await();
                            break;
                        case "C":
                            conditionC.await();
                            break;
                        default:
                            break;
                    }
                }

                for (int j = 1; j <= time; j++) {
                    System.out.println(threadName + "--->" + j);
                }
                System.out.println();

                if (signNum < 3) {
                    signNum++;
                } else {
                    signNum = 1;
                }
                //condition.signalAll(); //notifyAll();
                // 精准唤醒下一个线程
                switch (threadName) {
                    case "A":
                        conditionB.signal();
                        break;
                    case "B":
                        conditionC.signal();
                        break;
                    case "C":
                        conditionA.signal();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
