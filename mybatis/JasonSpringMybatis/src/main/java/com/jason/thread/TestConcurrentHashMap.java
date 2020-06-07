package com.jason.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-06-02 23:28
 **/
public class TestConcurrentHashMap {
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
            Map<String,String> map  =new ConcurrentHashMap();
            map.put("A","0");
            map.get("A");

            int max=2147483647;
//        System.out.println(Integer.toHexString(max));
//        System.out.println("A".hashCode()>>1);

        System.out.println(-1>>>31);
        System.out.println(-1>>31);
           // System.out.println(Integer.toHexString(max));
//        int initialCapacity=0;
//            int MAXIMUM_CAPACITY = 1 << 30;
//
//        int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ?
//                MAXIMUM_CAPACITY :
//                tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
//        System.out.println(cap);
    }
    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
