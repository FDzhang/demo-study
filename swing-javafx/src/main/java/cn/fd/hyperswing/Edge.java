package cn.fd.hyperswing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Edge extends JComponent {
    private final Vertex start;
    private final Vertex end;

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;

        setName(String.format("Edge <%s -> %s>", start.getVertexId(), end.getVertexId()));
        setSize(MainFrame.APP_WIDTH, MainFrame.GRAPH_HEIGHT);
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    // Get edge angle.
    //
    // Return values indicate the following:
    //  -135 -90 -45
    //     \  |  /
    //      \ | /
    // 180 -- x -- 0
    //      / | \
    //     /  |  \
    //   135  90  45
    public Double getEdgeAngle() {
        double startX = start.getCenterX();
        double startY = start.getCenterY();
        double endX = end.getCenterX();
        double endY = end.getCenterY();

        if ((startX == endX) && (startY > endY)) {
            return -90.0;
        } else if ((startY == endY) && (startX < endX)) {
            return 0.0;
        } else if ((startX == endX) && (startY < endY)) {
            return 90.0;
        } else if ((startY == endY) && (startX > endX)) {
            return 180.0;
        } else if ((startX == endX) && (startY == endY)) {
            throw new IllegalArgumentException();
        }

        double dx = endX - startX;
        double dy = endY - startY;
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Line2D line = new Line2D.Float((float) start.getCenterX(), (float) start.getCenterY(), (float) end.getCenterX(), (float) end.getCenterY());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2.0F));
        g2d.draw(line);
    }
}
