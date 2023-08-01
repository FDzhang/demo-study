package cn.fd.javafx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Demo117 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fx = new FXMLLoader(Demo117.class.getClassLoader().getResource("Demo117.fxml"));

        AnchorPane root = fx.load();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
