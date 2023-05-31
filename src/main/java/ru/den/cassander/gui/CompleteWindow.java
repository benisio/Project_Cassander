package ru.den.cassander.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

import static java.awt.GridBagConstraints.*;
import static ru.den.cassander.Utilities.*;

/**
 * Created on 07.08.2015.
 * Updated on January 2023
 */

public class CompleteWindow extends AbstractWindow {

    // TODO переписать CompleteWindow с использованием JOptionPane и поправить кнопку Открыть

    private String destinationFilePath;
    private CompleteWindowController controller;

    public CompleteWindow(String destinationFilePath) {
        super("Готово!", 650, 200, DISPOSE_ON_CLOSE);
        this.destinationFilePath = destinationFilePath;

        controller = new CompleteWindowController();

        createLabel();
        createOKButton();
        createOpenButton();
        createOpenFileLocationButton();
    }

    private JLabel text;
    private JLabel icon;
    private JButton OKButton;
    private JButton openButton; // @since 2.1
    private JButton openFileLocationButton;// кнопка "Открыть расположение файла" @since 2.1

    private void createLabel() {
        icon = new JLabel(new ImageIcon("D:\\Патронаж\\info.png"));
        text = new JLabel(convertToMultiline("Документ успешно создан!\n\n" +
                "Расположение документа:\n" + destinationFilePath));

        panel.add(icon, new GridBagConstraints(0, 0, 1, 1, 0.9, 0.9,
                CENTER, NONE, new Insets(0, 7, 10, 0), 0, 0));
        panel.add(text, new GridBagConstraints(1, 0, 3, 1, 0.9,
                0.9, CENTER, NONE, new Insets(0, 0, 10, 0), 0, 0));
    }

    private void createOKButton() {
        OKButton = new JButton("ОК");

        OKButton.addActionListener(event -> dispose());
        panel.add(OKButton, new GridBagConstraints(1, 1, 1, 1, 0.9, 0.9,
                EAST, NONE, new Insets(0, 3, 5, 5), 20, 0));
    }

    // создает кнопку "Открыть"
    private void createOpenButton() {
        openButton = new JButton("Открыть");
        openButton.addActionListener(e -> controller.openButtonClicked());
        panel.add(openButton, new GridBagConstraints(2, 1, 1, 1, 0.9, 0.9,
                EAST, NONE, new Insets(0, 3, 5, 5 ), 20, 0));
    }

    // создает кнопку "Открыть расположение файла"
    private void createOpenFileLocationButton() {
        openFileLocationButton = new JButton("Открыть расположение файла");
        openFileLocationButton.addActionListener(e -> controller.openFileLocationButtonClicked());
        panel.add(openFileLocationButton, new GridBagConstraints(3, 1, 1, 1, 0.9, 0.9,
                EAST, NONE, new Insets(0, 3, 5, 5 ), 20, 0));
    }

    /**
     * Created on January 2023
     * Класс-обработчик событий окна "Готово!"
     */
    private class CompleteWindowController {

        // обработчик нажатия кнопки "Открыть"
        private void openButtonClicked() {
            try {
                System.out.println(destinationFilePath);
                Desktop.getDesktop().open(new File(destinationFilePath)); // запуск приложения ОС (здесь - ворд)
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // обработчик нажатия кнопки "Открыть расположение файла"
        private void openFileLocationButtonClicked() {
            try {
                String documentLocation = new File(destinationFilePath).getParent();
                System.out.println("createOpenFileLocationButton()");
                System.out.println(documentLocation);
                Desktop.getDesktop().open(new File(documentLocation)); // TODO исправить на реальное расположение файла
            } catch (IOException ex) {
                throw new RuntimeException("Исключение в методе createOpenFileLocationButton() класса CompleteWindow", ex);
            }
        }
    }
}