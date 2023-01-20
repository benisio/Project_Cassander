package ru.den.cassander.windows;

import ru.den.cassander.controllers.MainWindowController;
import ru.den.cassander.windows.main.ExaminationOfTheAdultPanel;
import ru.den.cassander.windows.main.PatronagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static ru.den.cassander.Constants.*;

/**
 * Created on 06.08.2015.
 * Updated on 02.05.2018.
 *
 * Написать здесь, что именно в графическом интерфейсе я называю MainWindow. Что описывает этот класс ?
 *
 * @author Denis Vereshchagin
 */
public class MainWindow extends AbstractWindow {
    // TODO 1-я попытка переделать этот класс как Синглтон увенчалась фиаско
    // TODO когда убираешь мышку с открытого списка пунктов меню и кликаешь вне его, меню не закрывается

    // Полоска меню
    private JMenuBar menuBar;

    // Меню "Файл" и его пункты
    private JMenu fileMenu;
    private JMenuItem newPatronageItem;
    private JMenuItem newExaminationOfTheAdultItem;
    private JMenuItem createDocumentItem; // 2.1
    private static JMenuItem closeItem; // можно ли убрать static ?
    private JMenuItem exitItem;

    // Меню "Настройки" и его пункты
    private JMenu settingsMenu;
    private JMenuItem chooseDirectoryItem;
    private JCheckBoxMenuItem emptyFieldsCheckItem; // 2.1

    public JCheckBoxMenuItem getEmptyFieldsCheckItem() {
        return emptyFieldsCheckItem;
    }

    // Меню "Справка" и его пункты
    private JMenu helpMenu;
    private JMenuItem aboutItem;

    // Панели разделов "Патронаж" и "Осмотр взрослого"
    private PatronagePanel patronagePanel;
    private ExaminationOfTheAdultPanel examinationPanel;

    // Todo отправить в xml
    // Путь к изображению "Cassander"
    private static final String ICON_PATH = "resources\\mainWindow.png";

    // Возвращает true, если установлен чек-бокс напротив пункта меню "Проверка наличия незаполненных полей",
    // и false в противном случае
    public boolean isEmptyFieldsCheckBoxSelected() {
        return emptyFieldsCheckItem.isSelected();
    }

    // Настройки // перенести в класс-контроллер ?
    /*private SettingsRW settingsRW;
    private Settings settings;

    // это костыль для доступа к этому полю из контроллера
    public SettingsRW getSettingsRW() {
        return settingsRW;
    }*/

    // контроллер // 2,1
    private MainWindowController controller;

    public MainWindowController getController() {
        return controller;
    }

    public MainWindow() {
        super(CASSANDER, 1000, 700, DO_NOTHING_ON_CLOSE);
        panel.setBackground(Color.darkGray); // в настройки ?
        panel.add(new JLabel(new ImageIcon(ICON_PATH))); //MainWindow.class.getResource(ICON_PATH) // и это в настройки ?

        controller = new MainWindowController();

        // создание меню
        createMenu();

        // cлушатель закрытия окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.appClosing();
            }
        });
/*
        // чтение настроек
        settingsRW = new XMLSettingsRW();
        settings = settingsRW.readSettings();

        // применение настроек к пункту меню "Проверка наличия незаполненных полей"
        emptyFieldsCheckItem.setState(settings.isEmptyFieldsCheckEnabled());
*/
    }

    // TODO не относится к этому проекту ! в меню File -> Open recent почистить список проектов, оставить и сгруппировать
    // проекты в работе, убрать мусор

    public PatronagePanel getPatronagePanel() {
        return patronagePanel;
    }

    public ExaminationOfTheAdultPanel getExaminationPanel() {
        return examinationPanel;
    }

    // создает панель меню
    private void createMenu() {
        menuBar = new JMenuBar();

        createFileMenu();
        createSettingsMenu();
        createHelpMenu();

        setJMenuBar(menuBar);
    }

    // создает меню "Файл"
    private void createFileMenu() {
        fileMenu = new JMenu(FILE);

        // создание пункта меню "Новый патронаж"
        newPatronageItem = new JMenuItem(NEW_PATRONAGE);
        newPatronageItem.addActionListener(e -> controller.newPatronageMenuPressed());
        fileMenu.add(newPatronageItem);

        // создание пункта меню "Новый осмотр взрослого"
        newExaminationOfTheAdultItem = new JMenuItem(NEW_EXAMINATION_OF_THE_ADULT);
        newExaminationOfTheAdultItem.addActionListener(e -> controller.newExaminationMenuPressed());
        fileMenu.add(newExaminationOfTheAdultItem);

        fileMenu.addSeparator();

        // создание пункта меню "Создать документ" // 2.1
        createDocumentItem = new JMenuItem(CREATE_DOCUMENT);
        createDocumentItem.addActionListener(e -> controller.createDocumentMenuPressed());
        fileMenu.add(createDocumentItem);
        disableCreateDocumentMenuItem();

        fileMenu.addSeparator();

        // создание пункта меню "Закрыть"
        closeItem = new JMenuItem(CLOSE);
        closeItem.addActionListener(e -> controller.closeMenuItemPressed());
        fileMenu.add(closeItem);
        disableCloseMenuItem();

        // создание пункта меню "Выход"
        exitItem = new JMenuItem(EXIT);
        exitItem.addActionListener(e -> controller.appClosing());
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
    }

    // создает меню "Настройки"
    private void createSettingsMenu() {
        settingsMenu = new JMenu(SETTINGS);

        // создание пункта меню "Выбрать папку для хранения документов"
        chooseDirectoryItem = new JMenuItem(CHOOSE_FOLDER);
        chooseDirectoryItem.addActionListener(e -> controller.chooseDirectoryMenuItemPressed());
        settingsMenu.add(chooseDirectoryItem);

        // создание пункта меню "Проверка наличия незаполненных полей"
        emptyFieldsCheckItem = new JCheckBoxMenuItem(EMPTY_FIELDS_CHECK); // 2.1

        // применение настроек к пункту меню "Проверка наличия незаполненных полей" TODO разве этот кусок кода не должен находиться в классе-контроллере ??
        emptyFieldsCheckItem.setState(controller.getSettings().isEmptyFieldsCheckEnabled());

        // добавление слушателя, который меняет соответствующую настройку, если состояние этого пункта меню было изменено
        emptyFieldsCheckItem.addChangeListener(e ->
                //settings.setEmptyFieldsCheckStatus(emptyFieldsCheckItem.isSelected())); // see // перенести тело лямбды в класс-котроллер
                controller.emptyFieldsCheckMenuItemChanged()); // see // перенести тело лямбды в класс-котроллер

        settingsMenu.add(emptyFieldsCheckItem);
        menuBar.add(settingsMenu);
    }

    // создает меню "Справка"
    private void createHelpMenu() {
        helpMenu = new JMenu(HELP);

        // создание пункта "О программе"
        aboutItem = new JMenuItem(ABOUT);
        aboutItem.addActionListener(e -> controller.aboutMenuItemPressed());
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);
    }

    // создает панель "Патронаж"
    public void createPatronagePanel() { // перенести внутрь MainWindContr#newPatronageMenuItemPressed() ???
        patronagePanel = new PatronagePanel();
        patronagePanel.createBlanks();
        patronagePanel.createButton();
        setContentPane(patronagePanel);
        setTitle(CASSANDER_PATRONAGE);
    }

    // создает панель "Осмотр взрослого"
    public void createExaminationPanel() { // перенести внутрь MainWindContr#newPatronageMenuItemPressed() ???
        examinationPanel = new ExaminationOfTheAdultPanel();
        setContentPane(examinationPanel);
        validate();
        setTitle(CASSANDER_EXAMINATION_OF_THE_ADULT);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    // разблокировывает пункт меню "Закрыть"
    public static void enableCloseMenuItem() {
        closeItem.setEnabled(true);
    }

    // блокирует пункт меню "Закрыть"
    public static void disableCloseMenuItem() {
        closeItem.setEnabled(false);
    }

    // сходу не придумал нормальный комментарий
    public void setDefaultDimension() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    public void enableCreateDocumentMenuItem() {
        createDocumentItem.setEnabled(true);
    }

    public void disableCreateDocumentMenuItem() {
        createDocumentItem.setEnabled(false);
    }
}