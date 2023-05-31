package ru.den.cassander.gui;

import javax.swing.*;
import java.awt.*;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.Constants.*;

/**
 * Created on 05.09.2015.
 */
public class AboutDialog extends AbstractDialog {

    public AboutDialog() {
        super(getMainWindow(), ABOUT, 500, 300, new FlowLayout());
        createLabel();
    }

    private JLabel aboutLabel;

    private void createLabel() {
        aboutLabel = new JLabel("<html><i>Программа \"Кассандр\"</i>");
        Font labelFont = new Font("Verdana", Font.BOLD, 20);
        aboutLabel.setFont(labelFont);
        aboutLabel.setBackground(Color.CYAN);
        aboutLabel.setForeground(Color.RED);

        panel.add(aboutLabel);
    }
}