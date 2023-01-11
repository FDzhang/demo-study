package org.example;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FxDemo1  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        JPanel cp = new JPanel(new BorderLayout());
        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);

        textArea.setText("    public TextEditorDemo() {\n" +
                "\n" +
                "        JPanel cp = new JPanel(new BorderLayout());\n" +
                "\n" +
                "        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);\n" +
                "        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);\n" +
                "        textArea.setCodeFoldingEnabled(true);\n" +
                "        RTextScrollPane sp = new RTextScrollPane(textArea);\n" +
                "        cp.add(sp);\n" +
                "\n" +
                "        setContentPane(cp);\n" +
                "        setTitle(\"Text Editor Demo\");\n" +
                "        setDefaultCloseOperation(EXIT_ON_CLOSE);\n" +
                "        pack();\n" +
                "        setLocationRelativeTo(null);\n" +
                "    }");

        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);

        StackPane root = new StackPane();
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(cp);
        root.getChildren().add(swingNode);

        Map<?, ?> bestPossibleAntiAliasHints = RSyntaxUtilities.getBestPossibleAntiAliasHints();

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
