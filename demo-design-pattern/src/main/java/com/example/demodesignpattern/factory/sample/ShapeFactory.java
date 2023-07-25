package com.example.demodesignpattern.factory.sample;

public class ShapeFactory {

    public Shape getShape(String type){
        if (type.equals("type1")) {
            return new Shape1();
        } else if (type.equals("type2")) {
            return new Shape2();
        } else {
            throw new UnsupportedOperationException("unkonwn shape");
        }
    }
}
