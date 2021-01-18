package com.example.demodesignpattern.singleton.lazy;

/**
 * 懒汉式单例
 * 这种形式兼顾饿汉式的内存浪费，也兼顾synchronized性能问题
 * 完美地屏蔽了这两个缺点
 * <p>
 * 全程没有用到synchronized
 * 性能最优的一种写法
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton() {
        // 防止使用反射恶意调用,破坏单例
        if (LazyHolder.LAZY != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * 懒汉式单例
     * LazyHolder 里面的逻辑需要等到外部方法调用时才执行
     * 巧妙利用了内部类的特性
     * JVM底层独行逻辑,完美地避免了线程安全阿题
     */
    public static final LazyInnerClassSingleton getInstance() {
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.LAZY;
    }

    // 默认不加载
    private static class LazyHolder {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}
