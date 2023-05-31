package ru.den.cassander.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.Constants.EMPTY_STRING;
import static ru.den.cassander.gui.main.ExaminationOfTheAdultPanelConstants.*;

/**
 * Created on 05.12.2015.
 */
public class UrgentCarePreparationsDialog extends AbstractDialog {

    // константы, с помощью которых задается ориентация объекта Box
    private enum Orientation {
        HORIZONTAL, VERTICAL
    }

    // количество списков в каждом столбце (объекте Box)
    private static final byte SIZE = 5;

    public UrgentCarePreparationsDialog() {

        // инициализация диалога
        super(getMainWindow(), "Препараты - неотложная помощь", 530, 230, new BorderLayout());
        setModalityType(ModalityType.APPLICATION_MODAL);

        // создание основных компонентов
        createFormBox();
        createPreparationsBox();
        createDosesBox();
        createMethodsBox();
        createOkPanel();

        addComponents();
    }

    private JComboBox[] formLists = new JComboBox[SIZE];
    private JComboBox[] preparationsLists = new JComboBox[SIZE];
    private JComboBox[] dosesLists = new JComboBox[SIZE];
    private JComboBox[] methodsLists = new JComboBox[SIZE];

    private Box formBox;
    private Box preparationsBox;
    private Box dosesBox;
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
    private void createPreparationsBox() {
        preparationsBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Препарат");
        label.setPreferredSize(new Dimension(50, 25));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        preparationsBox.add(label);

        for (byte i = 0; i < preparationsLists.length; i++) {
            preparationsLists[i] = createComboBox(URGENT_CARE_PREPARATIONS_LIST_ITEMS);
            preparationsLists[i].addItemListener(new ItemChosenHandler());
            preparationsBox.add(preparationsLists[i]);
        }
    }

    // создает списки "Дозировка"
    private void createDosesBox() {
        dosesBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Дозировка, мл");
        label.setPreferredSize(new Dimension(50, 25));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        dosesBox.add(label);

        for (byte i = 0; i < dosesLists.length; i++) {
            dosesLists[i] = createComboBox(DOSES_LIST_ITEMS);
            dosesLists[i].setEnabled(false);
            dosesBox.add(dosesLists[i]);
        }
    }

    // создает списки "Способ применения"
    private void createMethodsBox() {
        methodsBox = createBox(Orientation.VERTICAL);

        JLabel label = new JLabel("Способ применения");
        label.setPreferredSize(new Dimension(50, 25));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        methodsBox.add(label);
        //methodsBox.add(new JLabel(convertToMultiline("Периодичность\nприменения")));

        for (byte i = 0; i < methodsLists.length; i++) {
            methodsLists[i] = createComboBox(URGENT_CARE_METHODS_LIST_ITEMS);
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
        panel.add(createBox(Orientation.HORIZONTAL, new Component[]{formBox, preparationsBox, dosesBox, methodsBox}));
        panel.add(okPanel, BorderLayout.SOUTH);
    }

    // возвращает true, если список, переданный в качестве параметра, пуст
    private boolean isEmpty(JComboBox list) {
        return list.getSelectedIndex() == -1;
    }

    // EVENT HANDLERS

    // класс-слушатель выбора элемента в списках "Препарат"
    private class ItemChosenHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            // получаем ссылку на измененное текстовое поле и вычисляем его индекс в массиве
            JComboBox list = (JComboBox)e.getSource();
            int index = getIndexOfComboBox(list);

            // в зависимости от того, пустое поле или нет, включаем или выключаем соответствующие списки
            boolean fieldIsEmpty = isEmpty(preparationsLists[index]);
            formLists[index].setEnabled(!fieldIsEmpty);
            dosesLists[index].setEnabled(!fieldIsEmpty);
            methodsLists[index].setEnabled(!fieldIsEmpty);

            // если поле пусто, то сбрасываем значения соответствующих ему списков
            if (fieldIsEmpty) {
                formLists[index].setSelectedIndex(-1);
                dosesLists[index].setSelectedIndex(-1);
                methodsLists[index].setSelectedIndex(-1);
            }
        }

        private int getIndexOfComboBox(JComboBox list) {
            for (byte i = 0; i < preparationsLists.length; i++) {
                if (preparationsLists[i] == list) {
                    return i;
                }
            }

            throw new IllegalArgumentException();
        }
    }

    // класс-обработчик нажатия кнопки OK
    private class OkButtonClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // находим последнее непустое поле (ищем с конца)
            byte indexOfTheLastNonEmptyField = 0;
            for (byte i = (byte)(preparationsLists.length - 1); i >= 0; i--) {
                if (!isEmpty(preparationsLists[i])) {
                    indexOfTheLastNonEmptyField = i;
                    break;
                }
            }

            // получаем ссылку на область для ввода препаратов
            JTextArea area = getMainWindow().getExaminationPanel().getUrgentCarePreparationsArea();

            // инициализируем переменную для текста
            text = EMPTY_STRING;

            // флаг, сигнализирующий о том, что пользователь допустил ошибку при вводе данных
            boolean errorFlag = false;

            // цикл по всем непустым (блок  if) спискам в столбце "Препарат"
            for (byte i = 0; i < preparationsLists.length; i++) {

                JComboBox currentList = preparationsLists[i];

                if (!isEmpty(currentList)) {

                    // ссылки для текстов в i-ых списках
                    String currentPreparation = currentList.getSelectedItem().toString();
                    String currentForm;
                    String currentDose;
                    String currentMethod;

                    try {
                        // заносим в них тексты в списках
                        currentForm = formLists[i].getSelectedItem().toString();
                        currentDose = dosesLists[i].getSelectedItem().toString();
                        currentMethod = methodsLists[i].getSelectedItem().toString();

                        // формируем из них текст
                        // TODO заюзать StringBuilder
                        text += currentForm + " " + currentPreparation + " " + currentDose + " мл. " + currentMethod;

                        // если текущее текстовое поле - непоследнее и непустое, то к тексту добавляем перенос строки
                        if (i != indexOfTheLastNonEmptyField) {
                            text += "\n";
                        }

                    } catch (NullPointerException ex) {

                        //TODO опция
                        JOptionPane.showMessageDialog(getMainWindow().getExaminationPanel().getUrgentCarePreparationsDialog(),
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