package ru.den.cassander.gui;

import javax.swing.*;
import java.awt.*;

import ru.den.cassander.controllers.DirectoryChooserDialogController;
import ru.den.cassander.settings.*;

import static java.awt.GridBagConstraints.*;
import static ru.den.cassander.Main.getMainWindow;

/**
 * Created on 06.09.2015.
 * Updated on January 2023.
 *
 * Диалоговое окно "Выбор папки"
 */
public class DirectoryChooserDialog extends AbstractDialog {

    private DirectoryChooserDialogController controller;

    private JPanel choosePanel;
    private JLabel chooseDirectoryLabel;
    private JTextField chooseDirectoryField;

    private Settings settings;

    public JButton getChooseButton() {
        return chooseDirectoryButton;
    }

    private JButton saveButton;
    private JButton chooseDirectoryButton;

    private JPanel radioPanel;
    private JRadioButton chooseDefaultDirectoryRB;
    private JRadioButton chooseCustomDirectoryRB;

    // конструктор
    public DirectoryChooserDialog() {
        super(getMainWindow(), "Выбор папки", 450, 250, new GridBagLayout());

        controller = new DirectoryChooserDialogController();

        // даем контроллеру ссылку на это диалоговое окно с помощью сеттера
        // эта строка нужна для избавления от говнокода в контроллере (а до контроллера - в классе-хэндлере событий кнопок)
        controller.setDirectoryChooserDialog(this);

        // создаем элементы графического интерфейса
        createLabel();
        createTextField();
        createChooseDirectoryButton();
        createChoosePanel();
        createSaveButton();
        createRadioButtons();

        applySettings(); // применяем настройки

        setVisible(true);
    }

    public JRadioButton getDefaultRadioButton() {
        return chooseDefaultDirectoryRB;
    }

    // включает кнопку "..." (выбор папки)
    public void enableChooseButton() {
        chooseDirectoryButton.setEnabled(true);
    }

    // блокирует кнопку "..." (выбор папки)
    public void disableChooseButton() {
        chooseDirectoryButton.setEnabled(false);
    }

    // делает текстовое поле доступным для редактирования текста в нем
    public void setTextFieldEditable() {
        chooseDirectoryField.setEditable(true);
    }

    // делает текстовое поле недоступным для редактирования текста в нем
    public void setTextFieldNotEditable() {
        chooseDirectoryField.setEditable(false);
    }

    // возвращает текст из текстового поля
    public String getTextFromTextField() {
        return chooseDirectoryField.getText();
    }

    // устанавливает text в текстовое поле
    public void setTextToTextField(String text) {
        chooseDirectoryField.setText(text);
    }

    private void createChoosePanel() {
        choosePanel = new JPanel();
        choosePanel.setLayout(new GridBagLayout());
        choosePanel.setBackground(Color.WHITE);

        choosePanel.add(chooseDirectoryLabel, new GridBagConstraints(0, 0, 1, 1, 0.9, 0.9, CENTER, NONE, new Insets(1, 1, 1, 1), 0, 0));
        choosePanel.add(chooseDirectoryField, new GridBagConstraints(0, 1, 1, 1, 0.9, 0.9, CENTER, NONE, new Insets(1, 1, 1, 1), 0, 0));
        choosePanel.add(chooseDirectoryButton,         new GridBagConstraints(1, 1, 1, 1, 0.9, 0.9, CENTER, NONE, new Insets(1, 1, 1, 1), 0, 0));

        panel.add(choosePanel, new GridBagConstraints(0, 0, 2, 2, 0.9, 0.9, CENTER, NONE, new Insets(20, 1, 1, 1), 0, 0));
    }

    // создает метку "Выберите папку для хранения документов:"
    private void createLabel() {
        chooseDirectoryLabel = new JLabel("Выберите папку для хранения документов:");
        chooseDirectoryLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
    }

    // создает текстовое поле
    private void createTextField() {
        chooseDirectoryField = new JTextField(25);
    }

    // создает кнопку "..." (выбор папки)
    private void createChooseDirectoryButton() {
        chooseDirectoryButton = new JButton("...");
        chooseDirectoryButton.addActionListener(e -> controller.chooseDirectoryButtonClicked());
    }

    // создает кнопку "Сохранить"
    private void createSaveButton() {
        saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> controller.saveButtonClicked());

        panel.add(saveButton, new GridBagConstraints(0, 6, 1, 1, 0.9, 0.9, SOUTHEAST, NONE, new Insets(0, 3, 10, 10), 0, 0));
    }

    // создает радиогруппу из двух радиокнопок
    private void createRadioButtons() {
        radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        chooseDefaultDirectoryRB = new JRadioButton("Использовать папку по умолчанию");
        chooseDefaultDirectoryRB.setBackground(Color.WHITE);
        chooseDefaultDirectoryRB.addActionListener(e -> controller.chooseDefaultDirectoryRBClicked());
        radioPanel.add(chooseDefaultDirectoryRB, new GridBagConstraints(0, 0, 1, 1, 0.9, 0.9, WEST, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

        chooseCustomDirectoryRB = new JRadioButton("Выбрать другую папку");
        chooseCustomDirectoryRB.setBackground(Color.WHITE);
        chooseCustomDirectoryRB.addActionListener(e -> controller.chooseCustomDirectoryRBClicked());
        radioPanel.add(chooseCustomDirectoryRB, new GridBagConstraints(0, 1, 1, 1, 0.9, 0.9, WEST, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

        // Добавляем радиокнопки в радиогруппу
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(chooseDefaultDirectoryRB);
        buttonGroup.add(chooseCustomDirectoryRB);

        panel.add(radioPanel, new GridBagConstraints(0, 5, 1, 1, 0.9, 0.9, WEST, NONE,
                new Insets(20, 40, 5, 5), 2, 2));
    }

    // применяет свойства к элементам интерфейса данного диалогового окна в зависимости от установленного в файле
    // настроек типа папки для хранения документов (по умолчанию или пользовательская)
    private void applySettings() {
        settings = XMLSettingsRW.getSettings();

        if (settings.getCurrentDirectoryType().equals(XMLSettings.CurrentDirectoryType.CUSTOM)) {
            useCustomSettings(); // если пользовательская (CUSTOM)
        } else {
            useDefaultSettings(); // если папка по умолчанию (DEFAULT)
        }
    }

    // применяет настройки к элементам графического интерфейса для случая, когда в настройках выбран
    // тип используемой папки DEFAULT (папка по умолчанию)
    private void useDefaultSettings() {
        chooseDirectoryButton.setEnabled(false); // блокируем кнопку "выбрать"
        chooseDirectoryField.setEditable(false); // блокируем поле со ссылкой на папку
        chooseDirectoryField.setText(settings.getDefaultDirectoryPath()); // записываем в поле путь к папке по умолчанию
        chooseDefaultDirectoryRB.setSelected(true); // выбираем радиокнопку "Использовать папку по умолчанию"
    }

    // применяет настройки к элементам графического интерфейса для случая, когда в настройках выбран
    // тип используемой папки CUSTOM (пользовательская папка)
    private void useCustomSettings() {
        chooseDirectoryButton.setEnabled(true);
        chooseDirectoryField.setEditable(true);
        chooseDirectoryField.setText(settings.getCurrentDirectoryPath());
        chooseCustomDirectoryRB.setSelected(true);
    }

}