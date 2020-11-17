package com.fuoo.juc.p71;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author fuoo
 * @date 2020/11/17 9:23
 * @description: ForkJoin 分支合并框架
 */
public class Main {
    public static void main(String[] args) throws Exception{
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        System.out.println(submit.get());
        forkJoinPool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {
    private final static int LIMIT_NUM = 10;

    private int minNum;
    private int maxNum;
    private int sum;

    public MyTask(int minNum, int maxNum) {
        this.minNum = minNum;
        this.maxNum = maxNum;
    }

    @Override
    protected Integer compute() {
        if ((maxNum - minNum) <= LIMIT_NUM) {
            for (int i = minNum; i <= maxNum; i++) {
                sum += i;
            }
        } else {
            int i = (minNum + maxNum) / 2;
            MyTask myTask = new MyTask(minNum, i);
            MyTask myTask1 = new MyTask(i + 1, maxNum);
            myTask.fork();
            myTask1.fork();

            sum = myTask.join() + myTask1.join();
        }
        return sum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


}