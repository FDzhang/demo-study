package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextAreaExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);

        textArea.setText("xxxxxxxxxxxxxx");
//        textArea.selectForward();

        textArea.selectPositionCaret(22);


        VBox vbox = new VBox(textArea);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TextArea示例");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}