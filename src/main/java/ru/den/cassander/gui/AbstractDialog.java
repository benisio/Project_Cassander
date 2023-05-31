package ru.den.cassander.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 06.09.2015.
 */
abstract class AbstractDialog extends JDialog {

    protected final String TITLE; // "пустая" константа (значение можно задавать не сразу, а в конструкторе (обязательно) )
    protected final int    WIDTH;
    protected final int    HEIGHT;

    protected AbstractDialog(Frame parent, String title, int width, int height, LayoutManager layoutManager) {
        super(parent);
        TITLE = title;
        WIDTH = width;
        HEIGHT = height;

        initialize(TITLE, WIDTH, HEIGHT);
        createPanel(layoutManager);
    }

    public void initialize(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setResizable(false);
        //setAlwaysOnTop(true);
        //setModalityType(ModalityType.APPLICATION_MODAL); // TODO exception because of this line
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    protected JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    private void createPanel(LayoutManager manager) { // public
        panel = new JPanel(manager);
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);
    }
}