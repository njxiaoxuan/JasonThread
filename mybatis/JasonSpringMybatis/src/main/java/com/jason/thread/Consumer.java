package com.jason.thread;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-28 23:45
 **/
public class Consumer  implements Runnable {
    private List<String> deque;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public Consumer(List<String> deque, int maxSize, Lock lock, Condition condition) {
        this.deque = deque;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i=0;
        while(true)
        {
            lock.lock();
            try{
                if(deque.size()>0)
                {
                    System.out.println("消费者->消费消息："+deque.get(0));
                    deque.remove(0);
                    try {
                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        long  t1=System.currentTimeMillis();
                        condition.await();
                        long t2=System.currentTimeMillis();
                        System.out.println("consumer 被唤醒重新加入就绪队列，耗时="+(t2-t1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }finally {
                lock.unlock();
            }
        }
    }
}
