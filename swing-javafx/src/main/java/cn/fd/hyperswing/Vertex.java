package cn.fd.hyperswing;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JPanel {
    private static final int VERTEX_DIAMETER = 50;

    private final String vertexId;
    private final int centerX;
    private final int centerY;
    private final int radius;
    private Color color;

    public Vertex(String vertexId, int centerX, int centerY) {
        this.vertexId = vertexId;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = Vertex.VERTEX_DIAMETER / 2;
        int left = centerX - radius;
        int top = centerY - radius;
        setColorNormal();

        setName(String.format("Vertex %s", vertexId));
        setBounds(left, top, VERTEX_DIAMETER, VERTEX_DIAMETER);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        JLabel label = new JLabel(vertexId);
        label.setName(String.format("VertexLabel %s", vertexId));
        add(label);
    }

    public String getVertexId() {
        return vertexId;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setColorNormal() {
        this.color = Color.WHITE;
    }

    public void setColorSelected() {
        this.color = Color.GREEN;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.fillOval(0, 0, VERTEX_DIAMETER, VERTEX_DIAMETER);
    }
}