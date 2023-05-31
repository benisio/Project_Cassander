package ru.den.cassander.controllers;

import javax.swing.*;
import java.io.File;

import ru.den.cassander.settings.Settings;
import ru.den.cassander.settings.XMLSettingsRW;
import ru.den.cassander.gui.DirectoryChooserDialog;

import static ru.den.cassander.settings.Settings.CurrentDirectoryType.*;

/**
 * Created on January 2023
 * Класс-обработчик событий диалогового окна "Выбор папки для хранения документов"
 */

public class DirectoryChooserDialogController {

    private DirectoryChooserDialog directoryChooserDialog; // ссылка на соответствующий GUI-класс
    private Settings settings;

    // конструктор
    public DirectoryChooserDialogController() {
        // directoryChooserDialog = getMainWindow().getController().getDirectoryChooser(); убрал данный говнокод
        // добавлением сеттера для directoryChooserDialog и вызовом этого сеттера в конструкторе DirectoryChooserDialog:
        // controller.setDirectoryChooserDialog(this);

        settings = XMLSettingsRW.getSettings();
    }

    // добавил сеттер для directoryChooserDialog для того, чтобы дать объекту DirectoryChooserDialogController ссылку
    // на объект DirectoryChooserDialog (GUI-класса)
    public void setDirectoryChooserDialog(DirectoryChooserDialog dialog) {
        this.directoryChooserDialog = dialog;
    }

    // обработчик нажатия кнопки "..." (выбор папки)
    public void chooseDirectoryButtonClicked() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // выбираем только папки
        fileChooser.setAcceptAllFileFilterUsed(false); // фильтр отображаемых файлов (фильтр "All files" выключен)
        fileChooser.setCurrentDirectory(new File(directoryChooserDialog.getTextFromTextField()));

        // это же отрисовка GUI? должен ли этот кусок кода находиться в контроллере ?
        // или его стоит перенести в GUI-класс ?
        JButton chooseButton = directoryChooserDialog.getChooseButton(); // все еще обращаюсь к элементам GUI напрямую из этого класса
        int result = fileChooser.showDialog(chooseButton, "Выбрать папку");

        if (result == JFileChooser.APPROVE_OPTION) {
            String chosenDirectoryPath = fileChooser.getSelectedFile().toString();

            // добавляем "слэш", если нужен
            if (!chosenDirectoryPath.endsWith("\\")) {
                chosenDirectoryPath += "\\";
            }

            directoryChooserDialog.setTextToTextField(chosenDirectoryPath);
        }
    }

    // а вот интересно, что будет, если в текстовое поле ввести какой-нибудь бред типа "блаблабла" и нажать "Сохранить" ?
    // Upd попробовал сделать - после нажатия кнопки "Сохранить" окно закрылось как обычно, после выхода из приложения
    // текст "блаблабла" записался в файл настроек. TODO ошибка - исправить !!!

    // обработчик нажатия кнопки "Сохранить"
    public void saveButtonClicked() {
        // применяем настройки
        settings.setCurrentDirectoryPath(directoryChooserDialog.getTextFromTextField());

        // запись типа используемой папки - по умолчанию или пользовательская
        JRadioButton defaultRadioButton = directoryChooserDialog.getDefaultRadioButton(); // все еще обращаюсь к элементам GUI напрямую из этого класса
        settings.setCurrentDirectoryType(defaultRadioButton.isSelected() ? DEFAULT : CUSTOM);

        directoryChooserDialog.dispose();
    }

    // обработчик нажатия радиокнопки с текстом "Использовать папку по умолчанию"
    public void chooseDefaultDirectoryRBClicked() {
        directoryChooserDialog.setTextFieldNotEditable();
        directoryChooserDialog.setTextToTextField(settings.getDefaultDirectoryPath());
        directoryChooserDialog.disableChooseButton();
    }

    // обработчик нажатия радиокнопки с текстом "Выбрать другую папку"
    public void chooseCustomDirectoryRBClicked() {
        directoryChooserDialog.setTextFieldEditable();
        directoryChooserDialog.setTextToTextField("C:\\");
        directoryChooserDialog.enableChooseButton();
    }

}