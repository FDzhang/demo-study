package cn.fd.javafx1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Demo113 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fx = new FXMLLoader();
        // AnchorPane root = fx.load(new FileInputStream(new File("D:\\projects\\IdeaProjects\\demo-study\\swing-javafx\\src\\main\\resources\\myfxml.fxml")));

        URL url = Demo113.class.getClassLoader().getResource("myfxml.fxml");
        fx.setLocation(url);
        AnchorPane root = fx.load();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        MyController113 mc = fx.getController();

        // Button b1 = (Button) root.lookup("#b1");

        Button b1 = mc.getB1();


        System.out.println(b1.getText());

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fx1 = new FXMLLoader(Demo113.class.getClassLoader().getResource("other.fxml"));
                    AnchorPane root1 = fx1.load();
                    // scene.setRoot(root1);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1, 400,400));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        primaryStage.show();
    }
}
