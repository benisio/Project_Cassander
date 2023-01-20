package ru.den.cassander.settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created on 04.03.2018
 * Updated on 04.03.2018
 *
 * Класс для чтения и записи настроек из xml-файла.
 * В идеале: сделать так, чтобы ему можно было бы подсунуть любые настройки: в виде
 * XML, JSON или TXT ()
 *
 * @author Denis Vereshchagin
 * @since 2.1
 */
public class XMLSettingsRW extends SettingsRW {

    private static JAXBContext jaxb;
    private static Settings xmlSettings;

    private static final File XML_FILE = new File("src\\main\\java\\ru\\den\\cassander\\settings\\settings.xml");

    @Override
    public Settings readSettings() {
        try {
            jaxb = JAXBContext.newInstance(XMLSettings.class, XMLSettings.Directory.class);
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            xmlSettings = (XMLSettings) unmarshaller.unmarshal(XML_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlSettings;
    }

    @Override
    public void writeSettings() {
        try {
            Marshaller marshaller = jaxb.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // форматирует текст в файле настроек
            marshaller.marshal(xmlSettings, XML_FILE); // запись изменений в файл
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Settings getSettings() {
        return xmlSettings;
    }
}