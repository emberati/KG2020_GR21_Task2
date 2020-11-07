package ru.vsu.cs.kg2020.emb.main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel mainPanel;
    private final Dimension MAIN_DIMENSION;

    {
        MAIN_DIMENSION = new Dimension(800, 600);
    }

    public MainFrame() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(MAIN_DIMENSION);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        /*      DrawPanel       */
        mainPanel = new MainPanel(MAIN_DIMENSION);
        mainPanel.setVisible(true);
        add(mainPanel);
    }
}
