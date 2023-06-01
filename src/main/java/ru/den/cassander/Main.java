package ru.den.cassander;

import ru.den.cassander.gui.MainWindow;

/**
 * Created on 06.08.2015.
 *
 * This class is a point of entrance of the program. It runs the main window of the application
 */
public class Main {
    // TODO запятые в тексте
    // TODO по поводу бланка: представить его в виде карты: ключ - текст, значение - кол-во подчеркиваний
    // TODO comments all around the project

    // TODO в Осмотре взрослого обследования перечисляются в строчку, а не идут каждое с новой строки + добавить пустую строку перед диагнозом

    /**
     * Номер текущей версии
     *
     * @since 2.1
     */

    private static MainWindow mainWindow;

    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    public static void main(String[] args) throws InterruptedException {
        mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

    // TODO перенести в settings (почти) все текстовые данные типа элементов списков и загружать оттуда

    // TODO убрать "защиту от дурака" в главном окне
    // System.getProperty("line.separator"); - возвращает перенос строки
}