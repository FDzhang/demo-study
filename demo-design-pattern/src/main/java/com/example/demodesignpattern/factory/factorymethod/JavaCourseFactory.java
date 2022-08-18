package com.example.demodesignpattern.factory.factorymethod;


import com.example.demodesignpattern.factory.ICourse;
import com.example.demodesignpattern.factory.JavaCourse;

public class JavaCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
