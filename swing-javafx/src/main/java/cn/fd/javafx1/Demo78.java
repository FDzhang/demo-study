
package cn.fd.javafx1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX视频教程第78课，实现鼠标拖动按钮，拖动窗体效果，设置Stage窗体的圆角和边框，自定义窗体样式。
 * <p>
 */
public class Demo78 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double x = 0;
    double y = 0;
    double c = 0;
    double d = 0;
    @Override
    public void start(Stage primaryStage) {
        Button b1 = new Button("Button1");

        AnchorPane an = new AnchorPane(b1);
//        an.setStyle("-fx-background-color: #119988");

        AnchorPane.setLeftAnchor(b1, 100.0);
        AnchorPane.setTopAnchor(b1, 100.0);

        Background bg = new Background(new BackgroundFill(Paint.valueOf("#119988"), new CornerRadii(40), new Insets(5)));
        an.setBackground(bg);

        an.setBorder(new Border(new BorderStroke(Color.valueOf("#912cee"), BorderStrokeStyle.SOLID, new CornerRadii(40), new BorderWidths(2), new Insets(0))));

        b1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getX();
                y = event.getY();
            }
        });

        b1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AnchorPane.setLeftAnchor(b1, event.getSceneX() - x);
                AnchorPane.setTopAnchor(b1, event.getSceneY() - y);
            }
        });
        Scene scene = new Scene(an, 600, 600);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                c = event.getSceneX();
                d = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               primaryStage.setX(event.getScreenX() - c);
               primaryStage.setY(event.getScreenY() - d);
            }
        });
        scene.setFill(Paint.valueOf("#ffffff00"));

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
