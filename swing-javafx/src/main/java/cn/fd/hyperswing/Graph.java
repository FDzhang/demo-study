package cn.fd.hyperswing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Graph extends JPanel {
    private final MainFrame mainFrame;

    private final HashMap<String, Vertex> vertexList = new HashMap<>();
    private final HashMap<String, Edge> edgeList = new HashMap<>();
    private String firstEdgeVertexId;
    private String secondEdgeVertexId;

    public Graph(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setName("Graph");
        setBounds(0, MainFrame.STATUS_HEIGHT, MainFrame.APP_WIDTH, MainFrame.GRAPH_HEIGHT);
        setBackground(Color.BLACK);
        setLayout(null);


        addMouseListener(new GraphPanelClickListener());
    }

    // Reset edge vertices.
    public void resetEdgeVertices() {
        firstEdgeVertexId = null;
        secondEdgeVertexId = null;
    }

    // Handle graph panel mouse clicks.
    private class GraphPanelClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Check if vertex was clicked.
            String clickedVertexId = getClickedVertex(e.getX(), e.getY());
            if (clickedVertexId != null) {
                // Are we in add edge mode?
                if (mainFrame.getCurrentMode() == MainFrame.Mode.EDGE) {
                    // Add new edge.
                    addEdge(clickedVertexId);
                }
            } else {
                // Are we in add vertex mode?
                if (mainFrame.getCurrentMode() == MainFrame.Mode.VERTEX) {
                    // Add new vertex.
                    addVertex(e);
                }
            }
        }
    }

    // Get clicked vertex.
    private String getClickedVertex(int x, int y) {
        // Loop through vertices.
        for (Map.Entry<String, Vertex> vertexEntry : vertexList.entrySet()) {
            // Calculate distance between entry and center of vertex.
            Vertex vertex = vertexEntry.getValue();
            double distance = Math.sqrt(Math.pow(x - vertex.getCenterX(), 2) + Math.pow(y - vertex.getCenterY(), 2));
            // Is distance within radius of vertex circle?
            if (distance <= vertex.getRadius()) {
                return vertexEntry.getKey();
            }
        }

        return null;
    }

    // Add new vertex.
    private void addVertex(MouseEvent e) {
        // Ask user for vertex id.
        String vertexId;
        do {
            vertexId = JOptionPane.showInputDialog(null, "Enter the Vertex ID (Should be 1 char):", "Vertex", JOptionPane.INFORMATION_MESSAGE);
            if (vertexId == null) {
                return;
            }
            if (vertexList.containsKey(vertexId)) {
                vertexId = "BAD";
            }
        } while (vertexId.trim().length() != 1);

        // Add vertex to graph panel.
        Vertex vertex = new Vertex(vertexId, e.getX(), e.getY());
        add(vertex);
        repaintPanel();

        // Add vertex to list.
        vertexList.put(vertexId, vertex);
    }

    // Add new edge.
    private void addEdge(String clickedVertexId) {
        // Is this first vertex for edge?
        if (firstEdgeVertexId == null) {
            // Save first vertex.
            firstEdgeVertexId = clickedVertexId;

            // Highlight first vertex.
            vertexList.get(firstEdgeVertexId).setColorSelected();
            repaintPanel();
        } else {
            // Did user click on first vertex again?
            if (clickedVertexId.equals(firstEdgeVertexId)) {
                // Unhighlight first vertex.
                vertexList.get(firstEdgeVertexId).setColorNormal();
                repaintPanel();

                // Reset first vertex.
                firstEdgeVertexId = null;

                return;
            }

            // Save second vertex.
            secondEdgeVertexId = clickedVertexId;

            // Highlight second vertex.
            vertexList.get(secondEdgeVertexId).setColorSelected();
            repaintPanel();

            // Ask user for weight.
            Integer weight;
            do {
                String input;
                input = JOptionPane.showInputDialog(null, "Enter Weight:", "Input", JOptionPane.INFORMATION_MESSAGE);
                if (input == null) {
                    // Unhighlight second vertex.
                    vertexList.get(secondEdgeVertexId).setColorNormal();
                    repaintPanel();

                    // Reset second vertex.
                    secondEdgeVertexId = null;

                    return;
                }
                try {
                    weight = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    weight = null;
                }
            } while (weight == null);

            // Add forward edge to graph panel.
            Edge forwardEdge = new Edge(vertexList.get(firstEdgeVertexId), vertexList.get(clickedVertexId));
            add(forwardEdge);

            // Add forward edge to list.
            edgeList.put(String.format("%s->%s", firstEdgeVertexId, clickedVertexId), forwardEdge);

            // Add backward edge to graph panel.
            Edge backwardEdge = new Edge(vertexList.get(clickedVertexId), vertexList.get(firstEdgeVertexId));
            add(backwardEdge);

            // Add backward edge to list.
            edgeList.put(String.format("%s->%s", clickedVertexId, firstEdgeVertexId), backwardEdge);

            // Add label for edge.
            addLabel(forwardEdge, weight);

            // Unhighlight first and second vertices.
            vertexList.get(firstEdgeVertexId).setColorNormal();
            vertexList.get(secondEdgeVertexId).setColorNormal();

            // Reset edge vertices.
            resetEdgeVertices();

            repaintPanel();
        }
    }

    // Add label for edge.
    private void addLabel(Edge edge, int weight) {
        // Calculate middle of edge.
        Vertex start = edge.getStart();
        Vertex end = edge.getEnd();
        int edgeMiddleX = Math.min(start.getCenterX(), end.getCenterX()) + Math.abs(start.getCenterX() - end.getCenterX()) / 2;
        int edgeMiddleY = Math.min(start.getCenterY(), end.getCenterY()) + Math.abs(start.getCenterY() - end.getCenterY()) / 2;

        // Position label next to edge.
        final int range = 20;
        int xOffset = 0;
        int yOffset = 0;
        Double edgeAngle = edge.getEdgeAngle();
        // Is line pretty horizontal? (Put label above line.)
        if ((edgeAngle > -range) && (edgeAngle < range)) {
            yOffset = -20;
        } else if ((edgeAngle < -180 + range) || (edgeAngle > 180 - range)) {
            yOffset = -20;
        }
        // Is line pretty vertical? (Put label to right of line.)
        else if ((edgeAngle > -90 - range) && (edgeAngle < -90 + range)) {
            xOffset = 10;
        } else if ((edgeAngle < 90 + range) && (edgeAngle > 90 - range)) {
            xOffset = 10;
        }
        // Line is somewhere in between. (Put label above and to right of line.)
        else {
            xOffset = 13;
            yOffset = -13;
        }

        JLabel label = new JLabel(String.valueOf(weight));
        label.setName(String.format("EdgeLabel <%s -> %s>", start.getVertexId(), end.getVertexId()));
        label.setBounds(edgeMiddleX + xOffset, edgeMiddleY + yOffset, 40, 15);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setForeground(Color.WHITE);
        add(label);
    }

    // Repaint graph panel.
    private void repaintPanel() {
        repaint();
        revalidate();
    }
}