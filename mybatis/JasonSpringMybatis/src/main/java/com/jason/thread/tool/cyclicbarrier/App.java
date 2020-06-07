package com.jason.thread.tool.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-06-01 23:59
 **/
public class App {
    private ExecutorService executorService;
    private CyclicBarrier cyclicBarrier;
    private int parties;

    public App(int parties) {
        executorService = Executors.newFixedThreadPool(parties);
        cyclicBarrier = new CyclicBarrier(parties, () -> System.out.println(Thread.currentThread().getName() + " gets barrierCommand done"));
        this.parties = parties;
    }

    public static void main(String[] args) {
        App cyclicBarrierExample = new App(10);
        cyclicBarrierExample.example();
    }

    public void example() {
        for (int i = 0; i < parties; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " gets job done");
            });
        }
        executorService.shutdown();
    }
}
