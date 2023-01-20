package ru.den.cassander.windows;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 09.08.2015.
 *
 * @author Denis Vereshchagin
 */

abstract class AbstractWindow extends JFrame {

    protected final String TITLE; // "пустая" константа (значение можно задавать не сразу, а в конструкторе (обязательно) )
    protected final int WIDTH;
    protected final int HEIGHT;
    protected final int OPERATION;

    protected AbstractWindow(String title, int width, int height, int operationOnClose) {
        TITLE = title;
        WIDTH = width;
        HEIGHT = height;
        OPERATION = operationOnClose;

        initialize(TITLE, WIDTH, HEIGHT, OPERATION);
        createPanel();
    }

    public void initialize(String title, int width, int height, int operationOnClose) {
        setTitle(title);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(operationOnClose);
        setLayout(new BorderLayout());
    }

    protected JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void createPanel() {
        panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        add(panel, BorderLayout.CENTER);
    }
}