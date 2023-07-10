package cn.fd.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 */
public class MouseDragExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button b1 = new Button("Button1");
        Button b2 = new Button("Button2");

        b1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("按住后不放开，进行拖拽");
            }
        });

        b1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                b1.startFullDrag();
                System.out.println("检测");
            }
        });

        b1.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
//                System.out.println("组件内拖拽有反应");
            }
        });

        b1.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                System.out.println("进入");
            }
        });

        b1.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                System.out.println("退出");
            }
        });

        b1.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                System.out.println("释放");
            }
        });

        b2.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                System.out.println("b2 进入");
            }
        });

        b2.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                System.out.println("b2 释放");

                System.out.println("事件 target :" + mouseDragEvent.getTarget());
                System.out.println("事件 source :" + mouseDragEvent.getSource());

                System.out.println("手势源 "+mouseDragEvent.getGestureSource());

            }
        });

        b2.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent mouseDragEvent) {
                System.out.println("b2 退出");

//                System.out.println(mouseDragEvent.getTarget());
//                System.out.println(mouseDragEvent.getSource());
            }
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(b1, b2);

        AnchorPane an = new AnchorPane(root);
        AnchorPane.setLeftAnchor(root, 100.0);
        AnchorPane.setTopAnchor(root, 100.0);

        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}