package cn.fd.javafx;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonCheckBoxExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        RadioButton radioButton1 = new RadioButton("选项1");
        RadioButton radioButton2 = new RadioButton("选项2");
        RadioButton radioButton3 = new RadioButton("选项3");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);

        CheckBox checkBox1 = new CheckBox("复选框1");
        CheckBox checkBox2 = new CheckBox("复选框2");
        CheckBox checkBox3 = new CheckBox("复选框3");

        BooleanProperty radioButtonSelected = new SimpleBooleanProperty();
        BooleanProperty checkBoxSelected = new SimpleBooleanProperty();

        radioButton1.selectedProperty().bindBidirectional(radioButtonSelected);
        checkBox1.selectedProperty().bindBidirectional(checkBoxSelected);

        radioButtonSelected.addListener((observable, oldValue, newValue) -> {
            System.out.println("选项的选中状态为：" + newValue);
        });
        checkBoxSelected.addListener((observable, oldValue, newValue) -> {
            System.out.println("复选框的选中状态为：" + newValue);
        });
        System.out.println(radioButtonSelected.getName());

        VBox vbox = new VBox(radioButton1, radioButton2, radioButton3, checkBox1, checkBox2, checkBox3);
        Scene scene = new Scene(vbox, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RadioButton和CheckBox示例");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}