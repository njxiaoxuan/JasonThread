package com.jason.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-29 00:00
 **/
public class App {
    public static void main(String[] args) {
            List<String> list= new LinkedList<String>();
            int maxSize=5;
            Lock lock=new ReentrantLock(true);
            Condition   condition=lock.newCondition();
            Condition   condition1=lock.newCondition();
            System.out.println(condition+" -------- "+condition1);

            Thread  product=new Thread(new Productor(list,maxSize,lock,condition),"product");
            Thread  consumer=new Thread(new Consumer(list,maxSize,lock,condition),"consumer");
            product.start();
            consumer.start();


    }
}
