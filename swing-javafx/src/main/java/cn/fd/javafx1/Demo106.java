package cn.fd.javafx1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Demo106 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        TreeView<String> treeView = new TreeView<>();
        TreeItem<String> root = new TreeItem<>("root");
        TreeItem<String> p1 = new TreeItem<>("p1");
        TreeItem<String> p2 = new TreeItem<>("p2");
        TreeItem<String> c1 = new TreeItem<>("c1");
        TreeItem<String> c2 = new TreeItem<>("c2");
        TreeItem<String> c3 = new TreeItem<>("c3");
        TreeItem<String> c4 = new TreeItem<>("c4");
        root.getChildren().addAll(p1, p2);
        p1.getChildren().addAll(c1, c2);
        p2.getChildren().addAll(c3, c4);

        treeView.setRoot(root);
        root.setExpanded(true);
        p1.setExpanded(true);
        p2.setExpanded(true);

        setTree(treeView);

        VBox vBox = new VBox(treeView);

        Scene scene = new Scene(vBox, 300, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ACell temp;

    private void setTree(TreeView<String> treeView) {
        treeView.setEditable(true);
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                ACell aCell = new ACell();

                aCell.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
//                        aCell.getTreeView().getFocusModel().focus(aCell.getIndex());
                    }
                });

                aCell.setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Dragboard db = aCell.startDragAndDrop(TransferMode.COPY_OR_MOVE);

                        ClipboardContent content = new ClipboardContent();
                        content.putString(aCell.getItem());

                        Text text = new Text(aCell.getItem());
                        text.setFont(new Font(15));
                        WritableImage wi = new WritableImage(40, 20);
                        text.snapshot(new SnapshotParameters(), wi);

                        db.setDragView(wi);
                        db.setContent(content);

                    }
                });

                aCell.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        event.acceptTransferModes(TransferMode.COPY);

                        if (temp != null) {
                            temp.setBorder(null);
                        }
                        temp = aCell;
                        System.out.println("x="+event.getX()+",y="+event.getY());
                        System.out.println("aCell.height="+aCell.getHeight());
                        if (event.getY() >= 0 && event.getY() <= aCell.getHeight() - 10) {

                        } else if (event.getY() > aCell.getHeight() - 10 && event.getY() <= aCell.getHeight()) {
                            BorderStroke bs = new BorderStroke(null, null, Paint.valueOf("#71C671"), null, BorderStrokeStyle.SOLID, null, null, null, null, new BorderWidths(0, 0, 2, 0), null);
                            Border border = new Border(bs);
                            aCell.setBorder(border);
                        }
                    }
                });

                aCell.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        String value = event.getDragboard().getString();

                        aCell.getTreeItem().isLeaf();

                        if (aCell.getTreeItem().getParent() != null) {
                            int index = aCell.getTreeItem().getParent().getChildren().indexOf(aCell.getTreeItem());
                            aCell.getTreeItem().getParent().getChildren().add(index + 1, new TreeItem<>(value));
                        }

                        aCell.setBorder(null);
                    }
                });

                return aCell;
            }
        });
    }

    // TreeCell可以理解为一个label
    static class ACell extends TreeCell<String> {
        public void startEdit() {
            super.startEdit();

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setStyle("-fx-background-color: #FFA500");

            TextField tf = new TextField(this.getItem());
            hBox.getChildren().addAll(tf);

//                this.setText("-text区域");
            this.setGraphic(hBox);
            tf.requestFocus();

            tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        String val = tf.getText();

                        commitEdit(val);
                    }
                }
            });
        }

        @Override
        public void commitEdit(String newValue) {
            super.commitEdit(newValue);
            System.out.println("commit = " + newValue);
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setStyle("-fx-background-color: #FFA500");

            Label tf = new Label(this.getItem());
            hBox.getChildren().addAll(tf);

//                this.setText("-text区域");
            this.setGraphic(hBox);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_LEFT);
//                hBox.setStyle("-fx-background-color: #FFA500");

                Label label = new Label(item);
                hBox.getChildren().addAll(label);

                // 折叠展开的图标
                if (this.getTreeItem().isExpanded()) {
                    ImageView imageView = new ImageView("jian.png");
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(15);

                    this.setDisclosureNode(imageView);
                } else {
                    ImageView imageView = new ImageView("jia.png");
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(15);
                    this.setDisclosureNode(imageView);
                }

//                this.setText("-text区域");
                this.setGraphic(hBox);
            } else {
                this.setGraphic(null);
//                this.setText(null);
            }

        }
    }
}
