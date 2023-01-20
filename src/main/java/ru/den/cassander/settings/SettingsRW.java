package ru.den.cassander.settings;

/**
 * Created on 04.03.2018
 * Updated on 04.03.2018
 * <p>
 * Some description
 *
 * @author Denis Vereshchagin
 * @since $VERSION
 */
public abstract class SettingsRW {

    public abstract Settings readSettings();

    public abstract void writeSettings();

}