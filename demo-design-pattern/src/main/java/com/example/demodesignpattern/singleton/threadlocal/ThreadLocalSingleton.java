package com.example.demodesignpattern.singleton.threadlocal;

/**
 * ThreadLocal 不能保证其
 * 创建的对象是全局唯一，但是能保证在单个线程中是唯一的，天生的线程安全
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL_INSTANCE =
            ThreadLocal.withInitial(ThreadLocalSingleton::new);

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL_INSTANCE.get();
    }
}
