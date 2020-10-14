package com.fuoo.juc.p46;

/**
 * @author fuoo
 * @date 2020/10/14 10:47
 * @description 多线程按照顺序调用 A->B->C
 * 三个线程启动，要求如下：
 * aa打印5次，bb打印10次 cc打印15次
 * 接着
 * aa打印5次，bb打印10次 cc打印15次
 * 循环10次
 *
 *
 *
 * 1、 高内聚低耦合的前提下，线程操作资源类
 * 2、 判断/干活/通知
 * 3、 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * 4、 标志位
 */
public class Main {
    public static void main(String[] args) {
/*        SortRun sortRun = new SortRun();
        new Thread(() -> {sortRun.synchronizedPrint(2, 10);}, "B").start();
        new Thread(() -> {sortRun.synchronizedPrint(1, 5);}, "A").start();
        new Thread(() -> {sortRun.synchronizedPrint(3, 15);}, "C").start();*/

        SortRun sortRun2 = new SortRun();
        new Thread(() -> {sortRun2.lockPrint(2, 10);}, "B").start();
        new Thread(() -> {sortRun2.lockPrint(1, 5);}, "A").start();
        new Thread(() -> {sortRun2.lockPrint(3, 15);}, "C").start();
    }
}
