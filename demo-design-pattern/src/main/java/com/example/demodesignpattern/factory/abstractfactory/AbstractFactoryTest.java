package com.example.demodesignpattern.factory.abstractfactory;

public class AbstractFactoryTest {

    public static void main(String[] args) {
        JavaCourseFactory factory = new JavaCourseFactory();
        factory.createNote().edit();
        factory.createVideo().record();
    }

}
