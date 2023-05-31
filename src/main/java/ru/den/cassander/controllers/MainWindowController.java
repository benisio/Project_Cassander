package ru.den.cassander.controllers;

import ru.den.cassander.settings.*;
import ru.den.cassander.gui.*;

import javax.swing.*;

import static ru.den.cassander.Constants.CASSANDER;
import static ru.den.cassander.Main.getMainWindow;

/**
 * Created on 02.05.2018
 * Updated on January 2023
 * <p>
 * Класс-контроллер событий главного окна приложения. Цель данного класса - разделить ГУЙ и обработку событий.
 * Сюда вынести все обработчики нажатий кнопок, меню и т.д.
 */
public class MainWindowController {

    private DirectoryChooserDialog directoryChooser; // TODO подумать, где на самом деле должно находиться это поле (и должно ли оно быть полем)

    private static int patronageCallCounter = 0;
    private static int examinationCallCounter = 0;

    private SettingsRW settingsRW;
    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    // Конструктор
    public MainWindowController() {
        settingsRW = new XMLSettingsRW();
        settings = settingsRW.readSettings();

        // применение настроек к пункту меню "Проверка наличия незаполненных полей"
        //getMainWindow().getEmptyFieldsCheckItem().setState(settings.isEmptyFieldsCheckEnabled());
    }

    // Обработчик нажатия пункта меню "Новый патронаж"
    public void newPatronageMenuClicked() {
        examinationCallCounter = 0;

        if (++patronageCallCounter == 1) { // если вызывает первый раз ????
            getMainWindow().createPatronagePanel();
            getMainWindow().setDefaultDimension();
            MainWindow.enableCloseMenuItem();
            getMainWindow().enableCreateDocumentMenu();
        } else {
            getMainWindow().getPatronagePanel().resetComponent(getMainWindow().getPatronagePanel());
        }
    }

    // Обработчик нажатия пункта меню "Новый осмотр взрослого"
    public void newExaminationMenuClicked() {
        patronageCallCounter = 0;

        if (++examinationCallCounter == 1) {
            getMainWindow().createExaminationPanel(); // на выполнение этого метода тратится около 1,8 с
            MainWindow.enableCloseMenuItem();
            getMainWindow().enableCreateDocumentMenu();
        } else {
            getMainWindow().getExaminationPanel().resetComponent(getMainWindow().getExaminationPanel());
            getMainWindow().getExaminationPanel().setTherapyPreparationsDialog(new TherapyPreparationsDialog());
            getMainWindow().getExaminationPanel().setUrgentCarePreparationsDialog(new UrgentCarePreparationsDialog());
        }
    }

    // Обработчик нажатия пункта меню "Создать документ"
    public void createDocumentMenuClicked() {
        System.out.println("Убери кнопки \"Создать документ\" и запили такой же пункт меню!");

        // TODO попробовать применить знания по интерфейсам здесь. Вызывать отсюда метод DocumentCreator#createDocument().
        // в ссылку creator записывать такого наследника интерфейса DocumentCreator, который соответствует текущему заполняемому бланку
        // т.е. в этом классе добавить метод setCreator(DocumentCreator) и вызывать его внутри методов createPatronagePanel() {...setCreator(new PatronageCreator())...}
        // и createExaminationPanel() {...setCreator(new ExaminationCreator())...}

        //creator.createDocument();
    }

    // Обработчик нажатия пункта меню "Закрыть"
    public void closeMenuClicked() {
        getMainWindow().setContentPane(getMainWindow().getPanel());
        getMainWindow().validate();

        MainWindow.disableCloseMenuItem();
        getMainWindow().disableCreateDocumentMenu();
        getMainWindow().setTitle(CASSANDER);
        getMainWindow().setDefaultDimension();
        clearCounters();
    }

    // обнуляет счетчики нажатия пунктов меню "Новый патронаж" и "Новый осмотр взрослого"
    private void clearCounters() {
        patronageCallCounter = 0;
        examinationCallCounter = 0;
    }

    // Обработчик нажатия пункта меню "Выбор папки для хранения документов"
    public void chooseDirectoryMenuClicked() {
        directoryChooser = new DirectoryChooserDialog();
    }

    // Обработчик установки/сброса чекбокса в меню "Проверка наличия незаполненных полей"
    public void emptyFieldsCheckMenuChanged() {
        settings.setEmptyFieldsCheckStatus(getMainWindow().getEmptyFieldsCheckItem().isSelected());
    }

    // Обработчик нажатия пункта меню "О программе"
    public void aboutMenuClicked() {
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.setVisible(true);
    }

    // Обработчик закрытия приложения (вызывается, когда нажата кнопка Выход или закрывается окно приложения)
    // в нем выполняются действия, которые д.б. выполнены перед закрытием приложения
    /*сохраняю изменения в настройках тут, то есть перед закрытием приложения. Когда лучше ? здесь или при нажатии кнопки
    * "Сохранить" в диалоговом окне "Выбор папки для хранения файлов" ?*/
    public void appClosing() {
        int result = JOptionPane.showConfirmDialog(getMainWindow(), "Вы уверены, что хотите выйти из приложения ?",
                "Подтверждение выхода", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            settingsRW.writeSettings(); // сохраняем настройки TODO если они были изменены
            getMainWindow().dispose();
        }
    }
}