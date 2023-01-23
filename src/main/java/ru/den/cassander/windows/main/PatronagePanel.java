package ru.den.cassander.windows.main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import ru.den.cassander.document_creators.PatronageCreator;
import ru.den.cassander.windows.CompleteWindow;

import static java.awt.GridBagConstraints.*;
import static ru.den.cassander.Constants.*;

/**
 * Created on 11.09.2015.
 * Updated on January 2023
 * Класс, создающий GUI для раздела "Патронаж"
 *
 * @author Denis Vereshchagin
 */
public class PatronagePanel extends AbstractPanel {

    private PatronagePanelController controller; // обработчик нажатия кнопки
    private Blank[] blanks;

    // конструктор
    public PatronagePanel() {
        super(); // дада, первым делом при вызове конструктора класса вызывается конструктор его родителя
        controller = new PatronagePanelController();
    }

    public Blank[] getBlanks() {
        return blanks;
    }

    // создает 2 бланка данной формы "Патронаж"
    public void createBlanks() {
        blanks = new Blank[2];
        blanks[0] = new Blank(1);
        blanks[1] = new Blank(2);
    }

    // создает кнопку "Создать документ"
    public void createButton() {
        // кнопка "Создать документ"
        JButton createDocumentButton = new JButton("Создать документ");
        createDocumentButton.addActionListener(e -> controller.createDocButtonClicked());
        add(createDocumentButton, new GridBagConstraints(4, 16, 1, 1, 0.9, 0.9, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(3, 1, 3, 1), 0, 0));
    }

    /**
     * Created on 07.08.2015.
     * Updated on January 2023
     * Шаблон документа "Патронаж" состоит из двух одинаковых шаблонов-"бланков": бланк 1 и бланк 2.
     * Данный класс описывает GUI для такого бланка.
     *
     * @author Denis Vereshchagin
     */
    public class Blank {
        @SuppressWarnings("UnusedDeclaration") private int blankNumber;

        // элементы GUI

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

        private JTextField patronageField;
        private JTextField modeField;
        private JTextField behaviorField;
        private JTextArea  indexesField;

        private JComboBox<String> complaintsField;
        private JComboBox<String> feedingField;
        private JComboBox<String> conditionField;
        private JComboBox<String> skinField;

        // конструктор
        public Blank(int blankNumber) {
            this.blankNumber = blankNumber;

            createAllLabels(blankNumber);
            createAllTextFields(blankNumber);
            createAllComboBoxes(blankNumber);
            createTextArea(blankNumber);

            fillTheDateField();
        }

        // и снова геттеры для элементов GUI, которые я использую в PatronageCreator
        // м.б. имеет смысл сделать так же, как я сделал в DirectoryChooserDialog ?
        // т.е. добавить сюда публичные методы, которые делают что-то необходимое с элементами GUI, и
        // вызывать их в PatronageCreator ???

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

        // создает все метки в бланке с номером blankNumber
        private void createAllLabels(int blankNumber) {
            blankLabel      = createLabel("Бланк " + blankNumber, 2, 0, CENTER, blankNumber);
            dateLabel       = createLabel(DATE, 0, 1, CENTER, blankNumber);
            patronageLabel  = createLabel("Патронаж м/с в ", 2, 1, EAST, blankNumber);
            monthsLabel     = createLabel("мес. жизни", 4, 1, WEST, blankNumber);
            complaintsLabel = createLabel(COMPLAINTS, 1, 2, CENTER, blankNumber);
            feedingLabel    = createLabel("Вскармливание: ", 1, 3, CENTER, blankNumber);
            modeLabel       = createLabel("Режим: ", 3, 3, CENTER, blankNumber);
            conditionLabel  = createLabel("Состояние: ", 1, 4, CENTER, blankNumber);
            skinLabel       = createLabel("Кожа, слизистые: ", 3, 4, CENTER, blankNumber);
            behaviorLabel   = createLabel("Поведение: ", 1, 5, CENTER, blankNumber);
            indexesLabel    = createLabel("Показатели НПР: ", 1, 6, CENTER, blankNumber);
        }

        // создает метку с текстом text в бланке с номером blankNumber
        private JLabel createLabel(String text, int gridX, int gridY, int anchor, int blankNumber) {
            JLabel label = new JLabel(text);

            add(label, new GridBagConstraints(gridX, gridY + getDeltaGridY(blankNumber), 1, 1, 0.9, 0.9,
                    anchor, NONE, new Insets(3, 1, 3, 1), 0, 0));

            return label;
        }

        // создает все текстовые поля в бланке с номером blankNumber
        private void createAllTextFields(int blankNumber) {
            dateField       = createTextField(10, 1, 1, WEST, blankNumber);
            patronageField  = createTextField(10, 3, 1, WEST, blankNumber);
            modeField       = createTextField(15, 4, 3, WEST, blankNumber);
            behaviorField   = createTextField(30, 2, 5, WEST, blankNumber);
        }

        // создает все выпадающие списки в бланке с номером blankNumber
        private void createAllComboBoxes(int blankNumber) {
            complaintsField = createComboBox(new String[]{"у мамы нет"},2,2, WEST, blankNumber);
            feedingField    = createComboBox(new String[]{"грудное", "искусственное", "смешанное"},2,3, WEST, blankNumber);
            conditionField  = createComboBox(new String[]{"удовл-ное", "средней тяжести", "тяжёлое"},2,4, WEST, blankNumber);
            skinField       = createComboBox(new String[]{"б/особенностей"},4,4, WEST, blankNumber);
        }

        // создает выпадающий список, содержащий элементы из массива words, в бланке с номером blankNumber
        private JComboBox<String> createComboBox(String[] words, int gridX, int gridY, int anchor, int blankNumber) {
            // используем здесь Vector, а не ArrayList, т.к. ниже мы используем конструктор JComboBox(Vector v)
            // Конструктора JComboBox(List list) в классе JComboBox нет
            Vector<String> vector = new Vector<>();
            vector.addAll(Arrays.asList(words));

            JComboBox<String> comboBox = new JComboBox<>(vector);
            comboBox.setEditable(true);
            comboBox.setSelectedItem(EMPTY_STRING);

            add(comboBox, new GridBagConstraints(gridX, gridY + getDeltaGridY(blankNumber), 1, 1, 0.9, 0.9,
                    anchor, NONE, new Insets(3, 1, 3, 1), 0, 0));

            return comboBox;
        }

        // создает текстовое поле в бланке с номером blankNumber
        private JTextField createTextField(int size, int gridX, int gridY, int anchor, int blankNumber) {
            JTextField field = new JTextField(size);

            add(field, new GridBagConstraints(gridX, gridY + getDeltaGridY(blankNumber), 1, 1, 0.9, 0.9,
                    anchor, NONE, new Insets(3, 1, 3, 1), 0, 0));

            return field;
        }

        // создает текстовую область в бланке с номером blankNumber
        private void createTextArea(int blankNumber) {
            indexesField = new JTextArea(5, 30);

            indexesField.setWrapStyleWord(true);
            indexesField.setLineWrap(true);
            add(indexesField, new GridBagConstraints(2, 6 + getDeltaGridY(blankNumber), 1, 1, 0.9, 0.9,
                    WEST, NONE, new Insets(3, 1, 3, 1), 0, 0));
        }

        private int getDeltaGridY(int blankNumber) {
            int deltaGridY = 0;

            switch (blankNumber) {
                case 1:
                /*NOP*/
                    break;

                case 2:
                    deltaGridY = 8;
                    break;

                default:
                    throw new IllegalArgumentException("аргумент \"blankNumber\" должен принимать значения 1 или 2.");
            }

            return deltaGridY;
        }
    }

    /**
     * Created on January 2023
     * Класс-обработчик событий диалогового окна "Выбор папки для хранения документов"
     *
     * @author Denis Vereshchagin
     */
    private class PatronagePanelController {

        // TODO продолжение ошибки, описанной в DirectoryChooserDialogController
        /*
        * 1. в файле настроек было следующее (я добавил чисто поржать и забыл про это):
        * <directory type="CURRENT">
            <path>блаблабла</path>
          </directory>
        *
        * 2. открыл "Новый патронаж", заполнил данные
        * 3. нажал "Создать документ"
        * 4. Вылезло окно "Готово !" (в коде - CompleteWindow, вроде), с текстом "Файл блаблаблаПатронаж__02_01_2023 успешно создан"
        * при этом прога никаких ошибок и исключений не выдает
        * 5. после нажатия на "Открыть файл" прога выдает исключение
        */

        // обработчик нажатия кнопки "Создать документ"
        private void createDocButtonClicked() {
            PatronageCreator creator = new PatronageCreator();
            creator.createDocument();

            CompleteWindow completeWindow = new CompleteWindow(creator.DESTINATION_FILE_PATH);
            completeWindow.setVisible(true);
        }

    }
}