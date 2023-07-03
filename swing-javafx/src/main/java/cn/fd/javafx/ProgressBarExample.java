package cn.fd.javafx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 在JavaFX中，ProgressBar（进度条）和ProgressIndicator（进度指示器）是常用的UI组件，
 * 用于显示任务的进度。ProgressBar显示任务的具体进度，而ProgressIndicator则用于显示任务的整体进度。
 * ProgressBar : 直线形
 * ProgressIndicator ： 圆盘形
 */
public class ProgressBarExample extends Application {
    ScheduledService<Double> schedule;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ProgressBar progressBar = new ProgressBar();

//        progressBar.setProgress(0.1); // 设置进度为10%
        // 未知进度
//        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);

        progressBar.setPrefWidth(300);
        progressBar.setPrefHeight(20);



        ProgressIndicator progressIndicator = new ProgressIndicator();
        // JDK>9之后， pref 无效
//        progressIndicator.setPrefWidth(300f);
//        progressIndicator.setPrefHeight(300f);

        progressIndicator.setMinSize(100, 100);
//        progressIndicator.setProgress(0.25); // 设置进度为25%
//        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);

        startProgress(progressBar, progressIndicator);


        progressBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println(t1);
            }
        });

//        AnchorPane an = new AnchorPane();
//        AnchorPane.setTopAnchor(progressBar, 200.0);
//        an.getChildren().addAll(progressBar, progressIndicator);
        VBox root = new VBox(progressBar, progressIndicator);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startProgress(ProgressBar progressBar, ProgressIndicator progressIndicator) {
        schedule = new ScheduledService<>() {
            private double cur = 0;

            @Override
            protected Task<Double> createTask() {
                Task<Double> task = new Task<>() {
                    @Override
                    protected Double call() {
                        System.out.println("call");
                        return cur = cur + 0.1;
                    }

                    @Override
                    protected void updateValue(Double aDouble) {
                        progressBar.setProgress(aDouble);
                        progressIndicator.setProgress(aDouble);
                        if (aDouble >= 1) {
                            schedule.cancel();
                            System.out.println("任务取消");
                        }
                    }
                };
                return task;
            }
        };
        schedule.setDelay(Duration.millis(0));
        schedule.setPeriod(Duration.millis(1000));
        schedule.start();
    }
}