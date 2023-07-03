package cn.fd.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Comparator;
public class ChoiceBoxExample extends Application {
    private ChoiceBox<String> choiceBox;
    @Override
    public void start(Stage primaryStage) {
        choiceBox = new ChoiceBox<>();
        // 创建对象列表
        ObservableList<Person> personList = FXCollections.observableArrayList();
        personList.add(new Person("John", 25));
        personList.add(new Person("Alice", 30));
        personList.add(new Person("Bob", 20));
        // 对数据源进行排序
        personList.sort(Comparator.comparing(Person::getName));
        // 将对象的某个属性作为选项显示在ChoiceBox中
        for (Person person : personList) {
            choiceBox.getItems().add(person.getName());
        }
        VBox vbox = new VBox(choiceBox);
        Scene scene = new Scene(vbox, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChoiceBox示例");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    private static class Person {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }
}