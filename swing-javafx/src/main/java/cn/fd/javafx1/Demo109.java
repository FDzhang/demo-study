package cn.fd.javafx1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Demo109 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        ProgressBar pb = new ProgressBar(0);
        pb.setPrefWidth(200);

        Button cancel = new Button("取消");
        Button start = new Button("开始");

        Label l1 = new Label("state");
        Label l2 = new Label("value");
        Label l3 = new Label("title");
        Label l4 = new Label("message");

        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ffffff");

        hBox.getChildren().addAll(start, cancel, pb, l1, l2, l3, l4);

        an.getChildren().add(hBox);
        AnchorPane.setLeftAnchor(hBox, 100.0);
        AnchorPane.setTopAnchor(hBox, 100.0);

        Scene scene = new Scene(an, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        MyTask task = new MyTask();
        Thread thread = new Thread(task);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thread.start();
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                task.cancel();
            }
        });

        task.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pb.setProgress(newValue.doubleValue());
            }
        });

        task.titleProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                l3.setText(newValue);
            }
        });

        task.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                l2.setText(newValue.toString());
            }
        });

        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                l4.setText(newValue);
            }
        });

        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                System.out.println("任务状态 : " + newValue.toString());
                l1.setText(newValue.toString());
            }
        });

        task.exceptionProperty().addListener(new ChangeListener<Throwable>() {
            @Override
            public void changed(ObservableValue<? extends Throwable> observable, Throwable oldValue, Throwable newValue) {
                System.out.println("异常消息： " + newValue.getMessage());
            }
        });
    }

}

class MyTask extends Task<Number> {

    @Override
    protected Number call() throws Exception {
        System.out.println("call is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("call tread name = " + Thread.currentThread().getName());

        this.updateTitle("copy");

        FileInputStream fis = new FileInputStream(new File("D:\\0zxq\\photo/zhangruonan5.jpg"));
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
            Thread.sleep(50);
        }
        fos.close();
        fis.close();

        return progress;
    }

    @Override
    protected void updateMessage(String message) {
        super.updateMessage(message);
    }

    @Override
    protected void updateTitle(String title) {
        super.updateTitle(title);
    }

    @Override
    protected void updateValue(Number value) {
        super.updateValue(value);
        System.out.println("updateValue tread name = " + Thread.currentThread().getName());
        System.out.println("updateValue is fx thread ? : " + Platform.isFxApplicationThread());
        System.out.println("update value : " + value);
    }
}
