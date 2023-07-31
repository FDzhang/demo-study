package cn.fd.javafx1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MyController113 {

    @FXML
    private Button b1;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label l1;

    public MyController113() {
    }

    @FXML
    private void initialize() {
        System.out.println("initialize");
        System.out.println(l1.getText());

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("新的value = " + newValue);
            }
        });
    }

    @FXML
    private void action(ActionEvent actionEvent) {
        System.out.println("fxml action");
    }

    public Button getB1() {
        return b1;
    }

    public Label getL1() {
        return l1;
    }
}
