package cn.fd.javafx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComboBoxExample extends Application {
    private ComboBox<String> comboBox;
    private ObservableList<String> data;

    @Override
    public void start(Stage primaryStage) {
        comboBox = new ComboBox<>();
        data = FXCollections.observableArrayList();
        comboBox.setItems(data);
        comboBox.setEditable(true);
        comboBox.setPromptText("请选择或输入");
        data.addAll("苹果", "苹果2号", "香蕉", "橙子", "草莓", "葡萄");


        TextField editor = comboBox.getEditor();
        editor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1 == null) {
                    comboBox.setItems(null);
                    comboBox.setPlaceholder(new Label("没有找到"));
                    return;
                }
                System.out.println("s : "+s);
                System.out.println("t1 : "+t1);
                FilteredList<String> filtered = data.filtered(x -> x.contains(t1));
                if (filtered.isEmpty()) {
                    comboBox.setItems(null);
                    comboBox.setPlaceholder(new Label("没有找到"));
                } else {
                    comboBox.setItems(filtered);
                    comboBox.hide();
                    comboBox.show();
                }
            }
        });

        VBox vbox = new VBox(comboBox);
        Scene scene = new Scene(vbox, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ComboBox示例");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}