package ru.den.cassander.settings;

/**
 * Created on 04.03.2018
 * Updated on 04.03.2018
 * <p>
 * Some description
 *
 * @author Denis Vereshchagin
 * @since 2.1
 */
public interface Settings {

    String getDefaultDirectoryPath();

    void setDefaultDirectoryPath(String path);

    String getCurrentDirectoryPath();

    void setCurrentDirectoryPath(String path);

    CurrentDirectoryType getCurrentDirectoryType();

    void setCurrentDirectoryType(CurrentDirectoryType type);

    boolean isEmptyFieldsCheckEnabled();

    void setEmptyFieldsCheckStatus(boolean isEnabled);

    // Тип папки, используемой для хранения документов
    enum CurrentDirectoryType {
        DEFAULT, // папка по умолчанию
        CUSTOM   // пользовательская
    }
}