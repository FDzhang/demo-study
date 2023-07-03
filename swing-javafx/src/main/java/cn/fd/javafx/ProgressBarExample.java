package cn.fd.javafx;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 在JavaFX中，ProgressBar（进度条）和ProgressIndicator（进度指示器）是常用的UI组件，
 * 用于显示任务的进度。ProgressBar显示任务的具体进度，而ProgressIndicator则用于显示任务的整体进度。
 */
public class ProgressBarExample extends Application {
    ScheduledService<Double> schedule;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ProgressBar progressBar = new ProgressBar();

        progressBar.setProgress(0.1); // 设置进度为10%
        // 未知进度
//        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);

        progressBar.setPrefWidth(100);
        progressBar.setPrefHeight(20);

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


        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(0.25); // 设置进度为25%


        VBox root = new VBox(progressBar, progressIndicator);
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}