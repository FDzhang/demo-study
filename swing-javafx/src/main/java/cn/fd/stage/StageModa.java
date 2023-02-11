package cn.fd.stage;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageModa extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage s2 = new Stage();
        s2.setTitle("s2");
        s2.show();

        Stage s1 = new Stage();
        s1.setTitle("s1");
        s1.initModality(Modality.APPLICATION_MODAL); // 锁住之前的窗口
        s1.show();

        Stage s3 = new Stage();
        s3.setTitle("s3");
        s3.initOwner(s2);
        s3.initModality(Modality.WINDOW_MODAL); // 锁住owner
        s3.show();

        primaryStage.setTitle("main");
        primaryStage.show();
    }
}
