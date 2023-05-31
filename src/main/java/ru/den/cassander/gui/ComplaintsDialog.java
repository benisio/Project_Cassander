package ru.den.cassander.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.Constants.*;
import static ru.den.cassander.gui.main.ExaminationOfTheAdultPanelConstants.*;

/**
 * Created on 26.09.2015.
 */
public class ComplaintsDialog extends AbstractDialog {

    public ComplaintsDialog() {
        super(getMainWindow(), COMPLAINTS, 500, 230, new BorderLayout());
        setModalityType(ModalityType.APPLICATION_MODAL);

        createList();
        createOkPanel();

        addComponents();
    }

    private JList<String> complaintsList;
    private JScrollPane scrollPane;

    private void createList() {
        complaintsList = new JList<>(COMPLAINTS_LIST_ITEMS);
        scrollPane = new JScrollPane(complaintsList);
    }

    private JButton okButton;
    private JPanel okPanel;

    private void createOkPanel() {
        okButton = new JButton("ОК");
        okButton.setPreferredSize(new Dimension(70, 27));
        okButton.addActionListener(event -> {
            List<String> selectedValues = complaintsList.getSelectedValuesList();
            String complaints = String.join(", ", selectedValues);

            JTextArea area = getMainWindow().getExaminationPanel().getComplaintsArea();
            area.setText(complaints);

            this.dispose();
        });

        okPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        okPanel.setBackground(Color.WHITE);
        okPanel.add(okButton);
    }

    private void addComponents() {
        panel.add(scrollPane);
        panel.add(okPanel, BorderLayout.SOUTH);
    }
}