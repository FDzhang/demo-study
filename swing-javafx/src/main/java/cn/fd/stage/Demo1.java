package cn.fd.stage;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Demo1 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFx");
        primaryStage.getIcons().add(new Image("/icon.png"));

        // primaryStage.setResizable(false); // 大小不可变

        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.err.println("height : " + newValue.doubleValue());
            }
        });

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.err.println("width : " + newValue.doubleValue());
            }
        });


        // primaryStage.setFullScreen(true); // 设置全屏 必须设置scene
        // primaryStage.setScene(new Scene(new Group()));

        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        primaryStage.setOpacity(0.5); // 设置透明度
        primaryStage.setAlwaysOnTop(true); //  设置窗口置顶

        // 从屏幕的左上角开始
        primaryStage.setX(100); // 设置窗口x的坐标
        primaryStage.setY(100); // 设置窗口y的坐标

        primaryStage.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.err.println("x : " + newValue.doubleValue());
            }
        });
        primaryStage.yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.err.println("y : " + newValue.doubleValue());
            }
        });
        primaryStage.show();
    }
}
