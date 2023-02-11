package cn.fd.screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Demo1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen screen = Screen.getPrimary();

        double dpi = screen.getDpi();
        System.err.println("dpi: " + dpi);


        Rectangle2D bounds = screen.getBounds();
        Rectangle2D visualBounds = screen.getVisualBounds();

        // 全部屏幕的宽高和坐标
        System.out.println("左上角x: " + bounds.getMinX());
        System.out.println("左上角y: " + bounds.getMinY());
        System.out.println("右下角x: " + bounds.getMaxX());
        System.out.println("右下角y: " + bounds.getMaxY());

        // 可视屏幕的宽高和坐标
        System.out.println("左上角x: " + visualBounds.getMinX());
        System.out.println("左上角y: " + visualBounds.getMinY());
        System.out.println("右下角x: " + visualBounds.getMaxX());
        System.out.println("右下角y: " + visualBounds.getMaxY());

        Platform.exit();;
    }
}
