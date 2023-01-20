package ru.den.cassander.controllers;

import ru.den.cassander.document_creators.DocumentCreator;
import ru.den.cassander.settings.Settings;
import ru.den.cassander.settings.SettingsRW;
import ru.den.cassander.settings.XMLSettingsRW;
import ru.den.cassander.windows.*;

import javax.swing.*;

import static ru.den.cassander.Constants.CASSANDER;
import static ru.den.cassander.Main.getMainWindow;

/**
 * Created on 02.05.2018
 * Updated on 02.05.2018
 * <p>
 * Класс-контроллер событий главного окна приложения. Цель данного класса - разделить ГУЙ и обработку событий.
 * Сюда вынести все обработчики нажатий кнопок, меню и т.д.
 *
 * @author Denis Vereshchagin
 * @since 2.1
 */
public class MainWindowController {
    // TODO перенести сюда все обработчики нажатия меню и прочих элементов MainWindow, если они есть
    // TODO оставить в MainWindow только отрисовку GUI

    public DirectoryChooserDialog getDirectoryChooser() {
        return directoryChooser;
    }

    private DirectoryChooserDialog directoryChooser; // TODO подумать, где на самом деле должно находиться это поле (и должно ли оно быть полем)

    private static short patronageCallCounter = 0;
    private static short examinationCallCounter = 0;

    private SettingsRW settingsRW;
    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    // Конструктор класса
    public MainWindowController() {
        settingsRW = new XMLSettingsRW();
        settings = settingsRW.readSettings();

        // применение настроек к пункту меню "Проверка наличия незаполненных полей"
        //getMainWindow().getEmptyFieldsCheckItem().setState(settings.isEmptyFieldsCheckEnabled());
    }

    // Обработчик нажатия пункта меню "Новый патронаж"
    public void newPatronageMenuPressed() {
        System.out.println("Сработал обработчик нажатия пункта меню \"Новый патронаж\"");
        examinationCallCounter = 0;

        if (++patronageCallCounter == 1) { // если вызывает первый раз ????
            getMainWindow().createPatronagePanel();
            getMainWindow().setDefaultDimension();
            MainWindow.enableCloseMenuItem();
            getMainWindow().enableCreateDocumentMenuItem();
        } else {
            getMainWindow().getPatronagePanel().resetComponent(getMainWindow().getPatronagePanel());
        }
    }

    // Обработчик нажатия пункта меню "Новый осмотр взрослого"
    public void newExaminationMenuPressed() {
        patronageCallCounter = 0;

        if (++examinationCallCounter == 1) {
            getMainWindow().createExaminationPanel();
            //getMainWindow().pack();
            MainWindow.enableCloseMenuItem();
            getMainWindow().enableCreateDocumentMenuItem();
        } else {
            getMainWindow().getExaminationPanel().resetComponent(getMainWindow().getExaminationPanel());
            getMainWindow().getExaminationPanel().setTherapyPreparationsDialog(new TherapyPreparationsDialog());
            getMainWindow().getExaminationPanel().setUrgentCarePreparationsDialog(new UrgentCarePreparationsDialog());
        }
    }

    private DocumentCreator creator;

    // Обработчик нажатия пункта меню "Создать документ"
    public void createDocumentMenuPressed() {
        System.out.println("Убери кнопки \"Создать документ\" и запили такой же пункт меню!");

        // TODO попробовать применить знания по интерфейсам здесь. Вызывать отсюда метод DocumentCreator#createDocument().
        // в ссылку creator записывать такого наследника интерфейса DocumentCreator, который соответствует текущему заполняемому бланку
        // т.е. в этом классе добавить метод setCreator(DocumentCreator) и вызывать его внутри методов createPatronagePanel() {...setCreator(new PatronageCreator())...}
        // и createExaminationPanel() {...setCreator(new ExaminationCreator())...}

        //creator.createDocument();
    }

    // Обработчик нажатия пункта меню "Закрыть"
    public void closeMenuItemPressed() {
        getMainWindow().setContentPane(getMainWindow().getPanel());
        getMainWindow().validate();
        //getMainWindow().repaint();

        MainWindow.disableCloseMenuItem();
        getMainWindow().disableCreateDocumentMenuItem();
        getMainWindow().setTitle(CASSANDER);
        getMainWindow().setDefaultDimension();
        clearCounters();
    }

    private void clearCounters() {
        patronageCallCounter = 0;
        examinationCallCounter = 0;
    }

    // Обработчик нажатия пункта меню "Выход"
    /*public void exitMenuItemPressed() {
        System.exit(0);
    } // см. MainWindow#setOnClose()*/

    // Обработчик нажатия пункта меню "Выбор папки для хранения документов"
    public void chooseDirectoryMenuItemPressed() {
        directoryChooser = new DirectoryChooserDialog();
    }

    // Обработчик установки/сброса пункта меню "Проверка наличия незаполненных полей"
    public void emptyFieldsCheckMenuItemChanged() {
        settings.setEmptyFieldsCheckStatus(getMainWindow().getEmptyFieldsCheckItem().isSelected());
    }

    // Обработчик нажатия пункта меню "О программе"
    public void aboutMenuItemPressed() {
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.setVisible(true);
    }

    // Обработчик закрытия приложения (вызывается, когда нажата кнопка Выход или закрывается окно приложения)
    // в нем выполняются действия, которые д.б. выполнены перед закрытием приложения
    public void appClosing() {
        int result = JOptionPane.showConfirmDialog(getMainWindow(), "Вы уверены, что хотите выйти из приложения ?",
                "Подтверждение выхода", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            settingsRW.writeSettings(); // сохраняем настройки TODO если они были изменены
            getMainWindow().dispose();
        }

        System.out.println("Сработал метод appClosing() в классе MainWindowController");
    }
}