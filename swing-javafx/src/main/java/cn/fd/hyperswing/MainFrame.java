package cn.fd.hyperswing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 600;

    public static final int STATUS_HEIGHT = 20;
    public static final int GRAPH_HEIGHT = APP_HEIGHT - STATUS_HEIGHT;

    public enum Mode {
        VERTEX("Add a Vertex"),
        EDGE("Add an Edge"),
        NONE("None");

        public final String label;

        Mode(String label) {
            this.label = label;
        }
    }

    private Mode currentMode = Mode.VERTEX;

    private final JLabel currentModeLabel = new JLabel();

    private final Graph graphPanel = new Graph(this);

    // Initialize main frame.
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(APP_WIDTH, APP_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);

        // Add current mode panel and label.
        JPanel labelPanel = new JPanel();
        labelPanel.setBounds(0, 0, APP_WIDTH, STATUS_HEIGHT);
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setLayout(null);

        currentModeLabel.setName("Mode");
        currentModeLabel.setBounds(APP_WIDTH - 205, 0, 200, STATUS_HEIGHT);
        currentModeLabel.setHorizontalAlignment(JLabel.RIGHT);
        currentModeLabel.setForeground(Color.WHITE);
        labelPanel.add(currentModeLabel);
        add(labelPanel);
        updateModeLabel();

        // Add graph panel.
        add(graphPanel);

        // Add menu bar.
        addMenuBar();

        setVisible(true);
    }

    public Mode getCurrentMode() {
        return currentMode;
    }

    // Add menu bar.
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setName("MenuBar");

        JMenu modeMenu = new JMenu("Mode");
        modeMenu.setName("Mode");

        MenuActionListener menuActionListener = new MenuActionListener();

        JMenuItem addVertexMenuItem = new JMenuItem(Mode.VERTEX.label);
        addVertexMenuItem.setName(Mode.VERTEX.label);
        addVertexMenuItem.addActionListener(menuActionListener);

        JMenuItem addEdgeMenuItem = new JMenuItem(Mode.EDGE.label);
        addEdgeMenuItem.setName(Mode.EDGE.label);
        addEdgeMenuItem.addActionListener(menuActionListener);

        JMenuItem noneMenuItem = new JMenuItem(Mode.NONE.label);
        noneMenuItem.setName(Mode.NONE.label);
        noneMenuItem.addActionListener(menuActionListener);

        modeMenu.add(addVertexMenuItem);
        modeMenu.add(addEdgeMenuItem);
        modeMenu.add(noneMenuItem);

        menuBar.add(modeMenu);
        setJMenuBar(menuBar);
    }

    // Handle menu item selection.
    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(Mode.VERTEX.label)) {
                currentMode = Mode.VERTEX;
                updateModeLabel();
            }
            if (e.getActionCommand().equals(Mode.EDGE.label)) {
                currentMode = Mode.EDGE;
                updateModeLabel();
            }
            if (e.getActionCommand().equals(Mode.NONE.label)) {
                currentMode = Mode.NONE;
                updateModeLabel();
            }
        }
    }

    // Update current mode label.
    private void updateModeLabel() {
        currentModeLabel.setText(String.format("Current Mode -> %s", currentMode.label));
        graphPanel.resetEdgeVertices();
    }
}