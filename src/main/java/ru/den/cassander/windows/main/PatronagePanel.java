package ru.den.cassander.windows.main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import ru.den.cassander.handlers.ButtonPressureHandler;
import ru.den.cassander.windows.main.AbstractPanel;

import static java.awt.GridBagConstraints.*;
import static ru.den.cassander.Constants.*;

/**
 * Created on 11.09.2015.
 *
 * @author Denis Vereshchagin
 */
public class PatronagePanel extends AbstractPanel {

    private ButtonPressureHandler handler = new ButtonPressureHandler();

    private JButton createDocumentButton;

    private Blank blank1;
    private Blank blank2;
    private Blank[] blanks;

    public PatronagePanel() {
        super();
    }

    public Blank[] getBlanks() {
        return blanks;
    }

    public Blank getBlank1() {
        return blank1;
    }

    public Blank getBlank2() {
        return blank2;
    }

    public ButtonPressureHandler getHandler() {
        return handler;
    }

    public void createBlanks() {
        blank1 = new Blank((byte) 1);
        blank2 = new Blank((byte) 2);

        blanks = new Blank[2];
        blanks[0] = blank1;
        blanks[1] = blank2;
    }

    public void createButton() {
        createDocumentButton = new JButton("Создать документ");
        createDocumentButton.addActionListener(handler);
        add(createDocumentButton, new GridBagConstraints(4, 16, 1, 1, 0.9, 0.9, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(3, 1, 3, 1), 0, 0));
    }

    /**
     * Created on 07.08.2015.
     *
     * @author Denis Vereshchagin
     */
    public class Blank {
        @SuppressWarnings("UnusedDeclaration") private byte number;

        @SuppressWarnings("UnusedDeclaration") private JLabel blankLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel dateLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel patronageLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel monthsLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel complaintsLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel feedingLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel modeLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel conditionLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel skinLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel behaviorLabel;
        @SuppressWarnings("UnusedDeclaration") private JLabel indexesLabel;

        //private JTextField dateField;
        private JTextField patronageField;
        private JTextField modeField;
        private JTextField behaviorField;
        private JTextArea  indexesField;

        private JComboBox<String> complaintsField;
        private JComboBox<String> feedingField;
        private JComboBox<String> conditionField;
        private JComboBox<String> skinField;

        public Blank(byte number) {
            this.number = number;

            createLabels(number);
            createTextFields(number);
            createComboBoxes(number);
            createTextArea(number);

            fillTheDateField();
        }

        public JTextField getDateField() {
            return dateField;
        }

        public JTextField getPatronageField() {
            return patronageField;
        }

        public JComboBox<String> getComplaintsField() {
            return complaintsField;
        }

        public JComboBox<String> getFeedingField() {
            return feedingField;
        }

        public JTextField getModeField() {
            return modeField;
        }

        public JComboBox<String> getConditionField() {
            return conditionField;
        }

        public JComboBox<String> getSkinField() {
            return skinField;
        }

        public JTextField getBehaviorField() {
            return behaviorField;
        }

        public JTextArea getIndexesField() {
            return indexesField;
        }

        private void createLabels(byte number) {
            blankLabel      = createOneLabel("Бланк " + number, (byte) 2, (byte) 0, CENTER, number);
            dateLabel       = createOneLabel(DATE, (byte) 0, (byte) 1, CENTER, number);
            patronageLabel  = createOneLabel("Патронаж м/с в ", (byte) 2, (byte) 1, EAST, number);
            monthsLabel     = createOneLabel("мес. жизни", (byte) 4, (byte) 1, WEST, number);
            complaintsLabel = createOneLabel(COMPLAINTS, (byte) 1, (byte) 2, CENTER, number);
            feedingLabel    = createOneLabel("Вскармливание: ", (byte) 1, (byte) 3, CENTER, number);
            modeLabel       = createOneLabel("Режим: ", (byte) 3, (byte) 3, CENTER, number);
            conditionLabel  = createOneLabel("Состояние: ", (byte) 1, (byte) 4, CENTER, number);
            skinLabel       = createOneLabel("Кожа, слизистые: ", (byte) 3, (byte) 4, CENTER, number);
            behaviorLabel   = createOneLabel("Поведение: ", (byte) 1, (byte) 5, CENTER, number);
            indexesLabel    = createOneLabel("Показатели НПР: ", (byte) 1, (byte) 6, CENTER, number);
        }

        private JLabel createOneLabel(String text, byte gridX, byte gridY, int anchor, byte number) {
            JLabel label = new JLabel(text);

            add(label, new GridBagConstraints(gridX, gridY + getDeltaGridY(number), 1, 1, 0.9, 0.9,
                    anchor, NONE, new Insets(3, 1, 3, 1), 0, 0));

            return label;
        }

        private void createTextFields(byte number) {
            dateField       = createOneField((byte) 10, (byte) 1, (byte) 1, WEST, number);
            patronageField  = createOneField((byte) 10, (byte) 3, (byte) 1, WEST, number);
            modeField       = createOneField((byte) 15, (byte) 4, (byte) 3, WEST, number);
            behaviorField   = createOneField((byte) 30, (byte) 2, (byte) 5, WEST, number);
        }

        private void createComboBoxes(byte number) {
            complaintsField = createOneBox(new String[]{"у мамы нет"}, (byte) 2, (byte) 2, WEST, number);
            feedingField    = createOneBox(new String[]{"грудное", "искусственное", "смешанное"}, (byte)2, (byte)3, WEST, number);
            conditionField  = createOneBox(new String[]{"удовл-ное", "средней тяжести", "тяжёлое"}, (byte)2, (byte)4, WEST, number);
            skinField       = createOneBox(new String[]{"б/особенностей"}, (byte) 4, (byte) 4, WEST, number);
        }

        private JComboBox<String> createOneBox(String[] words, byte gridX, byte gridY, int anchor, byte number) {
            Vector<String> vector = new Vector<>();
            vector.addAll(Arrays.asList(words));

            JComboBox<String> comboBox = new JComboBox<>(vector);
            comboBox.setEditable(true);
            comboBox.setSelectedItem(EMPTY_STRING);

            add(comboBox, new GridBagConstraints(gridX, gridY + getDeltaGridY(number), 1, 1, 0.9, 0.9,
                    anchor, NONE, new Insets(3, 1, 3, 1), 0, 0));

            return comboBox;
        }

        private JTextField createOneField(byte size, byte gridX, byte gridY, int anchor, byte number) {
            JTextField field = new JTextField(size);

            add(field, new GridBagConstraints(gridX, gridY + getDeltaGridY(number), 1, 1, 0.9, 0.9,
                    anchor, NONE, new Insets(3, 1, 3, 1), 0, 0));

            return field;
        }

        private void createTextArea(byte number) {
            indexesField = new JTextArea(5, 30);

            indexesField.setWrapStyleWord(true);
            indexesField.setLineWrap(true);
            add(indexesField, new GridBagConstraints(2, 6 + getDeltaGridY(number), 1, 1, 0.9, 0.9,
                    WEST, NONE, new Insets(3, 1, 3, 1), 0, 0));
        }

        private byte getDeltaGridY(byte number) {
            byte deltaGridY = 0;

            switch (number) {
                case 1:
                /*NOP*/
                    break;

                case 2:
                    deltaGridY = 8;
                    break;

                default:
                    throw new IllegalArgumentException("аргумент \"number\" должен принимать значения 1 или 2.");
            }

            return deltaGridY;
        }
    }
}