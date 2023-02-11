package cn.fd.platform;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Demo1  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        System.err.println("start : " + Thread.currentThread().getName());

        // 同样在JavaFx线程中
        // 一些简单的界面更新可以这样使用
        // 复杂耗时的任务会让界面卡住
        // javaFx真正的多任务，有别的实现
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.err.println("run : " + Thread.currentThread().getName());

                for (int i = 0; i < 10; i++) {
                    System.err.println(i);
                }
            }
        });

        System.err.println("runLater 下面");

    }
}
