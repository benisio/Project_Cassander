package ru.den.cassander.document_creators;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.den.cassander.windows.CompleteWindow;
import ru.den.cassander.windows.main.ExaminationOfTheAdultPanel;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.windows.main.ExaminationOfTheAdultPanelConstants.THERAPY_LIST_FOR_DOCUMENT_ITEMS;


/**
 * Created on 06.12.2015.
 *
 * Класс ExaminationCreator.java создает документ *.docx (?), содержащий результаты осмотра взрослого пациента.
 *
 * @author Denis Vereshchagin
 */
public class ExaminationCreator extends AbstractDocumentCreator {

    private ExaminationOfTheAdultPanel examinationPanel = getMainWindow().getExaminationPanel();

    private String text; // для временного хранения текстов в списках и других элементах

    public ExaminationCreator() {
        super("Осмотр взрослого__" + getCurrentDateAndTime() + ".docx", 10);
    }

    @Override
    public void createDocument() {

        clearRunsAndParagraphs();

        boolean errorFlag = false;

        try {
            writeHeader();
            writeDate();
            writeComplaints();
            writeAnamnesys();
            writeSkinCovering();
            writeLymphaticNodes();
            writeJoints();
            writeMouth();
            writeTonsils();
            writeBreathing();
            writeHeartTones();
            writeNoises();
            writeTonesOnTop();
            writeArterialPressure();
            writePulse();
            writeTongue();
            writeStomach();
            writeMuscleProtection();
            writeLiver();
            writeLiverEdge();

            // почки
            writeKidneysAreaInfo();
            writePasternatskySymptomInfo();
            writeKidneysInfo();
            writeUrinaInfo();

            writeBMInfo();
            writeConstipationsInfo();
            writeUrinationInfo();
            writeEdemasInfo();
            writeDiagnosisInfo();
            writeModeInfo();
            writeExaminationInfo();
            writeDietInfo();
            writeMedicalTherapyInfo();
            writeUrgentCareInfo();
            writeParamedicSign();

            for (XWPFParagraph p : paragraphs) {
                p.setSpacingAfterLines(20);
            }

        } catch (NullPointerException e) {

            // TODO какого хуя проверка на незаполненные поля делает в классе, пишущем текст в документ ?

            // если включена функция проверки незаполненных полей, то выводим сообщение об их наличии
            if (getMainWindow().isEmptyFieldsCheckBoxSelected()) {
                JOptionPane.showMessageDialog(getMainWindow().getExaminationPanel(),
                        "Не заполнен один или несколько списков или текстовых полей !",
                        "Ошибка!",
                        JOptionPane.ERROR_MESSAGE);

                errorFlag = true;
            }

        }

        if (!errorFlag) {
            try (FileOutputStream stream = new FileOutputStream(wordDocument)) {

                outputDocx.write(stream);

                CompleteWindow completeWindow = new CompleteWindow(DESTINATION_FILE_PATH);
                completeWindow.setVisible(true);

                clearRunsAndParagraphs();

            } catch (IOException e) {
                System.out.println("IOException has been caught.");
                e.printStackTrace();
            }
        }
    }

    private void writeHeader() {
        addParagraph();
        addRun();

        text = examinationPanel.getExaminationTypeList().getSelectedItem().toString();

        currentParagraph.setAlignment(ParagraphAlignment.CENTER);
        addBoldText(text, 12);
    }

    private void writeDate() {
        addParagraph();
        addRun();

        addBoldText("Дата");
        addWhiteSpace();
        addSeveralUnderlines(6);

        text = examinationPanel.getDate();
        addUnderlinedText(text);

        addSeveralUnderlines(7);
        addWhiteSpace();
    }

    private void writeComplaints() {
        addRun();

        addBoldText("Жалобы ");
        addUnderline();

        text = examinationPanel.getComplaintsArea().getText();
        addUnderlinedText(text);

        addUnderline();
    }

    private void writeAnamnesys() {
        addParagraph();
        addRun();

        addBoldText("Анамнез: ");
        addUnderline();

        text = examinationPanel.getAnamnesisArea().getText();
        addUnderlinedText(text);

        addUnderline();
    }

    private void writeSkinCovering() {
        addParagraph();
        addRun();

        addBoldText("Кожные покровы ");

        text = examinationPanel.getSkinList().getSelectedItem().toString();
        addText(text);

        addComma();
    }

    private void writeLymphaticNodes() {
        addBoldText("лимфатические узлы ");

        text = examinationPanel.getLymphaticNodesList().getSelectedItem().toString();
        addText(text);

        addComma();
    }

    private void writeJoints() {
        addBoldText("суставы ");

        text = examinationPanel.getJointsList().getSelectedItem().toString();
        addText(text);

        if (examinationPanel.getJointsTypeList().isEnabled()) {
            addWhiteSpace();

            text = examinationPanel.getJointsTypeList().getSelectedItem().toString();
            addText(text);
        }

        addComma();
    }

    private void writeMouth() {
        addBoldText("зев ");

        text = examinationPanel.getMouthList().getSelectedItem().toString();
        addText(text);

        addComma();
    }

    private void writeTonsils() {
        addBoldText("миндалины ");

        text = examinationPanel.getTonsilsList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeBreathing() {
        addParagraph();
        addRun();

        addBoldText("В лёгких выслушивается ");

        text = examinationPanel.getBreathingList().getSelectedItem().toString();
        addText(text);

        addBoldText(" дыхание");
        addComma();

        text = examinationPanel.getRalesList().getSelectedItem().toString();
        addText(text);
        addDot();

        addBoldText("Число дыханий ");
        text = examinationPanel.getBreathingNumberList().getSelectedItem().toString();
        addText(text);
        addBoldText(" в мин.");
    }

    private void writeHeartTones() {
        addParagraph();
        addRun();

        addBoldText("Тоны сердца ");
        text = examinationPanel.getHeartTonesList().getSelectedItem().toString();
        addText(text);

        if (examinationPanel.getAccentList().isEnabled()) {
            addComma();

            addBoldText("акцент II т. ");

            text = examinationPanel.getAccentList().getSelectedItem().toString();
            addText(text);
        }

        addDot();
    }

    private void writeNoises() {
        boolean systolicNoiseIsChosen = examinationPanel.getSystolicNoiseBox().isSelected();
        boolean diastolicNoiseIsChosen = examinationPanel.getDiastolicNoiseBox().isSelected();

        if (systolicNoiseIsChosen || diastolicNoiseIsChosen) {
            addBoldText("Шум ");

            if (systolicNoiseIsChosen) {
                addBoldText("систолический: ");
                addText("на верхушке во II межреберье справа");

                if (diastolicNoiseIsChosen) {
                    addComma();
                }
            }

            if (diastolicNoiseIsChosen) {
                addBoldText("диастолический: ");

                text = examinationPanel.getDiastolicNoiseList().getSelectedItem().toString();
                addText(text);

            }

            addDot();
        }
    }

    private void writeTonesOnTop() {
        if (examinationPanel.getTonesOnTopBox().isSelected()) {
            addBoldText(examinationPanel.getTonesOnTopBox().getText());
            addDot();
        }
    }

    private void writeArterialPressure() {
        addBoldText("АД ");

        writeRightHandPressure();

        addComma();

        writeLeftHandPressure();

    }

    private void writeRightHandPressure() {
        addText("на правой руке ");

        addUnderline();
        text = examinationPanel.getRightHandList1().getSelectedItem().toString();
        addUnderlinedText(text);
        addUnderlinedText(SLASH);

        text = examinationPanel.getRightHandList2().getSelectedItem().toString();
        addUnderlinedText(text);
        addUnderline();

        addText(" мм рт. ст.");
    }

    private void writeLeftHandPressure() {
        addText("на левой руке ");

        addUnderline();
        text = examinationPanel.getLeftHandList1().getSelectedItem().toString();
        addUnderlinedText(text);
        addUnderlinedText(SLASH);

        text = examinationPanel.getLeftHandList2().getSelectedItem().toString();
        addUnderlinedText(text);
        addUnderline();

        addText(" мм рт. ст. ");
    }

    private void writePulse() {
        addBoldText("Пульс ");

        addUnderline();
        text = examinationPanel.getPulseList().getSelectedItem().toString();
        addUnderlinedText(text);
        addUnderline();

        addText(" ударов в мин.");

        addWhiteSpace();
        writePulseFeature();
        addComma();
        writeFilling();
        addComma();
        writeTension();
        addDot();
    }

    private void writePulseFeature() {
        addBoldText("Хар-ки пульса: ");

        text = examinationPanel.getPulseFeaturesList().getSelectedItem().toString();
        addText(text);
    }

    private void writeFilling() {
        text = examinationPanel.getFillingList().getSelectedItem().toString();
        int index = examinationPanel.getFillingList().getSelectedIndex();

        // если выбран последний элемент
        if (index == 2) {
            addText(text);
        } else {
            addBoldText("наполнения ");
            addText(text);
        }
    }

    private void writeTension() {
        text = examinationPanel.getTensionList().getSelectedItem().toString();
        int index = examinationPanel.getTensionList().getSelectedIndex();

        // если выбран последний элемент
        if (index == 2) {
            addText(text);
        } else {
            addBoldText("напряжения ");
            addText(text);
        }
    }

    private void writeTongue() {
        addParagraph();
        addRun();

        addBoldText("Язык ");

        text = examinationPanel.getTongueList().getSelectedItem().toString();
        addText(text);
        addDot();
    }

    private void writeStomach() {
        addBoldText("Живот ");

        text = examinationPanel.getStomachList().getSelectedItem().toString();
        addText(text);
        addComma();

        writeStomachPalpation();
    }

    private void writeStomachPalpation() {
        addBoldText("при пальпации - ");

        text = examinationPanel.getPalpationList().getSelectedItem().toString();
        addText(text);

        if (examinationPanel.getWhereList().isEnabled()) {
            addWhiteSpace();

            text = examinationPanel.getWhereList().getSelectedItem().toString();
            addText(text);
        }

        addDot();
    }

    private void writeMuscleProtection() {
        addBoldText("Мышечная защита ");

        text = examinationPanel.getMuscleProtectionList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeLiver() {
        addParagraph();
        addRun();

        addBoldText("Печень ");

        text = examinationPanel.getLiverList().getSelectedItem().toString();
        addText(text);

        if (examinationPanel.getCmList().isEnabled()) {
            addWhiteSpace();
            addSeveralUnderlines(2);

            text = examinationPanel.getCmList().getSelectedItem().toString();
            addUnderlinedText(text);

            addSeveralUnderlines(2);
            addWhiteSpace();

            addText("см");
        }

        addDot();
    }

    private void writeLiverEdge() {
        addBoldText("Край ");

        text = examinationPanel.getEdgeList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    // записывает информацию "Область почек"
    private void writeKidneysAreaInfo() {
        addParagraph();
        addRun();

        addBoldText("Область почек ");

        text = examinationPanel.getKidneysAreaList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    // записывает информацию "Симптом Пастернацкого"
    private void writePasternatskySymptomInfo() {
        addRun();

        addBoldText("Симптом Пастернацкого ");

        text = examinationPanel.getPasternatskySymptomList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    // записывает информацию "Почки"
    private void writeKidneysInfo() {
        addRun();

        addBoldText("Почки ");

        text = examinationPanel.getKidneysList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    // записывает информацию "Моча"
    private void writeUrinaInfo() {
        addRun();

        addBoldText("Моча ");

        text = examinationPanel.getUrinaList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeBMInfo() {
        addParagraph();
        addRun();

        addBoldText("Стул ");

        text = examinationPanel.getbMList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeConstipationsInfo() {
        addBoldText("Запоры ");

        text = examinationPanel.getConstipationList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeUrinationInfo() {
        addBoldText("Мочеиспускание ");

        text = examinationPanel.getUrinationList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeEdemasInfo() {
        addBoldText("Отеки ");

        text = examinationPanel.getEdemasList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeDiagnosisInfo() {
        addParagraph();
        addRun();

        addBoldText("Диагноз: ");

        text = examinationPanel.getDiagnosisField().getText();
        addText(text);

        addDot();
    }

    private void writeModeInfo() {
        addBoldText("Режим ");

        text = examinationPanel.getModeList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeExaminationInfo() {
        addParagraph();
        addRun();
        currentRun.addBreak();

        addBoldText("Обследование:");
        currentRun.addBreak();

        text = examinationPanel.getExaminationArea().getText();
        addText(text);
    }

    private void writeDietInfo() {
        addParagraph();
        addRun();

        addBoldText("Диета: ");

        text = examinationPanel.getDietList().getSelectedItem().toString();
        addText(text);

        addDot();
    }

    private void writeMedicalTherapyInfo() {
        addParagraph();
        addRun();
        currentRun.addBreak();

        addBoldText("Медикаментозная терапия:");
        currentRun.addBreak();

        text = examinationPanel.getTherapyList().getSelectedItem().toString();

        byte index = (byte)examinationPanel.getTherapyList().getSelectedIndex();
        text = THERAPY_LIST_FOR_DOCUMENT_ITEMS[index];
        addText(text);

        currentRun.addBreak();

        text = examinationPanel.getTherapyPreparationsArea().getText();
        addText(text);
    }

    private void writeUrgentCareInfo() {
        addParagraph();
        addRun();
        currentRun.addBreak();

        addBoldText("Неотложная помощь:");

        currentRun.addBreak();

        text = examinationPanel.getUrgentCarePreparationsArea().getText();
        addText(text);
    }

    private void writeParamedicSign() {
        addParagraph();
        currentParagraph.setAlignment(ParagraphAlignment.RIGHT);

        addRun();
        currentRun.addBreak();

        addText("Фельдшер ");
        addSeveralUnderlines(37);
    }

    private void addUnderline() {
        addText("_", UnderlinePatterns.NONE, 0, DEFAULT_FONT_SIZE);
    }

    /**
     * Добавляет несколько пробелов с подчеркиванием.
     *
     * @param amount количество пробелов, которое нужно добавить.
     * @see #addUnderline()
     */
    private void addSeveralUnderlines(int amount) {
        for (byte i = 0; i < amount; i++) {
            addUnderline();
        }
    }

    @Override
    protected void setStyles(XWPFRun run, UnderlinePatterns pattern, int pos, boolean isBold, boolean isItalic, int fontSize) {
        run.setFontSize(fontSize);
        run.setFontFamily("Arial");
        run.setUnderline(pattern);
        run.setTextPosition(pos);
        run.setBold(isBold);
        run.setItalic(isItalic);
    }

    // TODO обработчик NPE в начале класса не ловит ситуации, когда текстовые области не заполнены
    // TODO при увеличении документа видны траблы у подчеркнутого текста
}