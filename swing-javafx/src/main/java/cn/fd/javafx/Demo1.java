package cn.fd.javafx;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Demo1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        TextFlow textFlow = new TextFlow();
        Text redText = new Text("Hello, ");
        redText.setFill(Color.RED);
        Text greenText = new Text("JavaFx");
        greenText.setFill(Color.GREEN);
        textFlow.getChildren().addAll(redText, greenText);

        textArea.setText("xxxx");
        Pane root = new Pane();
        root.getChildren().add(textArea);
        ContextMenu contextMenu = new ContextMenu();
        textArea.setContextMenu(contextMenu);
        contextMenu.getItems().add(new MenuItem("xxx"));

        textArea.setOnContextMenuRequested(event -> {
            System.out.println("setOnContextMenuRequested");
        });

        // 超链接
//        Hyperlink link = new Hyperlink("www.baidu.com", new Button("网址"));
//        root.getChildren().add(link);
//
//        link.setOnAction(event->{
//            HostServices hostServices = Demo1.this.getHostServices();
//            hostServices.showDocument(link.getText());
//        });

        // customMenuItem
        // MenuButton[
        // SplitMenuButton
        // ContextMenu

        // Accordion
        // TitledPane





        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFx Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

