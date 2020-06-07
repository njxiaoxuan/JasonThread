package com.jason.thread.tool.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-30 23:51
 **/
public class App {
    public static int maxSize=3;
    public static CountDownLatch  countDownLatch=new CountDownLatch(maxSize);

    public static void main(String[] args) throws InterruptedException {
            System.out.println("等待线程计数器："+countDownLatch.getCount());
            Thread t_main=new Thread(){
                @Override
                public void run() {
                    countDownLatch.countDown();//通知AQS更新state状态值减一。
                    /*
                       后续还有其他业务逻辑，需要处理，不像join指令，需要等待当前线程消亡，由JVM通知等待在该线程上的线程。
                     */
                }
            };
        t_main.setName("t_main");
        Thread t_main1=new Thread(){
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        };
        t_main1.setName("t_main1");
        Thread t_main2=new Thread(){
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        };
        t_main.start();
        Thread.sleep(1000);
        System.out.println("等待线程计数器："+countDownLatch.getCount());

        t_main1.start();
        Thread.sleep(1000);
        System.out.println("等待线程计数器："+countDownLatch.getCount());

        t_main2.start();
        Thread.sleep(1000);
        System.out.println("等待线程计数器："+countDownLatch.getCount());
        try {
            countDownLatch.await();
            System.out.println("等待线程计数器："+countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
