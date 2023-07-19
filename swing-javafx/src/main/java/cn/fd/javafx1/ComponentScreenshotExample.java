package cn.fd.javafx1;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ComponentScreenshotExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("示例按钮");
        Button button2 = new Button("示例按钮2");

        // 创建一个场景，并将按钮添加到场景中
        Scene scene = new Scene(button, 200, 100);

        // 创建一个WritableImage对象，用于保存截图
        WritableImage image = new WritableImage(200, 100);

        // 创建一个SnapshotParameters对象，并设置为不显示组件
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(javafx.scene.paint.Color.TRANSPARENT);

        // 获取组件的截图，并保存到WritableImage对象中
        image = button.snapshot(new SnapshotParameters(), image);

        // 将WritableImage对象保存为图像文件
        File file = new File("component_screenshot.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("组件截图已保存为 component_screenshot.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
