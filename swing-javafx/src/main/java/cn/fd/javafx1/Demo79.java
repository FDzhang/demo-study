
package cn.fd.javafx1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

/**
 * JavaFX视频教程第79课，Clipboard 系统剪贴板的基本使用
 * <p>
 */
public class Demo79 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Clipboard clipboard = Clipboard.getSystemClipboard();

        ClipboardContent content = new ClipboardContent();

        content.put(DataFormat.PLAIN_TEXT, "zzzz");
        content.put(DataFormat.IMAGE, "./xx.png");
//        clipboard.setContent(content);

        Label label = new Label("等待粘贴内容");

        ImageView iv = new ImageView();
        iv.setFitHeight(400);
        iv.setFitWidth(400);

        AnchorPane an = new AnchorPane(label, iv);
        an.setStyle("-fx-background-color: #ffffff");

        AnchorPane.setLeftAnchor(label, 500.0);
        AnchorPane.setTopAnchor(label, 500.0);

        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        KeyCodeCombination kc = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kc, new Runnable() {
            @Override
            public void run() {
                if (clipboard.hasString()) {
                    label.setText(clipboard.getString());
                }
                if (clipboard.hasImage()) {
                    System.out.println("has image");
                    iv.setImage(clipboard.getImage());
                }
                if (clipboard.hasFiles()) {
                    System.out.println("has files");
                    File file = clipboard.getFiles().get(0);

                    Image image = null;
                    try (FileInputStream fileInputStream = new FileInputStream(file)) {
                        image = new Image(fileInputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    iv.setImage(image);
                }
            }
        });
    }
}
