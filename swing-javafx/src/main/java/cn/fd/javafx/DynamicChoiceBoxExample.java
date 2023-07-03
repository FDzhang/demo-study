package cn.fd.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class DynamicChoiceBoxExample extends Application {
    private ChoiceBox<String> choiceBox;
    private ObservableList<String> options;
    @Override
    public void start(Stage primaryStage) {
        choiceBox = new ChoiceBox<>();
        options = FXCollections.observableArrayList("选项1", "选项2", "选项3");
        choiceBox.setItems(options);
        Button addButton = new Button("添加选项");
        addButton.setOnAction(e -> addOption("新选项"));
        Button removeButton = new Button("移除选项");
        removeButton.setOnAction(e -> removeOption("选项2"));
        VBox vbox = new VBox(choiceBox, addButton, removeButton);
        Scene scene = new Scene(vbox, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("动态修改ChoiceBox的选项列表");
        primaryStage.show();
    }
    private void addOption(String option) {
        options.add(option);
    }
    private void removeOption(String option) {
        options.remove(option);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
