package ru.den.cassander.windows;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.windows.main.ExaminationOfTheAdultPanelConstants.BIOCHEMICAL_LIST_ITEMS;
import static ru.den.cassander.windows.main.ExaminationOfTheAdultPanelConstants.EXAMINATION_LIST_ITEMS;
import static ru.den.cassander.windows.main.ExaminationOfTheAdultPanelConstants.LIPIDOGRAM_LIST_ITEMS;
import static ru.den.cassander.Constants.EMPTY_STRING;

/**
 * Created on 29.09.2015.
 *
 * Диалоговое окно "Обследование".
 * Файл -> Новый осмотр взрослого -> кнопка "Добавить"
 *
 * @author Denis Vereshchagin
 */
public class ExaminationDialog extends AbstractDialog {

    private static final byte HORIZONTAL = 0;
    private static final byte VERTICAL = 1;

    public ExaminationDialog() {
        super(getMainWindow(), "Обследование", 450, 500, new BorderLayout());

        setModalityType(ModalityType.APPLICATION_MODAL);

        createExaminationPanel();
        createBiochemicalPanel();
        createLipidogramPanel();

        createBox();
        createOkPanel();

        addComponents();
    }

    private JList<String> examinationList;
    private JList<String> biochemicalList;
    private JList<String> lipidogramList;
    private JScrollPane examinationScrollPane;
    private JScrollPane biochemicalScrollPane;
    private JScrollPane lipidogramScrollPane;
    private JCheckBox biochemicalBox;
    private JCheckBox lipidogramBox;
    private JButton okButton;
    private JPanel okPanel;
    private JPanel examinationPanel;
    private JPanel biochemicalPanel;
    private JPanel lipidogramPanel;
    private Box box;

    private void createExaminationPanel() {
        examinationList = createList(EXAMINATION_LIST_ITEMS);
        examinationScrollPane = new JScrollPane(examinationList);
        examinationPanel = createPanel(new Component[]{examinationScrollPane});
    }

    private void createBiochemicalPanel() {
        biochemicalBox = new JCheckBox("Биохимический анализ");
        biochemicalList = createList(BIOCHEMICAL_LIST_ITEMS);
        biochemicalList.setEnabled(false);
        biochemicalBox.addChangeListener(event -> biochemicalList.setEnabled(biochemicalBox.isSelected()));
        biochemicalScrollPane = new JScrollPane(biochemicalList);

        biochemicalPanel = createPanel(new Component[]{biochemicalBox, biochemicalScrollPane});
    }

    private void createLipidogramPanel() {
        lipidogramBox = new JCheckBox("Липидограмма");
        lipidogramList = createList(LIPIDOGRAM_LIST_ITEMS);
        lipidogramList.setEnabled(false);
        lipidogramBox.addChangeListener(event -> lipidogramList.setEnabled(lipidogramBox.isSelected()));
        lipidogramScrollPane = new JScrollPane(lipidogramList);

        lipidogramPanel = createPanel(new Component[]{lipidogramBox, lipidogramScrollPane});
    }

    private void createBox() {
        box = Box.createVerticalBox();
        box.add(examinationPanel);
        box.add(createBox(HORIZONTAL, new Component[]{biochemicalPanel, lipidogramPanel}));
    }

    private Box createBox(byte orientation, Component[] components) {
        Box box;

        if (orientation == HORIZONTAL) {
            box = Box.createHorizontalBox();
        } else if (orientation == VERTICAL) {
            box = Box.createVerticalBox();
        } else throw new IllegalArgumentException("orientation must be HORIZONTAL or VERTICAL");

        for (Component component : components) {
            box.add(component);
        }

        return box;
    }

    private JList createList(String[] items) {
        JList<String> list = new JList<>(items);
        return list;
    }

    private List<String> selectedValues;
    private String text;
    private void createOkPanel() {
        okButton = new JButton("ОК");
        okButton.setPreferredSize(new Dimension(70, 27));
        okButton.addActionListener(event -> {
            JTextArea area = getMainWindow().getExaminationPanel().getExaminationArea();

            selectedValues = examinationList.getSelectedValuesList();
            text = String.join("\n", selectedValues);
            area.setText(text);

            handleCheckBoxPressure(biochemicalBox, biochemicalList);
            handleCheckBoxPressure(lipidogramBox, lipidogramList);

            close();
        });

        okPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        okPanel.setBackground(Color.WHITE);
        okPanel.add(okButton);
    }

    private void addComponents() {
        panel.add(box);
        panel.add(okPanel, BorderLayout.SOUTH);
    }

    private JPanel createPanel(Component[] components) {
        Box box = Box.createVerticalBox(); // BoxLayout

        for (Component component : components) {
            box.add(component);
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(box);
        return panel;
    }

    private void close() {
        this.dispose();
    }

    private String addCarriageReturnIfNeeded(String text) {
        if (!text.equals(EMPTY_STRING)) {
            text = "\n";
        } else {
            text = EMPTY_STRING;
        }

        return text;
    }

    private void handleCheckBoxPressure(JCheckBox box, JList<String> list){
        if (box.isSelected()) {
            selectedValues = list.getSelectedValuesList();
            text = addCarriageReturnIfNeeded(text);
            text += box.getText() + ": " + String.join(", ", selectedValues);
            getMainWindow().getExaminationPanel().getExaminationArea().append(text);
        }
    }
}