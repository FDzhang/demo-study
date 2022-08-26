package com.example.demodesignpattern.singletondesign;


import java.lang.reflect.Constructor;

/**
 * 加载单例类时，不会加载内部类，因此在加载类时不会创建对象。仅当调用 getInstance（） 方法时，才会创建内部类。因此，它可能看起来像是急于初始化，但它是惰性初始化。
 * 这是使用最广泛的方法，因为它不使用同步。
 *
 * @author : zxq
 * @create : 2022/8/26 23:29
 */
// Java code for Bill Pugh Singleton Implementation
public class BillPughGFG {

    private BillPughGFG() {
        // private constructor
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton {
        private static final BillPughGFG INSTANCE = new BillPughGFG();
    }

    public static BillPughGFG getInstance() {
        return BillPughSingleton.INSTANCE;
    }

    public static void main1(String[] args) {
        BillPughGFG instance1 = BillPughGFG.getInstance();
        BillPughGFG instance2 = null;
        try {
            Constructor[] constructors =
                    BillPughGFG.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (BillPughGFG) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("instance1.hashCode():- "
                + instance1.hashCode());
        System.out.println("instance2.hashCode():- "
                + instance2.hashCode());
    }

    public static void main(String[] args) {
        Runtime instance1 = Runtime.getRuntime();
        Runtime instance2 = null;
        try {
            Constructor[] constructors =
                    Runtime.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (Runtime) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("instance1.hashCode():- "
                + instance1.hashCode());
        System.out.println("instance2.hashCode():- "
                + instance2.hashCode());
    }
}


