package cn.fd.javafx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;


public class Demo116 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fx = new FXMLLoader(Demo116.class.getClassLoader().getResource("Demo116.fxml"));

        fx.setBuilderFactory(new PersonBuilderFactory());

        AnchorPane root = fx.load();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
