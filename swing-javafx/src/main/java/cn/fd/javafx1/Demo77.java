
package cn.fd.javafx1;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * JavaFX视频教程第77课，事件传递，addEventFilter 和 addEventHandler 的区别，以及consume()方法
 * <p>
 * Filter 事件从父组件往子组件传
 * handle 事件从子组件往父组件传
 *
 * event.consume(); 消费事件，使得事件不会向后传递
 *
 * 先Filter，在Handler
 * button 不会主动将事件向上传递
 * label等组件  会主动向上传递
 *
 * Filter > Handler > setOnXXX
 */
public class Demo77 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button b1 = new Button("Button1");
        Label label = new Label("hello world");

        HBox root = new HBox(b1, label);
        root.setStyle("-fx-background-color: #778877");
        root.setPrefSize(200,300);

        AnchorPane an = new AnchorPane(root);
        an.setStyle("-fx-background-color: Red");

        AnchorPane.setLeftAnchor(root, 100.0);
        AnchorPane.setTopAnchor(root, 100.0);


        // MouseEvent.ANY 鼠标的所有事件

        b1.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("b1 source" + event.getSource() +" target " + event.getTarget());
            }
        });
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("root source" + event.getSource() +" target " + event.getTarget());
            }
        });
        an.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("an source" + event.getSource() +" target " + event.getTarget());
//                event.consume();
            }
        });

        label.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("addEventFilter label1 ------");
            }
        });
        label.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("addEventFilter label ------");
            }
        });



        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("EventHandler b1 source" + event.getSource() +" target " + event.getTarget());
                Event.fireEvent(root, event);
            }
        });

        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("lable setonclick");
            }
        });
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("addEventHandler label2 ------");
            }
        });
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("addEventHandler label ------");
            }
        });

        root.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("EventHandler root source" + event.getSource() +" target " + event.getTarget());
            }
        });
        an.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("EventHandler an source" + event.getSource() +" target " + event.getTarget());
            }
        });


        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
