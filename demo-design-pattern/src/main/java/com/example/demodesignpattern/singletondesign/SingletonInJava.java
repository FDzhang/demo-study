package com.example.demodesignpattern.singletondesign;

import java.awt.*;

/**
 * @author : zxq
 * @create : 2022/8/26 23:02
 */
// Java program implementing Singleton class
// with method name as that of class

// Class 1
// Helper class
public class SingletonInJava {
    // Static variable single_instance of type Singleton
    private static SingletonInJava single_instance = null;

    // Declaring a variable of type String
    public String s;

    // Constructor of this class
    // Here private constructor is used to
    // restricted to this class itself
    private SingletonInJava() {
        s = "Hello I am a string part of Singleton class";
    }

    // Method
    // Static method to create instance of Singleton class
    public static SingletonInJava Singleton() {
        // To ensure only one instance is created
        if (SingletonInJava.single_instance == null) {
            SingletonInJava.single_instance = new SingletonInJava();
        }
        return SingletonInJava.single_instance;
    }
}

// Class 2
// Main class
class GFG {
    // Main driver method
    public static void main(String args[]) {
        Desktop desktop = Desktop.getDesktop();
        // Instantiating Singleton class with variable x
        SingletonInJava x = SingletonInJava.Singleton();

        // Instantiating Singleton class with variable y
        SingletonInJava y = SingletonInJava.Singleton();

        // instantiating Singleton class with variable z
        SingletonInJava z = SingletonInJava.Singleton();

        // Now changing variable of instance x
        // via toUpperCase() method
        x.s = (x.s).toUpperCase();

        // Print and display commands
        System.out.println("String from x is " + x.s);
        System.out.println("String from y is " + y.s);
        System.out.println("String from z is " + z.s);
        System.out.println("\n");

        // Now again changing variable of instance x
        z.s = (z.s).toLowerCase();

        System.out.println("String from x is " + x.s);
        System.out.println("String from y is " + y.s);
        System.out.println("String from z is " + z.s);
    }
}
