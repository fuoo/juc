package com.fuoo.juc.p59;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author fuoo
 * @date 2020/11/13 15:24
 * @description:
 */
public class Main {
    public static void main(String[] args) throws Exception{
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        /*queue.add(1);
        queue.add(2);
        queue.add(3);
        //queue.add(4);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //System.out.println(queue.remove());*/

/*        queue.offer(1);
        queue.offer(1);
        queue.offer(1);
        queue.offer(1);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());*/

/*        queue.put(1);
        queue.put(1);
        queue.put(1);
        queue.put(1);
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());*/
    }
}
