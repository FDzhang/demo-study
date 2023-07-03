package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
public class RichTextAreaExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefSize(400, 300);
        // 添加自定义CSS样式
        htmlEditor.getStylesheets().add(
                getClass().getResource("/remove-buttons.css").toExternalForm());


        VBox vbox = new VBox(htmlEditor);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RichTextArea示例");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}