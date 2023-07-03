package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FindKeyTextAreaExample extends Application {
    int curIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);

        textArea.setText("xxxxxxxx关键字xxxxxx");


        TextField tx = new TextField();

        Button find = new Button("查询");
        find.setOnAction(actionEvent -> {
            String keyword = tx.getText();
            String text = textArea.getText();
            int index = text.indexOf(keyword, curIndex);
            if (index != -1) {
                // 找到关键字，可以根据需要进行处理
                // 例如，可以将关键字标记为红色
                textArea.selectRange(index, index + keyword.length());
                textArea.setStyle("-fx-highlight-fill: red; -fx-highlight-text-fill: black;");
                curIndex = index + keyword.length();
            } else {
                curIndex = 0;
            }

            for (CharSequence paragraph : textArea.getParagraphs()) {
                System.out.println(paragraph);
            }
        });

        HBox hBox = new HBox(tx, find);


        VBox vbox = new VBox(hBox, textArea);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TextArea示例");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}