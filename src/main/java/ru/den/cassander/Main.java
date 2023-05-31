package ru.den.cassander;

import ru.den.cassander.gui.MainWindow;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Created on 06.08.2015.
 *
 * This class is a point of entrance of the program. It runs the main window of the application
 */
public class Main {
    // TODO запятые в тексте
    // TODO по поводу бланка: представить его в виде карты: ключ - текст, значение - кол-во подчеркиваний
    // TODO comments all around the project

    /**
     * Номер текущей версии
     *
     * @since 2.1
     */
    private static final String VERSION = "2.1"; // TODO добавить в XML и подгружать оттуда

    private static MainWindow mainWindow;

    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    public static void main(String[] args) throws InterruptedException {
        mainWindow = new MainWindow();

        // устанавливаем внешний вид компонентов типа NimbusLookAndFeel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); //getSystemLookAndFeelClassName()
        } catch (UnsupportedLookAndFeelException e) { // UnsupportedLookAndFeelException
            e.printStackTrace();
        }

        mainWindow.setVisible(true);
    }

    // TODO перенести в settings (почти) все текстовые данные типа элементов списков и загружать оттуда

    // TODO убрать "защиту от дурака" в главном окне
    // System.getProperty("line.separator"); - возвращает перенос строки
}