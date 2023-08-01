package cn.fd.javafx1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DemoController118  implements Initializable {

    public DemoController118() {
        System.out.println("DemoController118");
    }

    @FXML
    private void action() {
        System.out.println("hello world");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialize");
        System.out.println(url.getPath());
    }
}
