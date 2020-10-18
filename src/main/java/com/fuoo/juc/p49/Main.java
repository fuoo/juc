package com.fuoo.juc.p49;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 题目： 请举例说明集合类是不安全的
 * 1、故障现象 java.util.ConcurrentModificationException
 * 2、导致原因
 * 3、解决方法
 *      3.1、Vector 线程安全
 *      3.2、Collections.synchronizedList(new ArrayList<>());
 *      3.3、CopyOnWriteArrayList()
 * 4、快速优化
 */
public class Main {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//new Vector(); //new ArrayList();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add("a");
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
