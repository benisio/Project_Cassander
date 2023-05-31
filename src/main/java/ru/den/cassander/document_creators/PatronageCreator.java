package ru.den.cassander.document_creators;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

import static ru.den.cassander.Main.getMainWindow;
import static ru.den.cassander.Constants.EMPTY_STRING;

/**
 * Created on 18.08.2015.
 *
 * Класс, создающий docx-файл "Патронаж", используя введенные данные в окне "Новый патронаж"
 */
public class PatronageCreator extends AbstractDocumentCreator {

    public PatronageCreator() {
        super("Патронаж__" + getCurrentDateAndTime() + ".docx", 14);
    }

    private static final byte AMOUNT_OF_WHITESPACES = 28; // количество пробелов до текста в некоторых строках бланка (29 было)

    private String textOnField;

    // TODO ошибка: если в настройках в качестве папки для хранения файлов задать путь "C:\", то этот метод выкидывает
    // FileNotFoundException с сообщением "Отказано в доступе"
    // но на этом чудеса не заканчиваются: после этого все равно программа показывает окно о том, что файл успешно создан,
    // хотя он нихуя не создан и ни о каком успехе речи не идет. Данное окно всплывает только при создании файла "Патронаж".
    // При создании файла "Осмотр взрослого" оно не всплывает
    // Upd оказывается, я уже сталкивался с этой проблемой - см. ниже.
    @Override
    public void createDocument() {
        try ( FileOutputStream stream = new FileOutputStream(wordDocument) ) {

            createBlank(0);

            addParagraph();
            currentParagraph.setSpacingAfter(100);

            createBlank(1);

            outputDocx.write(stream);

            clearRunsAndParagraphs();

        } catch (IOException e) {
            System.out.println("IOException has been caught.");
            e.printStackTrace();

            //TODO при выборе папки C:\Users\ выскакивает IOException с текстом "отказано в доступе", причем поведение
            // программы различается в зависимости от активного раздела - патронаж или осмотр (окно комплитВиндоу в
            // одном случае открывается, а в другом нет). написать нормальный обработчик таких исключений
        }
    }

    @Override
    protected void setStyles(XWPFRun run, UnderlinePatterns pattern, int pos, boolean isBold, boolean isItalic, int fontSize) {
        run.setFontSize(fontSize);
        run.setFontFamily("Times New Roman");
        run.setUnderline(pattern);
        run.setTextPosition(pos);
        run.setBold(isBold);
        run.setItalic(isItalic);
    }

    /**
     * Добавляет символ нижнего подчеркивания, сдвинутый на 2 вверх, который внешне выглядит
     * как пробел с подчеркиванием, но обладает большей шириной. Использование такой имитации
     * пробела с подчеркиванием объясняется тем, что MS Word при включенном режиме подчеркивания
     * не подчеркивает пробелы, после которых нет текста.
     */
    private void addUnderline() {
        addText("_", UnderlinePatterns.NONE, 0, 12);
    }

    /**
     * Версия предыдущего метода, отличающаяся высотой pos сдвига подчеркнутого пробела.
     *
     * @see #addUnderline()
     * @param pos высота, на которую нужно поднять подчеркивание пробела.
     */
    private void addUnderline(int pos) {
        addText("_", UnderlinePatterns.NONE, pos, 12);
    }

    /**
     * Добавляет несколько пробелов с подчеркиванием.
     *
     * @see     #addUnderline()
     * @param   amount количество пробелов, которое нужно добавить.
     */
    private void addSeveralUnderlines(int amount) {
        for (byte i = 0; i < amount; i++) {
            addUnderline();
        }
    }

    private void addSeveralUnderlines(int amount, int pos) {
        for (byte i = 0; i < amount; i++) {
            addUnderline(pos);
        }
    }

    /**
     * В зависимости от длины текста, написанного в поле, вычисляет количество подчеркиваний,
     * необходимое для заполнения строки в документе до конца (до правого края). Формула для вычисления:
     * искомое количество подчеркиваний = количество подчеркиваний в отсутствие заполнения -
     *  - подчеркивание между словом и текстом ( = 1) - длина текста.
     *
     * @param   textOnField текст, введенный в конкретное поле
     * @param   totalAmountOfUnderlines количество подчеркиваний до конца строки при отсутствии заполнения текстом.
     * @return  рассчитанное количество подчеркиваний
     */
    @SuppressWarnings("UnnecessaryLocalVariable") // убирает предупреждение об избыточности resultAmountOfUnderlines в предпоследней строке
    private byte countUnderlinesToAdd(String textOnField, int totalAmountOfUnderlines) {
        final byte leftUnderline = 1;
        byte length = (byte)textOnField.length();
        byte resultAmountOfUnderlines = (byte)(totalAmountOfUnderlines - leftUnderline - length);
        return resultAmountOfUnderlines;
    }

    String[] words;
    String writtenWords = EMPTY_STRING;
    byte emptyLineLength; // количество подчеркиваний (без первого) при отсутствии заполнения поля Показатели НПР (43)
    byte lineNumber = 1;
    /**
     *
     * добавить метод, который в зависимости от длины текста, написанного в поле, правильно
     * разбивает его и записывает в документ в 4 строки.
     */
    private void addMultilineText(String textOnField) {
        words = textOnField.split(WHITESPACE);

        for (String s : words) {
            if ( spaceIsEnough(s) ) {
                addUnderline();
                addText(s, UnderlinePatterns.SINGLE, 0, 12);
                writtenWords = new StringBuilder(writtenWords).append(WHITESPACE).append(s).toString(); //writtenWords += " " + s
            } else {// заполняем подчеркиваниями и переходим на след. строку, модифицируя lineNumber
                if (lineNumber == 1) {
                    addSeveralUnderlines(countUnderlinesToAdd(writtenWords, 43));
                } else {
                    addSeveralUnderlines(countUnderlinesToAdd(writtenWords, 76));
                }

                addParagraph();

                currentParagraph.setSpacingAfter(100);

                addUnderline();
                addText(s, UnderlinePatterns.SINGLE, 0, 12);

                lineNumber++;
                writtenWords = WHITESPACE + s;
            }
        }
    }

    /**
     * Проверяет, достаточно ли места до конца строки для написания слова. Формула расчета: длина уже написанных слов +
     * + длина пробела + длина записываемого слова.
     * @param s записываемое слово
     * @return true, если достаточно, иначе false.
     * */
    @SuppressWarnings("UnnecessaryLocalVariable")
    private boolean spaceIsEnough(String s) {
        boolean result;

        emptyLineLength = countUnderlinesToAdd(EMPTY_STRING, (lineNumber == 1) ? 44 : 77 ); // тут был if
        result = writtenWords.length() + 1 + s.length() < emptyLineLength;

        return result;
    }

    /**
     * Добавляет текст, набранный в текстовом поле, в документ. Сложность метода (блок if/else)
     * обусловлена тем, что пробел имеет меньшую ширину, чем подчеркивание.
     */
    private void addTextFromTextField() {
        if ( !textOnField.contains(WHITESPACE)) {
            addUnderline();
            addText(textOnField, UnderlinePatterns.SINGLE, 0, 12);
        } else {
            String[] words = textOnField.split(WHITESPACE);

            for (String s : words) {
                addUnderline();
                addText(s, UnderlinePatterns.SINGLE, 0, 12);
            }
        }
    }

    private void fillTheRestOfLinesWithUnderlines() {
        if (lineNumber == 1) {
            addSeveralUnderlines(countUnderlinesToAdd(writtenWords, 44));
        } else {
            addSeveralUnderlines(countUnderlinesToAdd(writtenWords, 77));
        }

        switch (lineNumber) {
            case 1:
                addBlankLineOfUnderlines();
            case 2:
                addBlankLineOfUnderlines();
            case 3:
                addBlankLineOfUnderlines();
            case 4:
        }
    }

    private void addBlankLineOfUnderlines() {
        addParagraph();
        currentParagraph.setSpacingAfter(100);
        addSeveralUnderlines(countUnderlinesToAdd(EMPTY_STRING, 77) + 1);
    }

    /**
     *
     */
    private void createParagraph(String text) {
        addParagraph();
        currentParagraph.setSpacingAfter(0);
        addWhiteSpaces(29);
        addText(text);
    }

    private void createBlank(int numberOfBlank) {

        createParagraph(numberOfBlank, 1, "Дата");
        createParagraph(numberOfBlank, 2, "Жалобы");
        createParagraph(numberOfBlank, 3, "Вскармливание");
        createParagraph(numberOfBlank, 4, "Состояние");
        createParagraph(numberOfBlank, 5, "Поведение");
        createParagraph(numberOfBlank, 6, "Показатели НПР");

        createParagraph("           Назначения                        Приглашены на прием_____");
        createParagraph("1.Режим_________________        ________________________");
        createParagraph("2.Питание_______________");
        createParagraph("3.Физ.восп.______________");
        createParagraph("4.Закаливание____________");
        createParagraph("________________________");
        createParagraph("5.Обучение______________");
        createParagraph("________________________\t   Подпись_________________");
    }

    private void createParagraph(int numberOfBlank, int numberOfParagraph, String text) {
        byte spacing = 100;

        addParagraph();
        currentParagraph.setSpacingAfter(spacing);

        if (numberOfParagraph != 1) {
            addWhiteSpaces(AMOUNT_OF_WHITESPACES);
        }

        addText(text);

        switch (numberOfParagraph) {
            case 1:
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getDateField().getText();
                addTextFromTextField();
                addUnderline();
                addWhiteSpaces(16);

                addText("Патронаж м/с в ", UnderlinePatterns.NONE, 4, true, true);
                addSeveralUnderlines(6, 4);
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getPatronageField().getText();
                addText(textOnField, UnderlinePatterns.SINGLE, 4, false, true, 12);
                addSeveralUnderlines(6, 4);
                addWhiteSpace();
                addText("мес. жизни", UnderlinePatterns.NONE, 4, true, true);
                break;

            case 2:
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getComplaintsField().getSelectedItem().toString();
                addTextFromTextField();
                addSeveralUnderlines(countUnderlinesToAdd(textOnField, 52));
                break;

            case 3:
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getFeedingField().getSelectedItem().toString();
                addTextFromTextField();
                addSeveralUnderlines(countUnderlinesToAdd(textOnField, 20));

                addText("Режим");
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getModeField().getText();
                addTextFromTextField();
                addSeveralUnderlines(countUnderlinesToAdd(textOnField, 18));
                break;

            case 4:
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getConditionField().getSelectedItem().toString();
                addTextFromTextField();
                addSeveralUnderlines(countUnderlinesToAdd(textOnField, 19));

                addText("Кожа, слизистые");
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getSkinField().getSelectedItem().toString();
                addTextFromTextField();
                addSeveralUnderlines(countUnderlinesToAdd(textOnField, 14));
                break;

            case 5:
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getBehaviorField().getText();
                addTextFromTextField();
                addSeveralUnderlines(countUnderlinesToAdd(textOnField, 50));
                break;

            case 6:
                textOnField = getMainWindow().getPatronagePanel().getBlanks()[numberOfBlank].getIndexesField().getText();
                addMultilineText(textOnField);
                fillTheRestOfLinesWithUnderlines();

                lineNumber = 1;
                writtenWords = EMPTY_STRING;
                break;
        }

        clearRuns();
    }
}