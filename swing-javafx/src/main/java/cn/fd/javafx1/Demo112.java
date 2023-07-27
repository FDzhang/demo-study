package cn.fd.javafx1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

public class Demo112 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fx = new FXMLLoader();
        // AnchorPane root = fx.load(new FileInputStream(new File("D:\\projects\\IdeaProjects\\demo-study\\swing-javafx\\src\\main\\resources\\myfxml.fxml")));

        URL url = Demo112.class.getClassLoader().getResource("myfxml.fxml");
        fx.setLocation(url);
        AnchorPane root = fx.load();

        Button b1 = (Button) root.lookup("#b1");

        System.out.println(b1.getText());

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("aaaaaaa");
            }
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
