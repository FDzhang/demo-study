package cn.fd.javafx1;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;

public class TreeNode extends TreeItem<String> {
    private SimpleStringProperty name;
    private String json;

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
