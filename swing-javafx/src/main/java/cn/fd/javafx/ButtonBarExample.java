package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ButtonBarExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        ButtonBar buttonBar = new ButtonBar();

        Button okButton = new Button("确定");
        Button cancelButton = new Button("取消");

        okButton.setOnAction(actionEvent -> {
            System.out.println(ButtonBar.getButtonData(okButton));
        });
        cancelButton.setOnAction(actionEvent -> {
            System.out.println(ButtonBar.getButtonData(cancelButton));
        });

        okButton.setPrefSize(220, 20);
        ButtonBar.setButtonUniformSize(okButton, true);

        ButtonBar.setButtonData(okButton, ButtonBar.ButtonData.OK_DONE);
        ButtonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        buttonBar.getButtons().addAll(okButton, cancelButton);


        VBox vbox = new VBox(buttonBar);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ButtonBar示例");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
