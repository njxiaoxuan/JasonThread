package com.jason.thread.tool.semaphore;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-31 12:56
 **/
public class App {
    public static final  int MAX_THREAD=30;
    public static volatile Semaphore  semaphore=new Semaphore(MAX_THREAD);
    public static volatile ExecutorService pool=Executors.newFixedThreadPool(5000);

    public static void main(String[] args) {
           for(int i=0;i<5000;i++)
               pool.execute(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           long t1=System.currentTimeMillis();
                           System.out.println("线程ID： "+Thread.currentThread().getId()+
                                   "---当前等待许可证的线程数="+semaphore.getQueueLength()
                                   +"---当前可用的许可证="+semaphore.availablePermits());
                           semaphore.acquire();
                           long t2=System.currentTimeMillis();
                           System.out.println("线程ID："+Thread.currentThread().getId()+"  execute use time="+(t2-t1));
                           Thread.sleep(5000);
                           System.out.println("此刻在等待许可证的线程集合:"+JSONObject.fromObject(semaphore.getQueueLength()).toString());
                           semaphore.release();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               });

           pool.shutdown();
    }
}
