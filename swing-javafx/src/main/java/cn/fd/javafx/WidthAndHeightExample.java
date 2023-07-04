package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 1、关于宽高：  setPrefWidth ，getPrefWidth，getWidth，isResizable
 *
 *
 */
public class WidthAndHeightExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click me");
//        button.setPrefWidth(100);
//        button.setPrefHeight(150);

//        button.setPrefWidth(Button.USE_COMPUTED_SIZE);
//        button.setPrefWidth(Button.USE_PREF_SIZE);
//        button.setMaxWidth(Button.USE_PREF_SIZE);

//        button.setWrapText(true);
//        System.out.println(button.getContentBias());

        // 关于宽高设置
        // 1、不设置宽高时，有时父组件会自动给子组件一个宽高 (实体组件)
        // 2、有的组件不会给

//        Rectangle rec = new Rectangle();
//        rec.setWidth(100);
//        rec.setHeight(100);

        VBox root = new VBox(button);

//        System.out.println(root.getChildren().size());

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // isResizable 当组件没有设置宽高时，是否允许父组件自动给当前组件设置宽高
//        System.out.println(button.isResizable());// true
//        System.out.println(rec.isResizable()); // false

//        System.out.println(button.getPrefWidth()); // 没有设置会为-1
//        System.out.println(button.getWidth()); // 实际宽高

    }


}