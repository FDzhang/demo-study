package cn.fd.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccordionExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("面板1", new VBox());
        TitledPane pane2 = new TitledPane("面板2", new VBox());
        TitledPane pane3 = new TitledPane("面板3", new VBox());
        accordion.getPanes().addAll(pane1, pane2, pane3);


        //  1. expandedPaneProperty(): 该属性监听当前展开的面板。
        //  您可以使用该属性来获取当前展开的面板，或者为其添加监听器以在展开面板发生变化时执行相应的操作。
        accordion.expandedPaneProperty().addListener((observable, oldPane, newPane) -> {
            if (newPane != null) {
                System.out.println("展开的面板：" + newPane.getText());
            }
        });

        // 2. onMouseClicked(): 该事件监听用户单击面板标题时的操作。
        // 您可以为每个面板的标题添加单击事件监听器，以便在用户单击时执行特定的操作。
        pane1.setOnMouseClicked(event -> {
            System.out.println("面板1被单击");
        });

        Scene scene = new Scene(accordion, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Accordion示例");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}