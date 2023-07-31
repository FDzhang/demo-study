package cn.fd.javafx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;


public class Demo115 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fx = new FXMLLoader(Demo115.class.getClassLoader().getResource("Demo115.fxml"));

        fx.setBuilderFactory(new PersonBuilderFactory());

//        ArrayList<Person> root = fx.load();
        HashMap root = fx.load();

        System.out.println(root.size());

//        Scene scene = new Scene(root, 800, 600);
//        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
