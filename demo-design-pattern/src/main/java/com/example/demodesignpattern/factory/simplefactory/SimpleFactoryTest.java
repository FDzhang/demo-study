package com.example.demodesignpattern.factory.simplefactory;


import com.example.demodesignpattern.factory.ICourse;
import com.example.demodesignpattern.factory.JavaCourse;
import com.example.demodesignpattern.factory.PythonCourse;

/**
 * 小作坊式的工厂模型
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        CourseFactory factory = new CourseFactory();
        ICourse course = factory.create(JavaCourse.class);
        course.record();

        ICourse course1 = factory.create(PythonCourse.class);
        course1.record();
    }
}
