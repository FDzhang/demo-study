package com.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * 3. Optional 类
 * 在 Java8 之前，空指针异常是编码时最需要注意的异常，我们往往都需要手动对变量进行 null 值判断，对可能的空指针异常进行捕获处理。Java8 提供的 Optional 类可以以比较优雅的方式进行空值判断，解决空指针异常。
 * @author zhangxinqiang
 * @create 2022/2/15 20:05
 */
public class OptionalExample {
    private Person person;
    private Car car;
    private Insurance insurance;

    @Before
    public void init() {
        insurance = new Insurance("Tesla");
        car = new Car(Optional.of(insurance));
        person = new Person(Optional.of(car));
    }

    @Test
    public void test1() {
        //允许传递为 null 的参数
        Optional<Insurance> insurance = Optional.ofNullable(this.insurance);
        Optional<String> s = insurance.map(insurance1 -> insurance1.getName());
        System.out.println(s);
    }

    @Test
    public void test2() {
        // 简单来说，如果想得到一个非 null 值的 Optional 使用 Optional.of，允许 null 值的话使用 Optional.ofNullable;
        Optional<Person> person = Optional.of(this.person);
        // 对于返回一个 Optional 结果集需要使用 flatMap，比如 Person::getCar 方法和 Car::getInsurance，
        // 只要单一转换的使用 map，例如 Insurance::getName，如果是 empty 返回 orElse 的内容。
        String name = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance) //拿到封装的 Optional<Car>
                .map(Insurance::getName) //直接拿到值
                .orElse("unknown");
        System.out.println(name);
    }

    @Test
    public void test3() {
        Optional<Car> c = Optional.empty();
        Optional<String> s = c.flatMap(Car::getInsurance)
                .map(Insurance::getName);
        System.out.println(s);
        String unknow = s.orElse("unknown");
        System.out.println(unknow);
    }


}


class Person {
    private Optional<Car> car;

    public Person(Optional<Car> car) {
        this.car = car;
    }
    public Optional<Car> getCar() {
        return car;
    }
}


class Car {
    private Optional<Insurance> insurance;

    public Car(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;

    public Insurance(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}