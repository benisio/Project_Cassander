package ru.den.cassander.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.Constants.EMPTY_STRING;
import static ru.den.cassander.windows.main.ExaminationOfTheAdultPanelConstants.*;

/**
 * Created on 01.10.2015.
 *
 * @author Denis Vereshchagin
 */

public class TherapyPreparationsDialog extends AbstractDialog {

    // константы, с помощью которых задается ориентация объекта Box
    private enum Orientation {
        HORIZONTAL, VERTICAL
    }

    // количество текстовых полей и списков
    private static final byte SIZE = 5;

    public TherapyPreparationsDialog() {

        // инициализация диалога
        super(getMainWindow(), "Препараты - медикаментозная терапия", 600, 230, new BorderLayout());
        setModalityType(ModalityType.APPLICATION_MODAL);

        // создание основных компонентов
        createFormBox();
        createFieldsBox();
        createPeridiocityBox();
        createMethodsBox();
        createOkPanel();

        addComponents();
    }

    private JComboBox[] formLists = new JComboBox[SIZE];
    private JTextField[] preparationsFields = new JTextField[SIZE];
    private JComboBox[] peridiocityLists = new JComboBox[SIZE];
    private JComboBox[] methodsLists = new JComboBox[SIZE];

    private Box formBox;
    private Box fieldsBox;
    private Box peridiocityBox;
    private Box methodsBox;


    private void createFormBox() {
        formBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Форма");
        label.setPreferredSize(new Dimension(50, 25));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        formBox.add(label);

        for (byte i = 0; i < formLists.length; i++) {
            formLists[i] = createComboBox(URGENT_CARE_PREPARATIONS_FORM_LIST_ITEMS);
            formLists[i].setEnabled(false);
            formBox.add(formLists[i]);
        }
    }

    // создает столбец текстовых полей "Препарат"
    private void createFieldsBox() {
        fieldsBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Препарат");
        label.setPreferredSize(new Dimension(50, 25));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        fieldsBox.add(label);

        for (byte i = 0; i < preparationsFields.length; i++) {
            preparationsFields[i] = new JTextField(20);
            preparationsFields[i].addKeyListener(new TextTypedHandler());
            fieldsBox.add(preparationsFields[i]);
        }
    }

    // создает списки "Периодичность"
    private void createPeridiocityBox() {
        peridiocityBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Периодичность применения");
        label.setPreferredSize(new Dimension(50, 25));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        peridiocityBox.add(label);
        //peridiocityBox.add(new JLabel(convertToMultiline("Периодичность\nприменения")));

        for (byte i = 0; i < peridiocityLists.length; i++) {
            peridiocityLists[i] = createComboBox(PERIODICITY_LIST_ITEMS);
            peridiocityLists[i].setEnabled(false);
            peridiocityBox.add(peridiocityLists[i]);
        }
    }

    // создает списки "Способ приема"
    private void createMethodsBox() {
        methodsBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Способ применения");
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label.setPreferredSize(new Dimension(50, 25));
        methodsBox.add(label);

        for (byte i = 0; i < methodsLists.length; i++) {
            methodsLists[i] = createComboBox(THERAPY_METHODS_LIST_ITEMS);
            methodsLists[i].setEnabled(false);
            methodsBox.add(methodsLists[i]);
        }
    }

    private Box createBox(Orientation orientation, Component[] components) {
        Box box;

        if (orientation == Orientation.HORIZONTAL) {
            box = Box.createHorizontalBox();
        } else if (orientation == Orientation.VERTICAL) {
            box = Box.createVerticalBox();
        } else throw new IllegalArgumentException("orientation must be HORIZONTAL or VERTICAL");

        for (Component component : components) {
            box.add(component);
        }

        return box;
    }

    private Box createBox(Orientation orientation) {
        return createBox(orientation, new Component[]{});
    }

    private JComboBox<String> createComboBox(String[] words) {
        Vector<String> vector = new Vector<>();
        vector.addAll(Arrays.asList(words));

        JComboBox<String> comboBox = new JComboBox<>(vector);
        comboBox.setSelectedIndex(-1);

        return comboBox;
    }

    private JButton okButton;
    private JPanel okPanel;
    private String text;

    private void createOkPanel() {
        okButton = new JButton("ОК");
        okButton.setPreferredSize(new Dimension(70, 27));
        okButton.addActionListener(new OkButtonClickHandler());

        okPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        okPanel.setBackground(Color.WHITE);
        okPanel.add(okButton);
    }

    // добавляет компоненты на панель диалога
    private void addComponents() {
        panel.add(createBox(Orientation.HORIZONTAL, new Component[]{formBox, fieldsBox, peridiocityBox, methodsBox}));
        panel.add(okPanel, BorderLayout.SOUTH);
    }

    // возвращает true, если текстовое поле, переданное в качестве параметра, пустое
    private boolean isEmpty(JTextField field) {
        return field.getText().equals(EMPTY_STRING);
    }

    // TODO сделать что-то с методом static String convertToMultiline(String orig), который находится уже в трех классах

    // EVENT HANDLERS

    // класс-слушатель ввода текста в текстовые поля
    private class TextTypedHandler extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            // получаем ссылку на измененное текстовое поле и вычисляем его индекс в массиве
            JTextField field = (JTextField)e.getComponent();
            int index = getIndexOfTextField(field);

            // в зависимости от того, пустое поле или нет, включаем или выключаем соответствующие списки
            boolean fieldIsEmpty = isEmpty(preparationsFields[index]);
            formLists[index].setEnabled(!fieldIsEmpty);
            peridiocityLists[index].setEnabled(!fieldIsEmpty);
            methodsLists[index].setEnabled(!fieldIsEmpty);

            // если поле пусто, то сбрасываем значения соответствующих ему списков
            if (fieldIsEmpty) {
                formLists[index].setSelectedIndex(-1);
                peridiocityLists[index].setSelectedIndex(-1);
                methodsLists[index].setSelectedIndex(-1);
            }
        }

        private int getIndexOfTextField(JTextField field) {
            for (byte i = 0; i < preparationsFields.length; i++) {
                if (preparationsFields[i] == field) {
                    return i;
                }
            }

            throw new IllegalArgumentException();
        }
    }

    // класс-обработчик нажатия кнопки OK
    private class OkButtonClickHandler implements ActionListener {

        private final String br = System.getProperty("line.separator");

        @Override
        public void actionPerformed(ActionEvent e) {

            // находим последнее непустое поле (ищем с конца)
            byte indexOfTheLastNonEmptyField = 0;
            for (byte i = (byte)(preparationsFields.length - 1); i >= 0; i--) {
                if (!isEmpty(preparationsFields[i])) {
                    indexOfTheLastNonEmptyField = i;
                    break;
                }
            }

            // получаем ссылку на область для ввода препаратов
            JTextArea area = getMainWindow().getExaminationPanel().getTherapyPreparationsArea();

            // инициализируем переменную для текста
            text = EMPTY_STRING;

            // флаг, сигнализирующий о том, что пользователь допустил ошибку при вводе данных
            boolean errorFlag = false;

            // цикл по всем непустым (блок  if) текстовым полям
            for (byte i = 0; i < preparationsFields.length; i++) {

                JTextField currentField = preparationsFields[i];

                if (!isEmpty(currentField)) {
                    // ссылки для текстов в i-ых списках
                    String currentForm;
                    String currentPeridiocity;
                    String currentMethod;

                    try {
                        // заносим в них тексты в списках
                        currentForm = formLists[i].getSelectedItem().toString();
                        currentPeridiocity = peridiocityLists[i].getSelectedItem().toString();
                        currentMethod = methodsLists[i].getSelectedItem().toString();

                        // формируем из них текст
                        text += currentForm + " " + currentField.getText() + " " + currentPeridiocity + " " +  currentMethod;

                        // если текущее текстовое поле - непоследнее и непустое, то к тексту добавляем перенос строки
                        if (i != indexOfTheLastNonEmptyField) {
                            text += br; // "\n"
                        }

                    } catch (NullPointerException ex) {

                        // TODO сделать опцией
                        JOptionPane.showMessageDialog(getMainWindow().getExaminationPanel().getTherapyPreparationsDialog(),
                                "В одном или нескольких списках не выбран ниодин из вариантов !",
                                "Ошибка!",
                                JOptionPane.ERROR_MESSAGE);

                        errorFlag = true;
                        break;
                    }
                }
            }

            // если не было ошибки, записываем текст в область для препаратов и закрываем диалог
            if (!errorFlag) {
                area.setText(text);
                dispose();
            }
        }
    }
}