package ru.den.cassander.document_creators;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.den.cassander.settings.Settings;
import ru.den.cassander.settings.XMLSettings;
import ru.den.cassander.settings.XMLSettingsRW;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 06.12.2015.
 *
 * @author Denis Vereshchagin
 */
public abstract class AbstractDocumentCreator implements DocumentCreator {

    protected static final String DOT = ". ";
    protected static final String COMMA = ", ";
    protected static final String SLASH = "/";

    // имя файла и путь до него
    Settings settings = XMLSettingsRW.getSettings();
    protected final String DESTINATION_FILE_NAME;
    protected String DESTINATION_FILE_DIRECTORY = settings.getCurrentDirectoryPath();
    public final String DESTINATION_FILE_PATH;

    // выходной документ
    protected File wordDocument;
    protected XWPFDocument outputDocx;

    // ссылки на текущий абзац и ран
    protected XWPFParagraph currentParagraph;
    protected XWPFRun currentRun;

    // списки и счетчики для записи предыдущих абзацев и ранов
    protected List<XWPFRun> runs = new LinkedList<>();
    protected List<XWPFParagraph> paragraphs = new LinkedList<>();
    protected static short runCounter = 0;
    protected static byte paragraphCounter = 0;

    protected final int DEFAULT_FONT_SIZE;

    protected static final String WHITESPACE = " ";


    protected AbstractDocumentCreator(String name, int defaultFontSize) {
        DESTINATION_FILE_NAME = name;
        DESTINATION_FILE_PATH = DESTINATION_FILE_DIRECTORY + DESTINATION_FILE_NAME;

        wordDocument = new File(DESTINATION_FILE_PATH);
        outputDocx = new XWPFDocument();

        DEFAULT_FONT_SIZE = defaultFontSize;
    }

    @Override
    public abstract void createDocument();

    /**
     * Получает строку с текущими датой и временем в заданном формате.
     *
     * @return String с датой и временем.
     */
    protected static String getCurrentDateAndTime() {
        SimpleDateFormat dateAndTimeFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
        return dateAndTimeFormat.format(new Date());
    }

    /**
     * Создает новый пустой абзац в выходной документе и обновляет currentParagraph.
     */
    protected void addParagraph() {
        paragraphs.add(outputDocx.createParagraph());
        currentParagraph = paragraphs.get(paragraphCounter++);
    }

    /**
     * Создает новый пустой ран в текущем абзаце, находящемся в currentParagraph, и присваивает его переменной currentRun.
     */
    protected void addRun() {
        runs.add(currentParagraph.createRun());
        currentRun = runs.get(runCounter++);
    }

    protected abstract void setStyles(XWPFRun run, UnderlinePatterns pattern, int pos, boolean isBold, boolean isItalic, int fontSize);

    // добавляет текст (размер шрифта - по умолчанию)
    protected void addText(String text) {
        addText(text, UnderlinePatterns.NONE, 0, false, false, DEFAULT_FONT_SIZE);
    }

    // добавляет текст (жирный)
    protected void addBoldText(String text) {
        addText(text, UnderlinePatterns.NONE, 0, true, false, DEFAULT_FONT_SIZE);
    }

    // добавляет текст (жирный, размер шрифта уточнен)
    protected void addBoldText(String text, int fontSize) {
        addText(text, UnderlinePatterns.NONE, 0, true, false, fontSize);
    }

    // добавляет текст (подчеркивание, смещение по высоте, размер шрифта уточнен)
    protected void addUnderlinedText(String text) {
        addText(text, UnderlinePatterns.SINGLE, -2, false, false, DEFAULT_FONT_SIZE);
    }

    // добавляет текст (подчеркивание, смещение по высоте, размер шрифта уточнен)
    protected void addText(String text, UnderlinePatterns pattern, int pos, int fontSize) {
        addText(text, pattern, pos, false, false, fontSize);
    }

    // добавляет текст (подчеркивание, смещение по высоте, жирный, курсив)
    protected void addText(String text, UnderlinePatterns pattern, int pos, boolean isBold, boolean isItalic) {
        addText(text, pattern, pos, isBold, isItalic, DEFAULT_FONT_SIZE);
    }

    // добавляет текст (подчеркивание, смещение по высоте, жирный, курсив, размер шрифта уточнен)
    protected void addText(String text, UnderlinePatterns pattern, int pos, boolean isBold, boolean isItalic, int fontSize) {
        addRun();
        currentRun.setText(text);
        setStyles(currentRun, pattern, pos, isBold, isItalic, fontSize);
    }

    // добавляет пробел
    protected void addWhiteSpace() {
        addText(WHITESPACE);
    }

    // добавляет несколько пробелов
    protected void addWhiteSpaces(int amount) {
        for (byte i = 0; i < amount; i++) {
            addWhiteSpace();
        }
    }

    protected void clearRuns() {
        runs.clear();
        runCounter = 0;
    }

    protected void clearRunsAndParagraphs() {
        clearRuns();

        paragraphs.clear();
        paragraphCounter = 0;
    }

    protected void addComma() {
        addText(COMMA);
    }

    protected void addDot() {
        addText(DOT);
    }

    protected void addSlash() {
        addText(SLASH);
    }
}