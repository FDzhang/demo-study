package cn.fd.javafx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


public class Demo118 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fx = new FXMLLoader(Demo118.class.getClassLoader().getResource("Demo118.fxml"));

        Locale.setDefault(new Locale("en", "us"));

        ResourceBundle rb = ResourceBundle.getBundle("language");
        fx.setResources(rb);



        AnchorPane root = fx.load();

        Locale locale = Locale.getDefault();
        System.out.println(locale.getLanguage());
        System.out.println(locale.getCountry());


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
