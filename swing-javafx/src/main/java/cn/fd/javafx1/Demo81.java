package cn.fd.javafx1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX视频教程第81课，自定义拖拽数据类型
 */
public class Demo81 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("hello world");

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);



        TextField tx = new TextField();




        AnchorPane an = new AnchorPane(label, tx);
        an.setStyle("-fx-background-color: #ffffff");

        AnchorPane.setLeftAnchor(label, 100.0);
        AnchorPane.setLeftAnchor(tx, 300.0);


        label.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db  = label.startDragAndDrop(TransferMode.MOVE);

                Text text = new Text(label.getText());

                WritableImage wi = new WritableImage(((int) label.getWidth()), ((int) label.getHeight()));
                text.snapshot(new SnapshotParameters(), wi);

                db.setDragView(wi, 10, 10);
                ClipboardContent content = new ClipboardContent();
//                content.put(DataFormat.PLAIN_TEXT, label.getText());
                content.putString(label.getText());
                db.setContent(content);
            }
        });

        tx.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.MOVE);

            }
        });
        tx.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                tx.setText(dragboard.getString());
                label.setText("");
            }
        });


        Scene scene = new Scene(an, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
