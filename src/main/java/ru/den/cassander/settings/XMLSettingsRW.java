package ru.den.cassander.settings;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created on 04.03.2018
 * Updated on 04.03.2018
 *
 * Класс для чтения настроек из xml-файла в объект XMLSettings и записи обратно.
 *
 * В идеале: сделать так, чтобы ему можно было бы подсунуть любые настройки: в виде
 * XML, JSON или TXT ()
 */
public class XMLSettingsRW extends SettingsRW {

    private static JAXBContext jaxb;
    private static Settings xmlSettings;

    private static final File SETTINGS = new File("src\\main\\java\\ru\\den\\cassander\\settings\\settings.xml");

    // считывает настройки из xml-файла в объект XMLSettings.java
    @Override
    public Settings readSettings() {
        try {
            jaxb = JAXBContext.newInstance(XMLSettings.class, XMLSettings.Directory.class);
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            xmlSettings = (XMLSettings) unmarshaller.unmarshal(SETTINGS);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlSettings;
    }

    // записывает настройки из объекта XMLSettings.java в xml-файл
    @Override
    public void writeSettings() {
        try {
            Marshaller marshaller = jaxb.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // форматирует текст в файле настроек
            marshaller.marshal(xmlSettings, SETTINGS); // запись изменений в файл
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Settings getSettings() {
        return xmlSettings;
    }
}