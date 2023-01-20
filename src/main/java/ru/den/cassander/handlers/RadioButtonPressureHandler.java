package ru.den.cassander.handlers;

import ru.den.cassander.settings.Settings;
import ru.den.cassander.settings.XMLSettingsRW;
import ru.den.cassander.windows.DirectoryChooserDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.den.cassander.Main.getMainWindow;

/**
 * Created on 06.09.2015.
 *
 * @author Denis Vereshchagin
 */
@Deprecated
public class RadioButtonPressureHandler implements ActionListener {
    private JTextField chooseDirectoryField;
    private JButton chooseButton;

    private Settings settings = XMLSettingsRW.getSettings();

    // TODO код в хэндлерах - полнейший пиздец ! переписать, сделав обработчики каждого действия в своем уникальном методе

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton sourceRadioButton =(JRadioButton)e.getSource();
        String textOnRadioButton = sourceRadioButton.getText();

        chooseDirectoryField = getMainWindow().getController().getDirectoryChooser().getChooseDirectoryField();
        chooseButton = getMainWindow().getController().getDirectoryChooser().getChooseButton();

        switch (textOnRadioButton) {
            case "Использовать папку по умолчанию":
                chooseDirectoryField.setEditable(false);
                chooseDirectoryField.setText(settings.getDefaultDirectoryPath());
                //chooseDirectoryField.setText(DirectoryChooserDialog.getDefaultDirectory());
                chooseButton.setEnabled(false);
                break;

            case "Выбрать другую папку":
                chooseDirectoryField.setEditable(true);
                chooseDirectoryField.setText("C:\\");
                chooseButton.setEnabled(true);
                break;
        }
    }
}