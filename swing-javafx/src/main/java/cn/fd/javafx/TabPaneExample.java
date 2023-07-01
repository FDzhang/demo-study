package cn.fd.javafx;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TabPaneExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();


        tabPane.setSide(Side.TOP);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);


        Tab tab1 = new Tab("选项卡1");

        //  1. onSelectionChanged: 当Tab的选中状态发生改变时触发。
        //  可以使用tab.selectedProperty().addListener()方法来添加监听器。
        tab1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // 当前Tab被选中
                System.out.println("Tab被选中");
            } else {
                // 当前Tab取消选中
                System.out.println("Tab取消选中");
            }
        });
        // onClosed: 当Tab被关闭时触发。可以使用tab.setOnClosed()方法来设置关闭事件的处理逻辑。
        tab1.setOnClosed(event -> {
            // 关闭Tab的处理逻辑
            System.out.println("Tab被关闭");
        });
        // tab.setOnClosed 用于处理Tab已经被关闭的情况，
        // 而 tab.setOnCloseRequest 用于处理用户尝试关闭Tab的请求，并可以选择是否阻止Tab关闭。
        tab1.setOnCloseRequest(event -> {
            // Tab关闭请求时的逻辑
            // 阻止Tab关闭的逻辑
            event.consume(); // 阻止Tab关闭
        });

        ContextMenu contextMenu = new ContextMenu();
        MenuItem closeMenuItem = new MenuItem("关闭选项卡");
        closeMenuItem.setOnAction(event -> {
            tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
        });
        contextMenu.getItems().add(closeMenuItem);
        // 3. onContextMenuRequested: 当在Tab上右键点击时触发上下文菜单事件。
        // 可以使用tab.setOnContextMenuRequested()方法来设置上下文菜单事件的处理逻辑。
        tabPane.setOnContextMenuRequested(event -> {
            tabPane.getContextMenu().show(tabPane, event.getScreenX(), event.getScreenY());
        });
        tabPane.setContextMenu(contextMenu);


        tab1.setContent(new StackPane()); // 设置选项卡1的内容区域
        Tab tab2 = new Tab("选项卡2");


        ImageView imageView = new ImageView();
        Image image = new Image("/icon.png"); // 图像文件的路径
        imageView.setImage(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);

        tab2.setClosable(false);
        tab2.setGraphic(imageView);

        tab2.setContent(new StackPane()); // 设置选项卡2的内容区域
        Tab tab3 = new Tab("选项卡3");
        tab3.setContent(new StackPane()); // 设置选项卡3的内容区域


        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setStyle("-fx-background-color: #f0ffff;");


        Scene scene = new Scene(tabPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TabPane示例");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
