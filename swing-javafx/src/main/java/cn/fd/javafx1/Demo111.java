package cn.fd.javafx1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Demo111 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        ProgressBar pb = new ProgressBar(0);
        pb.setPrefWidth(200);

        Button cancel = new Button("取消");
        Button start = new Button("开始");

        Button reset = new Button("重置");
        Button restart = new Button("重启");

        Label l1 = new Label("state");
        Label l2 = new Label("value");
        Label l3 = new Label("title");
        Label l4 = new Label("message");

        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        hBox.getChildren().addAll(start, cancel, restart, reset, pb, l1, l2, l3, l4);

        an.getChildren().add(hBox);
        AnchorPane.setLeftAnchor(hBox, 100.0);
        AnchorPane.setTopAnchor(hBox, 100.0);

        Scene scene = new Scene(an, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        MyScheduledService mss = new MyScheduledService();
        mss.setDelay(Duration.seconds(2));
        mss.setPeriod(Duration.seconds(1));

        mss.setRestartOnFailure(true);// 默认失败会重启
        mss.setMaximumFailureCount(4);

        start.setOnAction(event -> mss.start());

        cancel.setOnAction(event -> mss.cancel());

        restart.setOnAction(event -> mss.restart());

        reset.setOnAction(event -> {
            mss.reset();
            pb.setProgress(0.0);
        });

        mss.progressProperty().addListener((observable, oldValue, newValue) -> pb.setProgress(newValue.doubleValue()));
        mss.messageProperty().addListener((observable, oldValue, newValue) -> l4.setText(newValue));

        mss.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                l2.setText(newValue.toString());
            }
            System.out.println("oldValue = " + oldValue + ", newValue = " + newValue);
        });

        mss.lastValueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null) {
                    l2.setText(newValue.toString());
                    System.out.println("最后的 value = " + newValue);
                }
            }
        });
    }

    static class MyScheduledService extends ScheduledService<Number> {
        int sum = 0;

        @Override
        protected Task<Number> createTask() {
            System.out.println("createTask 被调用");
            Task<Number> task = new Task<>() {

                @Override
                protected void updateValue(Number value) {
                    super.updateValue(value);

                    System.out.println("task 的线程名称 = " + Thread.currentThread().getName());
                    System.out.println("task 的 updateValue = " + value);
//                    if (value.intValue() == 100) {
//                        MyScheduledService.this.cancel();
//                        System.out.println("MyScheduledService 任务取消");
//                    }
                }

                @Override
                protected Number call() throws Exception {
                    for (int i = 0; i < 10; i++) {
                        sum += 1;
                    }
                    System.out.println("sum = " + sum);
                    return sum;
                }
            };

            return task;
        }
    }

}

