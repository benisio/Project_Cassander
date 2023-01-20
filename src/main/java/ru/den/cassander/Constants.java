package ru.den.cassander;

import java.awt.Desktop;
import java.io.File;

/**
 * Created on 29.08.2015.
 *
 * @author Denis Vereshchagin
// */
public abstract class Constants {

    // названия главного окна
    public static final String CASSANDER = "Кассандр";
    public static final String CASSANDER_PATRONAGE = "Кассандр - Патронаж";
    public static final String CASSANDER_EXAMINATION_OF_THE_ADULT = "Кассандр - Осмотр взрослого";

    // названия пунктов меню
    public static final String FILE = "Файл";
    public static final String NEW_PATRONAGE = "Новый патронаж";
    public static final String NEW_EXAMINATION_OF_THE_ADULT = "Новый осмотр взрослого";
    public static final String CREATE_DOCUMENT = "Создать документ";
    public static final String CLOSE = "Закрыть";
    public static final String EXIT = "Выйти";
    public static final String SETTINGS = "Настройки";
    public static final String CHOOSE_FOLDER = "Выбор папки для хранения документов";
    public static final String EMPTY_FIELDS_CHECK = "Проверка наличия незаполненных полей";
    public static final String HELP = "Справка";
    public static final String ABOUT = "О программе";

    // другие
    public static final String EMPTY_STRING = "";

    public static final String DATE = "Дата:";
    public static final String COMPLAINTS = "Жалобы";

    // TODO пример запуска сторонней программы из Java (здесь - MS Word)
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\User\\Desktop\\first.chm";
            File f = new File(filePath); //filePath - полный путь до вашего файла
            if (f.exists() && f.canRead()) {
                Desktop.getDesktop().open(f); //Вот то, что вам нужно

                //Этот код для отслеживания открытых приложением файлов, и когда файл
                //будет закрыт - то удаление файла с диска (файлы у меня берутся из бд и
                //имена у них временные, на основе GUID, поэтому их надо удалять...
//                opened_files_thread.setSuspended(15000);
//                insOpenedFile.setString(1, generateGuid());
//                insOpenedFile.setString(2, getCurrentUser());
//                insOpenedFile.setString(3, filePath);
//                insOpenedFile.execute();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}