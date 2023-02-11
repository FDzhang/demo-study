package cn.fd.platform;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Demo2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置当所有窗口关闭后程序是否退出
        Platform.setImplicitExit(false);

        // primaryStage.show();

        // 是否支持3D
        System.err.println(Platform.isSupported(ConditionalFeature.SCENE3D));
        // 是否支持FXML
        System.err.println(Platform.isSupported(ConditionalFeature.FXML));

        // 关闭程序
        // Platform.exit();
    }
}
