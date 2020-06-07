package com.jason.thread;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-28 23:44
 **/
public class Productor implements Runnable{
    private List<String>  deque;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public Productor(List<String> deque, int maxSize, Lock lock, Condition condition) {
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
                if(deque.size()>=maxSize)
                {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("生产者->生产消息:"+(++i));
                deque.add("i="+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }
}
