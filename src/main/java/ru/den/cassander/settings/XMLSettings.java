package ru.den.cassander.settings;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created on 10.11.2015
 * Updated on 03.03.2018
 *
 * Класс для хранения данных, прочитанных из xml-файла настроек.
 * Класс, хранящий состояние настроек, прочитанных из xml-файла
 * На время работы программы является временным хранилищем настроек после их
 * чтения из xml и до записи.
 */
@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.NONE) // TODO надо ли исправить на другое значение ?
public class XMLSettings implements Settings {

    @XmlElementWrapper
    @XmlElement(name = "directory")
    private List<Directory> directories;

    // Тип папки, используемой для хранения документов
    @XmlElement(name = "currentDirectoryType")
    private CurrentDirectoryType type;

    // Флаг, равный true, если проверка наличия незаполненных полей включена, и false, если отключена
    @XmlElement(name = "emptyFieldsCheckEnabled")
    private boolean emptyFieldsCheckEnabled;

    @Override
    public CurrentDirectoryType getCurrentDirectoryType() {
        return type;
    }

    @Override
    public void setCurrentDirectoryType(CurrentDirectoryType type) {
        this.type = type;
    }

    // Возвращает true, если проверка наличия незаполненных полей включена, и false в противном случае
    @Override
    public boolean isEmptyFieldsCheckEnabled() {
        return emptyFieldsCheckEnabled;
    }

    // устанавливает статус проверки на незаполненные поля
    // true - включена
    // false - отключена
    @Override
    public void setEmptyFieldsCheckStatus(boolean isEnabled) {
        this.emptyFieldsCheckEnabled = isEnabled;
    }

    // Возвращает ПУТЬ К ПАПКЕ, используемой ПО УМОЛЧАНИЮ для хранения документов.
    @Override
    public String getDefaultDirectoryPath() {
        return getDirectory(Directory.DirectoryType.DEFAULT).getPath();
    }

    // Устанавливает ПУТЬ К ПАПКЕ, которая будет использоваться ПО УМОЛЧАНИЮ для хранения документов.
    // TODO нужен ли здесь public доступ ?
    @Override
    public void setDefaultDirectoryPath(String path) {
        getDirectory(Directory.DirectoryType.DEFAULT).setPath(path);
    }

    /* Возвращает ПУТЬ К ПАПКЕ, ФАКТИЧЕСКИ используемой для хранения документов. Эта папка может отличаться от папки,
    возвращаемой методом XMLSettings#getDefaultDirectoryPath(), если пользователь в настройках выбрал другую папку для
    хранения документов, отличную от папки ПО УМОЛЧАНИЮ. Если же он этого не делал, или же выбрал опцию "Использовать
    папку по умолчанию", этот метод вернет тот же результат, что и метод XMLSettings#getDefaultDirectoryPath(). */
    @Override
    public String getCurrentDirectoryPath() {
        return getDirectory(Directory.DirectoryType.CURRENT).getPath();
    }

    /* Устанавливает ПУТЬ К ПАПКЕ, которая будет ФАКТИЧЕСКИ использоваться для хранения документов, создаваемых данным
    приложением. */
    @Override
    public void setCurrentDirectoryPath(String path) {
        getDirectory(Directory.DirectoryType.CURRENT).setPath(path);
    }

    /* Возвращает ПАПКУ типа, переданного параметром type.
    Если значение type равно XMLSettings.Directory.DirectoryType.CURRENT, метод вернет ПАПКУ, используемую ПО УМОЛЧАНИЮ.
    Если значение type равно XMLSettings.Directory.DirectoryType.DEFAULT, метод вернет ФАКТИЧЕСКИ используемую ПАПКУ. */
    private Directory getDirectory(Directory.DirectoryType type) {
        Directory directory = new Directory();

        for (Directory d : directories) {
            if (d.getType().equals(type)) {
                directory = d;
            }
        }

        return directory;
    }

    /**
     * Created on 28.02.2018
     * Updated on 03.03.2018
     *
     * Класс-представление элемента <directory> ("папка") из файла настроек.
     *
     * @author Denis Vereshchagin
     * @since 2.1
     */
    static class Directory {

        private DirectoryType type; // тип папки
        private String path; // путь к данной папке

        // Значения, которые может принимать тип папки (атрибут "type" элемента <directory>)
        private enum DirectoryType {
            CURRENT, // ФАКТИЧЕСКИ используемая папка
            DEFAULT  // папка ПО УМОЛЧАНИЮ
        }

        @XmlAttribute(name = "type")
        public DirectoryType getType() {
            return type;
        }

        /* private уровень доступа, т.к. этот сеттер не должен нигде использоваться, а его удаление влечет к ошибке во
        время компиляции. Возможно, @XmlAccessorType с нужным значением ее параметра у класса XMLSettings.java решит эту
        проблему. */
        private void setType(DirectoryType type) {
            this.type = type;
        }

        @XmlElement(name = "path")
        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}