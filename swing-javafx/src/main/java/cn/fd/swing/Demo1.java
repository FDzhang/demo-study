package cn.fd.swing;


import javax.swing.*;
import java.awt.*;

public class Demo1 extends JFrame {


}
 class ApplicationRunner {
    public static void main(String[] args) {
        new MainFrame();
    }
}
class MainFrame extends JFrame {
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel graph = new JPanel();
        graph.setName("Graph");
        graph.setBounds(0, 0, 800, 600);
        graph.setBackground(Color.BLACK);
        graph.setLayout(null);

        Vertex vertex0 = new Vertex(0, 0, "0");
        graph.add(vertex0);

        Vertex vertex1 = new Vertex(750, 0, "1");
        graph.add(vertex1);

        Vertex vertex2 = new Vertex(0, 550, "2");
        graph.add(vertex2);

        Vertex vertex3 = new Vertex(750, 550, "3");
        graph.add(vertex3);

        add(graph);

        setVisible(true);
    }
}

class Vertex extends JPanel {
    private static final int VERTEX_SIZE = 50;

    public Vertex(int x, int y, String labelText) {
        setName(String.format("Vertex %s", labelText));
        setBounds(x, y, VERTEX_SIZE, VERTEX_SIZE);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        JLabel label = new JLabel(labelText);
        label.setName(String.format("VertexLabel %s", labelText));
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillOval(0, 0, VERTEX_SIZE, VERTEX_SIZE);
    }
}