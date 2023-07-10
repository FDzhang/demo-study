package cn.fd.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 */
public class MouseExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button b1 = new Button("Button1");
        Button b2 = new Button("Button2");

        b1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println(mouseEvent.getX());
//                System.out.println(mouseEvent.getY());
//
//                System.out.println(mouseEvent.getSceneX());
//                System.out.println(mouseEvent.getSceneY());
//
//                System.out.println(mouseEvent.getScreenX());
//                System.out.println(mouseEvent.getScreenY());

                // 事件的源头
//                System.out.println(mouseEvent.getSource());
//                System.out.println(mouseEvent.getTarget());
//                System.out.println(mouseEvent.getEventType());
                // 哪个鼠标按钮
//                System.out.println(mouseEvent.getButton());
                // ctrl按键是否按下
//                System.out.println(mouseEvent.isControlDown());
//                System.out.println(mouseEvent.getClickCount());
                // 其它按键触发时，判断鼠标右键是否处于按下状态
//                System.out.println("click "+mouseEvent.isSecondaryButtonDown());
//                if (mouseEvent.getClickCount()==2 && mouseEvent.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("双击");
//                }
            }
        });

        b1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("鼠标释放");
                // 其它按键触发时，判断鼠标右键是否处于按下状态
//                System.out.println(mouseEvent.isSecondaryButtonDown());
            }
        });
        b1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("鼠标按下");
            }
        });

        b1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("鼠标进入");
            }
        });

        b1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("鼠标退出");
            }
        });

        b1.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("鼠标移动");
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