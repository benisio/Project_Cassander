package ru.den.cassander.gui;

import ru.den.cassander.controllers.MainWindowController;
import ru.den.cassander.gui.main.ExaminationOfTheAdultPanel;
import ru.den.cassander.gui.main.PatronagePanel;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

import static ru.den.cassander.Constants.*;

/**
 * Created on 06.08.2015.
 * Updated on 02.05.2018.
 *
 * Класс, создающий GUI стартового окна приложения
 */
public class MainWindow extends AbstractWindow {
    // TODO 1-я попытка переделать этот класс как Синглтон увенчалась фиаско
    // TODO когда убираешь мышку с открытого списка пунктов меню и кликаешь вне его, меню не закрывается

    // TODO мб имеет смысл завести сюда поле currentPanel и передавать в него значения patronagePanel или examinationPanel
    // в зависимости от выбранного раздела, и использовать это в обработчике меню "Создать документ"
    // если currentPanel == patronagePanel, вызывать код обработки кнопки "Создать документ" для "Патронажа", а если
    // examinationPanel, то для "Осмотра взрослого". Таким образом, можно убрать кнопки "Создать документ" и обойтись только
    // пунктом меню "Создать документ"

    // TODO свести все TODO в одно место. Мб загуглить про баг-репорты или просто завести отдельный txt файл для перечисления
    // всех туду или багов

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

    // загружать картинку главного окна
    private static JLabel icon;
    static {
        try (InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("mainWindow.png")) {
            icon = new JLabel(new ImageIcon(resourceStream.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Возвращает true, если установлен чек-бокс напротив пункта меню "Проверка наличия незаполненных полей",
    // и false в противном случае
    public boolean isEmptyFieldsCheckBoxSelected() {
        return emptyFieldsCheckItem.isSelected();
    }

    private MainWindowController controller; // контроллер

    public MainWindow() {
        super(CASSANDER, 1000, 700, DO_NOTHING_ON_CLOSE);
        panel.setBackground(Color.darkGray); // в настройки ?
        //panel.add(new JLabel(new ImageIcon(ICON_PATH))); // и это в настройки ? нет, наверное, это в папку ресурсы или в корень

        panel.add(icon); // и это в настройки ? нет, наверное, это в папку ресурсы или в корень

        controller = new MainWindowController();

        // создание GUI
        createMenu();
        setGUIAppearance();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.appClosing();
            }
        });// cлушатель закрытия окна
    }

    private void setGUIAppearance() { // устанавливаем внешний вид компонентов типа NimbusLookAndFeel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); //getSystemLookAndFeelClassName()
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

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
        newPatronageItem.addActionListener(e -> controller.newPatronageMenuClicked());
        fileMenu.add(newPatronageItem);

        // создание пункта меню "Новый осмотр взрослого"
        newExaminationOfTheAdultItem = new JMenuItem(NEW_EXAMINATION_OF_THE_ADULT);
        newExaminationOfTheAdultItem.addActionListener(e -> controller.newExaminationMenuClicked());
        fileMenu.add(newExaminationOfTheAdultItem);

        fileMenu.addSeparator();

        // создание пункта меню "Создать документ" // 2.1
        createDocumentItem = new JMenuItem(CREATE_DOCUMENT);
        createDocumentItem.addActionListener(e -> controller.createDocumentMenuClicked());
        fileMenu.add(createDocumentItem);
        disableCreateDocumentMenu();

        fileMenu.addSeparator();

        // создание пункта меню "Закрыть"
        closeItem = new JMenuItem(CLOSE);
        closeItem.addActionListener(e -> controller.closeMenuClicked());
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

        // создание пункта меню "Выбор папки для хранения документов"
        chooseDirectoryItem = new JMenuItem(CHOOSE_FOLDER);
        chooseDirectoryItem.addActionListener(e -> controller.chooseDirectoryMenuClicked());
        settingsMenu.add(chooseDirectoryItem);

        // создание пункта меню "Проверка наличия незаполненных полей"
        emptyFieldsCheckItem = new JCheckBoxMenuItem(EMPTY_FIELDS_CHECK);

        // применение настроек к пункту меню "Проверка наличия незаполненных полей" TODO разве этот кусок кода не должен находиться в классе-контроллере ??
        emptyFieldsCheckItem.setState(controller.getSettings().isEmptyFieldsCheckEnabled());

        // добавление слушателя, который меняет соответствующую настройку, если состояние этого пункта меню было изменено
        emptyFieldsCheckItem.addChangeListener(e ->
                //settings.setEmptyFieldsCheckStatus(emptyFieldsCheckItem.isSelected())); // see // перенести тело лямбды в класс-котроллер
                controller.emptyFieldsCheckMenuChanged()); // перенести тело лямбды в класс-контроллер

        settingsMenu.add(emptyFieldsCheckItem);
        menuBar.add(settingsMenu);
    }

    // создает меню "Справка"
    private void createHelpMenu() {
        helpMenu = new JMenu(HELP);

        // создание пункта "О программе"
        aboutItem = new JMenuItem(ABOUT);
        aboutItem.addActionListener(e -> controller.aboutMenuClicked());
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

    // устанавливает настройки по умолчанию ??? название метода соответствует комменту и телу метода ?
    public void setDefaultDimension() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    // разблокировывает пункт меню "Создать документ"
    public void enableCreateDocumentMenu() {
        createDocumentItem.setEnabled(true);
    }

    // блокирует пункт меню "Создать документ"
    public void disableCreateDocumentMenu() {
        createDocumentItem.setEnabled(false);
    }
}