package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
public class ColoredTextAreaExample extends Application {
    private TextArea textArea;
    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        VBox vbox = new VBox(textArea);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Colored TextArea示例");
        primaryStage.show();
        highlightText();
    }
    private void highlightText() {
        String searchText = textArea.getText();
        String keyword = "关键词"; // 要高亮的关键词
        TextFlow textFlow = new TextFlow();
        int startIndex = searchText.indexOf(keyword);
        while (startIndex >= 0) {
            int endIndex = startIndex + keyword.length();
            Text textBeforeKeyword = new Text(searchText.substring(0, startIndex));
            Text textKeyword = new Text(searchText.substring(startIndex, endIndex));
            Text textAfterKeyword = new Text(searchText.substring(endIndex));
            textKeyword.setFill(Color.RED);
            textFlow.getChildren().addAll(textBeforeKeyword, textKeyword);
            searchText = searchText.substring(endIndex);
            startIndex = searchText.indexOf(keyword);
        }
        Text textRemaining = new Text(searchText);
        textFlow.getChildren().add(textRemaining);
        textArea.clear();
        textArea.setWrapText(true);
    }
    public static void main(String[] args) {
        launch(args);
    }
}