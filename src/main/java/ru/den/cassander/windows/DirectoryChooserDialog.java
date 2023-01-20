package ru.den.cassander.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

import ru.den.cassander.controllers.DirectoryChooserDialogController;
import ru.den.cassander.handlers.ButtonPressureHandler;
import ru.den.cassander.handlers.RadioButtonPressureHandler;
import ru.den.cassander.settings.*;

import static java.awt.GridBagConstraints.*;
import static ru.den.cassander.Main.getMainWindow;

/**
 * Created on 06.09.2015.
 * Updated on 05.03.2018.
 *
 * Диалоговое окно "Выбор папки"
 *
 * @author Denis Vereshchagin
 */
public class DirectoryChooserDialog extends AbstractDialog {

    // папка с *.txt файлами настроек
    //private static final String COMMON_FOLDER = "D:\\Патронаж\\";
    //private static File commonFolder = new File(COMMON_FOLDER);

    // пиздееееееееееец !
    private ActionListener buttonHandler = new ButtonPressureHandler();
    private ActionListener radioHandler = new RadioButtonPressureHandler();

    private DirectoryChooserDialogController controller = new DirectoryChooserDialogController();

    private JPanel choosePanel;
    private JLabel chooseDirectoryLabel;
    private JTextField chooseDirectoryField;

    private Settings settings;

    public JTextField getChooseDirectoryField() {
        return chooseDirectoryField;
    }

    public JButton getChooseButton() {
        return chooseButton;
    }

    private JButton saveButton;
    private JButton chooseButton;

    private JPanel radioPanel;
    private JRadioButton chooseDefaultDirectoryRadioButton;
    private JRadioButton chooseCustomDirectoryRadioButton;

    public DirectoryChooserDialog() {
        super(getMainWindow(), "Выбор папки", 450, 250, new GridBagLayout());

        // создаем элементы графического интерфейса
        createLabel();
        createTextField();
        createChooseButton();
        createChoosePanel();
        createSaveButton();
        createRadioButtons();

        // применяем настройки
        applySettings();

        setVisible(true);
    }

    public JRadioButton getDefaultRadioButton() {
        return chooseDefaultDirectoryRadioButton;
    }

    private void createChoosePanel() {
        choosePanel = new JPanel();
        choosePanel.setLayout(new GridBagLayout());
        choosePanel.setBackground(Color.WHITE);

        choosePanel.add(chooseDirectoryLabel, new GridBagConstraints(0, 0, 1, 1, 0.9, 0.9, CENTER, NONE, new Insets(1, 1, 1, 1), 0, 0));
        choosePanel.add(chooseDirectoryField, new GridBagConstraints(0, 1, 1, 1, 0.9, 0.9, CENTER, NONE, new Insets(1, 1, 1, 1), 0, 0));
        choosePanel.add(chooseButton,         new GridBagConstraints(1, 1, 1, 1, 0.9, 0.9, CENTER, NONE, new Insets(1, 1, 1, 1), 0, 0));

        panel.add(choosePanel, new GridBagConstraints(0, 0, 2, 2, 0.9, 0.9, CENTER, NONE, new Insets(20, 1, 1, 1), 0, 0));
    }

    private void createLabel() {
        chooseDirectoryLabel = new JLabel("Выберите папку для хранения документов:");
        chooseDirectoryLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
    }

    private void createTextField() {
        chooseDirectoryField = new JTextField(25);
    }

    private void createChooseButton() {
        chooseButton = new JButton("...");
        chooseButton.addActionListener(buttonHandler);
    }

    // создает кнопку "Сохранить"
    private void createSaveButton() {
        saveButton = new JButton("Сохранить");
        //saveButton.addActionListener(e -> controller.saveButtonClicked());
        saveButton.addActionListener(buttonHandler);

        panel.add(saveButton, new GridBagConstraints(0, 6, 1, 1, 0.9, 0.9, SOUTHEAST, NONE, new Insets(0, 3, 10, 10), 0, 0));
    }

    private void createRadioButtons() {
        radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        chooseDefaultDirectoryRadioButton = new JRadioButton("Использовать папку по умолчанию");
        chooseDefaultDirectoryRadioButton.setBackground(Color.WHITE);
        chooseDefaultDirectoryRadioButton.addActionListener(radioHandler);
        radioPanel.add(chooseDefaultDirectoryRadioButton, new GridBagConstraints(0, 0, 1, 1, 0.9, 0.9, WEST, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

        chooseCustomDirectoryRadioButton  = new JRadioButton("Выбрать другую папку");
        chooseCustomDirectoryRadioButton.setBackground(Color.WHITE);
        chooseCustomDirectoryRadioButton.addActionListener(radioHandler);
        radioPanel.add(chooseCustomDirectoryRadioButton, new GridBagConstraints(0, 1, 1, 1, 0.9, 0.9, WEST, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

        // Добавляем радиокнопки в радиогруппу
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(chooseDefaultDirectoryRadioButton);
        buttonGroup.add(chooseCustomDirectoryRadioButton);

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

    //
    private void useDefaultSettings() {
        chooseButton.setEnabled(false); // блокируем кнопку "выбрать"
        chooseDirectoryField.setEditable(false); // блокируем поле со ссылкой на папку
        chooseDirectoryField.setText(settings.getDefaultDirectoryPath()); // записываем в поле путь к папке по умолчанию
        chooseDefaultDirectoryRadioButton.setSelected(true); // выбираем радиокнопку "Использовать папку по умолчанию"
    }

    private void useCustomSettings() {
        chooseButton.setEnabled(true);
        chooseDirectoryField.setEditable(true);
        chooseDirectoryField.setText(settings.getCurrentDirectoryPath());
        chooseCustomDirectoryRadioButton.setSelected(true);
    }

    /*@SuppressWarnings("ResultOfMethodCallIgnored")
    private static void createCommonFolderIfNeeded() {
        if (!commonFolder.exists()) {
            commonFolder.mkdirs();
        }
    }*/
}