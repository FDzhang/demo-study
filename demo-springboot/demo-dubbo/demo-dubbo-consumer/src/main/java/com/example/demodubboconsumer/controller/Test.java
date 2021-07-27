package com.example.demodubboconsumer.controller;

public class Test {
}

class Hobby {
    String type;
    String desc;
}

class BaseInfo{
    String name;
    Integer age;
}

class User{
    BaseInfo baseInfo;
    Hobby hobby;
}

class Vo{
    String name;
    Integer age;

    String type;
    String desc;
}
