package cn.fd.group;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 extends Application {
    int i = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("b1");
        Button b2 = new Button("b2");

        b1.setLayoutX(40);
        b1.setLayoutY(40);

        Group group = new Group();
        group.getChildren().addAll(b1, b2);

        // 某个点上是否有node， 只能检测node的左上角
//        System.err.println(group.contains(41,41));

//        group.setOpacity(0.5); // 透明度
//        group.setAutoSizeChildren(false); // 设置是否自动匹配尺寸
//        group.getChildren().clear(); // 清空

        // 组件发生改变
        group.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                System.err.println("当前组件数量 = " + c.getList().size());
            }
        });


        Object[] objects = group.getChildren().toArray();
        System.err.println(objects.length);

        for (Object obj : objects) {
            Button b = (Button) obj;
            b.setPrefWidth(50);
            b.setPrefHeight(50);
        }



        // 按钮事件
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                i = i + 10;

                Button bb = new Button("b3");
                bb.setLayoutX(60);
                bb.setLayoutY(i);
                group.getChildren().add(bb);

            }
        });

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(a|e|s)");
        String ss = "a|e|s|v";
        Matcher matcher = pattern.matcher(ss);

        System.err.println(matcher.group(1));
    }
}
