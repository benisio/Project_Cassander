package ru.den.cassander.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

import ru.den.cassander.document_creators.PatronageCreator;
import ru.den.cassander.settings.Settings;
import ru.den.cassander.settings.XMLSettingsRW;
import ru.den.cassander.windows.CompleteWindow;
import ru.den.cassander.windows.DirectoryChooserDialog;

/**
 * Created on 07.08.2015.
 *
 * @author Denis Vereshchagin
 */
@Deprecated
public class ButtonPressureHandler implements ActionListener {
    // TODO код в хэндлерах - полнейший пиздец ! переписать, сделав обработчики каждого действия в своем уникальном методе

    public final Date TODAY;

    private CompleteWindow completeWindow;
    private PatronageCreator creator;
    private DirectoryChooserDialog directoryChooserDialog;
    private JTextField chooseDirectoryField;

    public PatronageCreator getCreator() {
        return creator;
    }

    private Settings settings;
    public ButtonPressureHandler() {
        TODAY = new Date();
        settings = XMLSettingsRW.getSettings();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton sourceButton =(JButton)e.getSource();
        String textOnButton = sourceButton.getText();

        switch (textOnButton) {
            case "Создать документ": // кнопка "Создать документ" в разделе "Патронаж"

                creator = new PatronageCreator();
                creator.createDocument();

                completeWindow = new CompleteWindow(creator.DESTINATION_FILE_PATH);
                completeWindow.setVisible(true);

                break;

            case "ОК":
                //completeWindow.dispose();
                System.err.println("не юзаю этот блок в батон прэше хэндлере:60");
                break;

                // кнопка "Сохранить" в диалоге выбора папки сохранения документов
            case "Сохранить":
                /*directoryChooserDialog = getMainWindow().getController().getDirectoryChooser();
                chooseDirectoryField = directoryChooserDialog.getChooseDirectoryField();
                JRadioButton defaultRadioButton = directoryChooserDialog.getDefaultRadioButton();

                // применяем настройки
                settings.setCurrentDirectoryPath(chooseDirectoryField.getText());

                // запись типа используемой папки - по умолчанию или пользовательская
                settings.setCurrentDirectoryType(defaultRadioButton.isSelected() ?
                        Settings.CurrentDirectoryType.DEFAULT : Settings.CurrentDirectoryType.CUSTOM);

                directoryChooserDialog.dispose();*/
                System.out.println("обработчик удален из ButtonPressureHandler");
                break;

            case "...":
                /*directoryChooserDialog = getMainWindow().getController().getDirectoryChooser();
                chooseDirectoryField = directoryChooserDialog.getChooseDirectoryField();
                JButton chooseButton = directoryChooserDialog.getChooseButton();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // выбираем только папки
                fileChooser.setAcceptAllFileFilterUsed(false); // а это что ?
                fileChooser.setCurrentDirectory(new File(chooseDirectoryField.getText()));
                int result = fileChooser.showDialog(chooseButton, "Выбрать папку");

                if (result == JFileChooser.APPROVE_OPTION) {
                    String chosenDirectoryPath = fileChooser.getSelectedFile().toString();

                    // добавляем "слэш", если нужен
                    if (!chosenDirectoryPath.endsWith("\\")) {
                        chosenDirectoryPath += "\\";
                    }

                    chooseDirectoryField.setText(chosenDirectoryPath);
                }*/

                System.out.println("обработчик тоже удален из ButtonPressureHandler");

                break;
        }
    }
}