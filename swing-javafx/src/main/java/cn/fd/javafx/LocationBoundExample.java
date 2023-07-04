package cn.fd.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 */
public class LocationBoundExample extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("button");
        button.setEffect(new DropShadow());
        button.setRotate(90);

        AnchorPane an = new AnchorPane(button);
        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 100.0);

        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(button.getLayoutBounds());
        System.out.println(button.getBoundsInLocal());
        System.out.println(button.getBoundsInParent());


    }
}