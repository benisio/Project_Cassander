package ru.den.cassander.handlers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.den.cassander.windows.*;

import static ru.den.cassander.Constants.*;
import static ru.den.cassander.Main.getMainWindow;

/**
 * Created on 29.08.2015.
 *
 * @author Denis Vereshchagin
 */
@Deprecated
public class MenuItemPressureHandler implements ActionListener {

    private DirectoryChooserDialog directoryChooser;
    private static short patronageCallCounter = 0;
    private static short examinationCallCounter = 0;

    public DirectoryChooserDialog getDirectoryChooser() {
        return directoryChooser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem)e.getSource();
        String textOnMenuItem = menuItem.getText();

        switch (textOnMenuItem) {
            case NEW_PATRONAGE:
                examinationCallCounter = 0;

                if (++patronageCallCounter == 1) { // если вызывает первый раз ????
                    getMainWindow().createPatronagePanel();
                    getMainWindow().setDefaultDimension();
                    MainWindow.enableCloseMenuItem();
                    getMainWindow().enableCreateDocumentMenuItem();
                } else {
                    getMainWindow().getPatronagePanel().resetComponent(getMainWindow().getPatronagePanel());
                }

                break;

            case NEW_EXAMINATION_OF_THE_ADULT:
                patronageCallCounter = 0;

                if (++examinationCallCounter == 1) {
                    getMainWindow().createExaminationPanel();
                    //getMainWindow().pack();
                    MainWindow.enableCloseMenuItem();
                    getMainWindow().enableCreateDocumentMenuItem();
                } else {
                    getMainWindow().getExaminationPanel().resetComponent(getMainWindow().getExaminationPanel());
                    getMainWindow().getExaminationPanel().setTherapyPreparationsDialog(new TherapyPreparationsDialog());
                    getMainWindow().getExaminationPanel().setUrgentCarePreparationsDialog(new UrgentCarePreparationsDialog());
                }

                break;

            case EXIT:
                System.exit(0);
                break;

            case ABOUT:
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.setVisible(true);

                break;

            case CHOOSE_FOLDER:
                directoryChooser = new DirectoryChooserDialog();
                break;

            case CLOSE:
                getMainWindow().setContentPane(getMainWindow().getPanel());
                getMainWindow().validate();
                //getMainWindow().repaint();

                MainWindow.disableCloseMenuItem();
                getMainWindow().disableCreateDocumentMenuItem();
                getMainWindow().setTitle(CASSANDER);
                getMainWindow().setDefaultDimension();
                clearCounters();

                break;
        }
    }

    private void clearCounters() {
        patronageCallCounter = 0;
        examinationCallCounter = 0;
    }
}