package cn.fd.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Mouse2Example extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button b1 = new Button("Button1");
        Button b2 = new Button("Button2");
        Circle cir = new Circle(100, Color.RED);

        Text text = new Text("hello world");

        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getTarget());
            }
        });

        //1、 开启矩形边界
        cir.setPickOnBounds(true);

        VBox root = new VBox(20);
        root.getChildren().addAll(b1, b2, cir);

        // 2、 阻止父组件传递事件给子组件
        cir.setMouseTransparent(true);

        root.setStyle("-fx-background-color: Yellow");

        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(" root.setOnMouseClicked");

                System.out.println(mouseEvent.getPickResult());
            }
        });

        cir.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("cir.setOnMouseClicked");
            }
        });

        b1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("b1");

                // 5、事件拷贝
                MouseEvent mouseEvent1 = mouseEvent.copyFor(b2, b2);
                System.out.println("copyFor " + mouseEvent1.getEventType());

                MouseEvent.fireEvent(mouseEvent1.getTarget(), mouseEvent1);


                // 6、是否为触摸设备
                System.out.println("mouseEvent.isSynthesized() : " + mouseEvent.isSynthesized());

                System.out.println(mouseEvent.getEventType());

                KeyEvent keyEvent = new KeyEvent(b2, b2, KeyEvent.KEY_PRESSED, "a", "a", KeyCode.A, false, false, false, false);

                // 7.给特定组件，传递事件
                MouseEvent.fireEvent(b2, mouseEvent);
                MouseEvent.fireEvent(text, mouseEvent);
                MouseEvent.fireEvent(b2, keyEvent);

            }
        });

        b2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode().getName());
            }
        });

        b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("b2.setOnMouseClicked");
            }
        });

        b2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(" b2.setOnMousePressed ");
            }
        });

        b1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("鼠标按下");

                // 3、设置拖拽检测
//                mouseEvent.setDragDetect(false);
                // 4、返回多次鼠标点击的位置是否一样
                System.out.println(mouseEvent.isStillSincePress());
            }
        });

        b1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("setOnDragDetected");
            }
        });

        AnchorPane an = new AnchorPane(root);
        AnchorPane.setLeftAnchor(root, 100.0);
        AnchorPane.setTopAnchor(root, 100.0);

        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
