package cn.fd.javafx1;

public class Person {
    String name;
    int age;

//    public Person() {
//        System.out.println("Person 无参数构造方法");
//    }

    public Person(String name, int age) {
        System.out.println("Person 有参数构造方法");

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Person setName方法");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Person setAge方法");
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
