package com.fuoo.juc.p58;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"\t 正在写"+key);
        //暂停一会儿线程
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace(); }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"\t 写完了"+key);
    }

    public Object get(String key){
        Object result = null;
        System.out.println(Thread.currentThread().getName()+"\t 正在读"+key);
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace(); }
        result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t 读完了"+result);
        return result;
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

/*        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }*/



        /**
         添加读写锁
         */
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                readWriteLock.writeLock().lock();
                myCache.put(num+"",num+"");
                readWriteLock.writeLock().unlock();
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                readWriteLock.readLock().lock();
                myCache.get(num+"");
                readWriteLock.readLock().unlock();
            },String.valueOf(i)).start();
        }
    }


}
