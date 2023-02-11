package cn.fd.stage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageType extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage s1 = new Stage();
        s1.setTitle("s1");
        s1.initStyle(StageStyle.DECORATED); // 默认样式
        s1.show();
//
//        Stage s2 = new Stage();
//        s2.setTitle("s2");
//        s2.initStyle(StageStyle.TRANSPARENT);
//        s2.show();

//        Stage s3 = new Stage();
//        s3.setTitle("s3");
//        s3.initStyle(StageStyle.UNDECORATED);
//        s3.show();

        Stage s4 = new Stage();
        s4.setTitle("s4");
        s4.initStyle(StageStyle.UNIFIED); // 去掉边界
        s4.show();

        Stage s5 = new Stage();
        s5.setTitle("s5");
        s5.initStyle(StageStyle.UTILITY); // 只有关闭按钮
        s5.show();

        primaryStage.setTitle("main");
        primaryStage.show();

    }
}
