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
public class JSONSettings implements Settings {
    @Override
    public String getDefaultDirectoryPath() {
        return null;
    }

    @Override
    public void setDefaultDirectoryPath(String path) {

    }

    @Override
    public String getCurrentDirectoryPath() {
        return null;
    }

    @Override
    public void setCurrentDirectoryPath(String path) {

    }

    @Override
    public CurrentDirectoryType getCurrentDirectoryType() {
        return null;
    }

    @Override
    public void setCurrentDirectoryType(CurrentDirectoryType type) {

    }

    @Override
    public boolean isEmptyFieldsCheckEnabled() {
        return false;
    }

    @Override
    public void setEmptyFieldsCheckStatus(boolean isEnabled) {

    }
}