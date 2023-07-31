package cn.fd.javafx1;

import javafx.util.Builder;

public class PersonBuilder implements Builder<Person> {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("PersonBuilder setName方法");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("PersonBuilder setAge方法");
        this.age = age;
    }

    @Override
    public Person build() {
        System.out.println("PersonBuilder build方法");

        return new Person(name, age);
    }
}
