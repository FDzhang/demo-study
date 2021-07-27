package com.example.demoasync.async;

/**
 * @author zhangxinqiang
 * @create 2021/5/22 16:28
 */
public class Task1 implements Runnable{
    @Override
    public void run() {
        print();
    }

    public void print() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + " --- " + i);
        }
    }
}
