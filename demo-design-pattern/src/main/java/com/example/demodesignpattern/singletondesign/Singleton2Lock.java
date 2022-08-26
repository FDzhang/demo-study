package com.example.demodesignpattern.singletondesign;

/**
 * @author : zxq
 * @create : 2022/8/26 22:50
 */
// Double Checked Locking based Java implementation of
// singleton design pattern
public class Singleton2Lock {
    private static volatile Singleton2Lock obj = null;

    private Singleton2Lock() {
    }

    public static Singleton2Lock getInstance() {
        if (Singleton2Lock.obj == null) {
            // To make thread safe
            synchronized (Singleton2Lock.class) {
                // check again as multiple threads
                // can reach above step
                if (Singleton2Lock.obj == null) {
                    Singleton2Lock.obj = new Singleton2Lock();
                }
            }
        }
        return Singleton2Lock.obj;
    }
}