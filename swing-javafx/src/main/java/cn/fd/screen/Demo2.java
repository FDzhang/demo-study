package cn.fd.screen;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.Buffer;

/**
 * stage
 *  - scene
 *    - node
 *
 * 1、加载资源icon
 * 2、打开网页
 */
public class Demo2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 打开某个网页
        HostServices host = getHostServices();
        host.showDocument("www.baidu.com");


        Button button = new Button("按钮");
        button.setPrefWidth(120);
        button.setPrefHeight(120);
        button.setCursor(Cursor.MOVE);

        Group group =new Group();
        group.getChildren().add(button);

        Scene scene = new Scene(group);
        // 设置鼠标的icon, 默认 or 图片
        // scene.setCursor(Cursor.CLOSED_HAND);
        URL url = getClass().getClassLoader().getResource("icon.png");
        scene.setCursor(Cursor.cursor(url.toExternalForm()));


        primaryStage.setScene(scene);
        primaryStage.setTitle("javafx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}
