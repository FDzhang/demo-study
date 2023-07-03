module swing.javafx {
    requires javafx.swing;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires org.fife.RSyntaxTextArea;

    opens cn.fd to javafx.web,javafx.media,javafx.swing,javafx.controls,javafx.fxml,javafx.base,javafx.graphics;
    opens cn.fd.javafx to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media, javafx.swing, javafx.web;

}