package cn.fd.javafx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.lang.reflect.Method;

public class Demo120 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Node node = new Button();

        System.out.println(node.getClass().getName());
        Class<?> clazz = Class.forName(node.getClass().getName());


        Node node2 = (Node) clazz.getConstructor(null).newInstance(null);
        Method method = clazz.getMethod("setText", new Class[]{String.class});

        method.invoke(node2, new Object[]{"Button2"});

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(node, node2);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(hBox);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
