package cn.fd.javafx;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 */
public class LocationExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click me1");
        Button button2 = new Button("Click me2");

        VBox vbox = new VBox(button, button2);
        vbox.setStyle("-fx-background-color: Yellow");
        vbox.setPrefWidth(200);
        vbox.setPrefHeight(200);
        vbox.setAlignment(Pos.CENTER);

        AnchorPane an = new AnchorPane(vbox);
        AnchorPane.setTopAnchor(vbox, 100.0);
        AnchorPane.setLeftAnchor(vbox, 100.0);

        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        System.out.println(button.getLayoutX());
        System.out.println(button.getLayoutY());

        Bounds bounds = button.getLayoutBounds();
        Point2D p1 = button.localToParent(bounds.getMinX(), bounds.getMinY());
        System.out.println(p1);

        Point2D p2 = button.localToScene(bounds.getMaxX(), bounds.getMaxY());
        System.out.println(p2);

        Point2D p3 = button.localToScreen(bounds.getMinX(), bounds.getMinY());
        System.out.println(p3);

        Point2D p4 = button.parentToLocal(p2.getX(), p2.getY());
        System.out.println(p4);

        Point2D p5 = button.sceneToLocal(p2.getX(), p2.getY());
        System.out.println(p5);

        System.out.println(bounds);

        System.out.println(bounds.contains(10, 10));


    }
}