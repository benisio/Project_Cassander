package ru.den.cassander.controllers;

import javax.swing.*;
import java.io.File;

import ru.den.cassander.settings.Settings;
import ru.den.cassander.settings.XMLSettingsRW;
import ru.den.cassander.windows.DirectoryChooserDialog;

import static ru.den.cassander.Main.getMainWindow;

/**
 * Класс-обработчик событий диалогового окна "Выбор папки для хранения документов"
 */

public class DirectoryChooserDialogController {

    private DirectoryChooserDialog directoryChooserDialog;
    private JTextField chooseDirectoryField;
    private Settings settings;

    public DirectoryChooserDialogController() {
        directoryChooserDialog = getMainWindow().getController().getDirectoryChooser();
        settings = XMLSettingsRW.getSettings();
    }


    // обработчик нажатия кнопки "..." (выбор папки)
    public void chooseDirectoryButtonClicked() {
        directoryChooserDialog = getMainWindow().getController().getDirectoryChooser();
        chooseDirectoryField = directoryChooserDialog.getChooseDirectoryField();
        JButton chooseButton = directoryChooserDialog.getChooseButton();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // выбираем только папки
        fileChooser.setAcceptAllFileFilterUsed(false); // а это что ?
        fileChooser.setCurrentDirectory(new File(chooseDirectoryField.getText()));
        int result = fileChooser.showDialog(chooseButton, "Выбрать папку");

        if (result == JFileChooser.APPROVE_OPTION) {
            String chosenDirectoryPath = fileChooser.getSelectedFile().toString();

            // добавляем "слэш", если нужен
            if (!chosenDirectoryPath.endsWith("\\")) {
                chosenDirectoryPath += "\\";
            }

            chooseDirectoryField.setText(chosenDirectoryPath);
        }
    }

    // обработчик нажатия кнопки "Сохранить"
    public void saveButtonClicked() {
        directoryChooserDialog = getMainWindow().getController().getDirectoryChooser();
        chooseDirectoryField = directoryChooserDialog.getChooseDirectoryField();
        JRadioButton defaultRadioButton = directoryChooserDialog.getDefaultRadioButton();

        // применяем настройки
        settings.setCurrentDirectoryPath(chooseDirectoryField.getText());

        // запись типа используемой папки - по умолчанию или пользовательская
        settings.setCurrentDirectoryType(defaultRadioButton.isSelected() ?
                Settings.CurrentDirectoryType.DEFAULT : Settings.CurrentDirectoryType.CUSTOM);

        directoryChooserDialog.dispose();
    }

    public void radioButtonClicked() {

    }

}