package ru.den.cassander.gui.main;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static ru.den.cassander.Constants.EMPTY_STRING;

/**
 * Created on 11.09.2015.
 */
abstract class AbstractPanel extends JPanel {

    protected JTextField dateField;

    public JTextField getDateField() {
        return dateField;
    }

    public String getDate() {
        return dateField.getText();
    }

    protected AbstractPanel() {
        initialize();
    }

    public void initialize() {
        setBackground(Color.GRAY);
        setLayout(new GridBagLayout());
    }

    public void resetComponent(JComponent component) {
        Component[] components = component.getComponents();

        for (Component c : components) {
            if (c instanceof JTextField && c != dateField) {
                ((JTextField) c).setText(EMPTY_STRING);
            } else if (c instanceof JTextArea) {
                ((JTextArea) c).setText(EMPTY_STRING);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(-1);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if (c instanceof JPanel) {
                resetComponent((JPanel) c);
            } else if (c instanceof Box) {
                resetComponent((Box) c);
            } else if (c instanceof JTabbedPane) {
                resetComponent((JTabbedPane) c);
            } else if (c instanceof JScrollPane) {
                resetComponent(((JScrollPane) c).getViewport());
            }
        }
    }

    // устанавливает дату в текстовом поле "Дата"
    protected void fillTheDateField() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateField.setText(dateFormat.format(new Date()) + " г.");
    }

    protected void setBackgroundColor(Color color) {

    }
}