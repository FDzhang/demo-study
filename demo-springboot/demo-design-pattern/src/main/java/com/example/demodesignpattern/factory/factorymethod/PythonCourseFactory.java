package com.example.demodesignpattern.factory.factorymethod;


import com.example.demodesignpattern.factory.ICourse;
import com.example.demodesignpattern.factory.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
