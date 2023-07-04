package cn.fd.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 */
public class LocationContainsExample extends Application {

    double x = 0;
    double y = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("btn1");
        Button button2 = new Button("btn2");


        AnchorPane an = new AnchorPane(button, button2);
        AnchorPane.setTopAnchor(button2, 100.0);
        AnchorPane.setLeftAnchor(button2, 100.0);

        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        Bounds bou2 = button2.getLayoutBounds();
        Point2D p1 = button2.localToParent(bou2.getMinX(), bou2.getMinY());
        Point2D p2 = button2.localToParent(bou2.getMaxX(), bou2.getMaxY());
        System.out.println(p1);
        System.out.println(p2);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Bounds bou1 = button.getLayoutBounds();
            Point2D p3 = null;
            Point2D p4 = null;

            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().getName().equals(KeyCode.A.getName())) {
                    x += 10;
                    button.setLayoutX(x);

                    p3 = button.localToParent(bou1.getMinX(), bou1.getMinY());
                    p4 = button.localToParent(bou1.getMaxX(), bou1.getMaxY());
                }
                if (keyEvent.getCode().getName().equals(KeyCode.S.getName())) {
                    y += 10;
                    button.setLayoutY(y);
                    p3 = button.localToParent(bou1.getMinX(), bou1.getMinY());
                    p4 = button.localToParent(bou1.getMaxX(), bou1.getMaxY());
                }
                if (p4 == null) {
                    return;
                }

                if (p4.getX() >= p1.getX() && p4.getY() >= p1.getY()) {
                    if (p4.getX() <= p2.getX() && p4.getY() <= p2.getY()) {
                        System.out.println("碰撞");
                    }
                }
                System.out.println("没有相撞");
            }
        });


    }
}