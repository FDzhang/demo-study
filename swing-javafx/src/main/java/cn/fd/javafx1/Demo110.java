package cn.fd.javafx1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Demo110 extends Application {
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

        MyService service = new MyService();

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.start();
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.cancel();
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.restart();
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.reset();
                pb.setProgress(0.0);
            }
        });
        service.progressProperty().addListener((observable, oldValue, newValue) -> pb.setProgress(newValue.doubleValue()));
        service.messageProperty().addListener((observable, oldValue, newValue) -> l4.setText(newValue));
    }
}

class MyService extends Service<Number> {

    @Override
    protected void ready() {
        super.ready();
        System.out.println("ready is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("ready tread name = " + Thread.currentThread().getName());
    }

    @Override
    protected void scheduled() {
        super.scheduled();
        System.out.println("scheduled is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("scheduled tread name = " + Thread.currentThread().getName());
    }

    @Override
    protected void running() {
        super.running();
        System.out.println("running is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("running tread name = " + Thread.currentThread().getName());
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        System.out.println("succeeded is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("succeeded tread name = " + Thread.currentThread().getName());
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        System.out.println("cancelled is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("cancelled tread name = " + Thread.currentThread().getName());
    }

    @Override
    protected void failed() {
        super.failed();
        System.out.println("failed is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("failed tread name = " + Thread.currentThread().getName());
    }

    @Override
    protected void executeTask(Task<Number> task) {
        super.executeTask(task);
        System.out.println("executeTask is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("executeTask tread name = " + Thread.currentThread().getName());
        task.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("executeTask的task的valueProperty被触发");
            }
        });
    }

    @Override
    protected Task<Number> createTask() {
        Task<Number> task = new Task<>() {
            @Override
            protected Number call() throws Exception {
                System.out.println("call is fx thread ? : " + Platform.isFxApplicationThread());
                System.out.println("call tread name = " + Thread.currentThread().getName());

                this.updateTitle("copy");

                FileInputStream fis = new FileInputStream(new File("D:\\0zxq\\photo/zhangruonan.jpg"));
                FileOutputStream fos = new FileOutputStream(new File("D:\\0zxq\\photo/zhangruonan2.jpg"));

                double sum = fis.available();
                double cur = 0;
                double progress = 0;

                byte[] bytes = new byte[5000];
                int i;
                while ((i = fis.read(bytes, 0, bytes.length)) != -1) {
                    if (this.isCancelled()) {
                        break;
                    }
                    fos.write(bytes, 0, i);

                    cur += i;

                    progress = cur / sum;


                    this.updateProgress(cur, sum);

                    if (progress < 0.5) {
                        this.updateMessage("请耐心等待");
                    } else if (progress < 0.8) {
                        this.updateMessage("马上就好");
                    } else if (progress < 1) {
                        this.updateMessage("即将完成");
                    } else if (progress >= 1) {
                        this.updateMessage("搞定了");
                    }

                    // System.out.println("当前进度 : " + progress);
                    Thread.sleep(10);
                }
                fos.close();
                fis.close();

                return progress;
            }
        };

        return task;
    }
}
