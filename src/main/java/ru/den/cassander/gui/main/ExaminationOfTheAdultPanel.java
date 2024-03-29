package ru.den.cassander.gui.main;

import ru.den.cassander.document_creators.ExaminationCreator;
import ru.den.cassander.gui.ComplaintsDialog;
import ru.den.cassander.gui.ExaminationDialog;
import ru.den.cassander.gui.TherapyPreparationsDialog;
import ru.den.cassander.gui.UrgentCarePreparationsDialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Arrays;
import java.util.Vector;

import static java.awt.GridBagConstraints.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static ru.den.cassander.Constants.*;
import static ru.den.cassander.gui.main.ExaminationOfTheAdultPanelConstants.*;

/**
 * Created on 13.09.2015.
 * Класс, создающий GUI для раздела "Осмотр взрослого"
 */
/*
 * TODO еще баг
 * пиздец, сломался раздел "Осмотр взрослого". Сегодня 23 января. Заполняешь целиком форму, нажимаешь Создать
 * а в документе всего 3 строчки. Нашел документ от 11 января, там все норм было.
 * Upd а, нет, не совсем так. почему-то подумал, что дело может быть в том, что заполнены не все поля в форме.
 * включил в настройках пункт "проверка незаполненных полей", снова нажал "Создать", прога выдала предупреждение
 * о том, что не все поля заполнены. проверил, дозаполнил, нажал "Создать" и вуаля - документ создался целиком.
 * Вроде этот баг связан с элементами GUI, от которых зависит работа других элементов: например, если выбираешь
 * какое-то значение выпадающего списка, то при этом выборе разблокировывается и становится доступным какой-то
 * элемент GUI, причем этот второй элемент не заполнен
 *
 * В качестве иллюстрации такого поведения проги сохранил два документа: 1-ый - с неполным текстом, 2-ой - с полным
 * Лежит тут:
 * C:\Users\Public\Documents\баг
 *
 * Upd Upd Похоже, если не заполнить какое-то поле, то все, что идет после него, не будет напечатано в документе.
 * Яркий пример: не заполняем поле "Тип осмотра", заполняем остальное. В итоге документ будет пустой, так как
 * "Тип осмотра" - это первое поле.
 * */
public class ExaminationOfTheAdultPanel extends AbstractPanel {

    private static final int PREF_WIDTH_1 = 170; // предпочтительная высота выпадающих списков в box1
    private static final int PREF_HEIGHT = 25; // предпочтительная (она же стандартная) высота выпадающего списка

    // private JPanel rootPanel;
    // TODO сделать корневую панель с граничным расположением, в центр - элементы, снизу - кнопка создать документ

    // TODO !!! ЗАДАНИЕ НА ВЕРСИЮ 2.1 !!!
    // TODO сделать проверку опциональной, если она есть, то сообщение должно перечислять пустые поля,
    // TODO status localis после мочи JTextArea

    private ExaminationPanelController controller;

    private JButton createDocumentButton;

    private JLabel jointsTypeLabel;
    private JLabel systolicNoiseLabel;
    private JLabel slashLabel; // что это за метка ? с текстом "/" ?
    private JLabel bMLabel; // стул

    private JTextArea complaintsArea;
    private JTextArea anamnesisArea;

    private JComboBox skinList;
    private JComboBox lymphaticNodesList;
    private JComboBox jointsList; // суставы
    private JComboBox jointsTypeList;
    private JComboBox mouthList;
    private JComboBox tonsilsList;
    private JComboBox breathingList;
    private JComboBox ralesList;
    private JComboBox breathingAmountList;
    private JComboBox heartTonesList;
    private JComboBox accentList;
    private JComboBox diastolicNoiseList;
    private JComboBox rightHandList1;
    private JComboBox rightHandList2;
    private JComboBox leftHandList1;
    private JComboBox leftHandList2;
    private JComboBox pulseList;
    private JComboBox pulseFeaturesList;
    private JComboBox fillingList;
    private JComboBox tensionList;
    private JComboBox tongueList;
    private JComboBox stomachList;
    private JComboBox palpationList;
    private JComboBox whereList;
    private JComboBox muscleProtectionList;
    private JComboBox liverList;
    private JComboBox cmList;
    private JComboBox edgeList;
    private JComboBox bMList;
    private JComboBox constipationList;
    private JComboBox urinationList;
    private JComboBox edemasList;
    private JComboBox modeList;

    private JCheckBox accentBox;
    private JCheckBox systolicNoiseBox;
    private JCheckBox diastolicNoiseBox;

    public JCheckBox getDiastolicNoiseBox() {
        return diastolicNoiseBox;
    }

    public JCheckBox getSystolicNoiseBox() {
        return systolicNoiseBox;
    }

    public JCheckBox getTonesOnTopBox() {
        return tonesOnTopBox;
    }

    private JCheckBox tonesOnTopBox;

    private JPanel datePanel;
    private JPanel complaintsPanel;
    private JPanel anamnesisPanel;
    private JPanel skinPanel;
    private JPanel lymphaticNodesPanel;
    private JPanel jointsPanel;
    private JPanel mouthPanel;
    private JPanel tonsilsPanel;
    private JPanel lungsPanel;
    private JPanel ralesPanel;
    private JPanel breathingAmountPanel;
    private JPanel breathingPanel;
    private JPanel heartTonesPanel;
    private JPanel heartPanel;
    private JPanel accentPanel;
    private JPanel systolicNoisePanel;
    private JPanel diastolicNoisePanel;
    private JPanel commonNoisePanel;
    private JPanel tonesOnTopPanel;
    private JPanel arterialPressurePanel;
    private JPanel rightHandPanel;
    private JPanel leftHandPanel;
    private JPanel rightHandAuxiliaryPanel;
    private JPanel leftHandAuxiliaryPanel;
    private JPanel pulsePanel;
    private JPanel pulseFrequencyPanel;
    private JPanel pulseFeaturesPanel;
    private JPanel fillingPanel;
    private JPanel tensionPanel;
    private JPanel tonguePanel;
    private JPanel stomachPanel;
    private JPanel muscleProtectionPanel;
    private JPanel liverPanel;
    private JPanel bMPanel;
    private JPanel constipationPanel;
    private JPanel urinationPanel;
    private JPanel edemasPanel;
    private JPanel modePanel;

    public ExaminationOfTheAdultPanel() {
        super();

        controller = new ExaminationPanelController(); // 2 мс

        /* для отдельного класса-контроллера
        controller.setExaminationPanel(this);*/

        // создаем GUI
        createDatePanel(); // 23 мс
        createComplaintsPanel(); // 18 мс
        createAnamnesisPanel(); // 5 мс
        createSkinPanel(); // 11 мс
        createLymphaticNodesPanel(); // 8 мс
        createJointsPanel(); // 18 мс
        createMouthPanel(); // 21 мс
        createTonsilsPanel(); // 34 мс
        createBreathingPanel(); // 32 мс
        createHeartPanel(); // 18 мс
        createHeartNoisePanel(); // 13 мс
        createArterialPressurePanel(); // 28 мс
        createPulsePanel(); // 27 мс
        createCommonStomachPanel(); // 27 мс
        createTonguePanel(); // 7 мс
        createLiverPanel(); // 22 мс
        createBMPanel(); // 6 мс
        createConstipationsPanel(); // 6 мс
        createUrinationPanel(); // 6 мс
        createEdemasPanel(); // 8 мс
        createCareTypePanel(); // 6 мс
        createDiagnosisPanel(); // 2 мс
        createExaminationTypePanel(); // 6 мс
        createExaminationPanel(); // 5 мс
        createHealingPanel(); // 289 мс

        createKidneysGeneralPanel(); // 28 мс

        createPanelBox(); // 1 мс
        createTabbedPane(); // 2 мс

        addAllPanels(); // 1 мс
        createButton(); // 1 мс
    }

    public JTextArea getComplaintsArea() {
        return complaintsArea;
    }

    public JTextArea getAnamnesisArea() {
        return anamnesisArea;
    }

    // создает панель "Дата"
    private void createDatePanel() {
        var dateLabel = new JLabel(DATE);
        dateField = new JTextField(10);
        fillTheDateField();

        datePanel = createPanel(dateLabel, dateField);
    }

    // кнопка "Добавить" работает неправильно:
    // если в TextArea уже есть какой-то текст, то при нажатии кнопки "Добавить", выборе новых пунктов для добавления
    // и нажатии "Ок" новые пункты не добавляются к уже написанному в TextArea тексту, а замещают его. Исправить.

    // Панель "Жалобы"
    private JButton addComplaintsButton;
    private void createComplaintsPanel() {
        JLabel complaintsLabel = new JLabel(COMPLAINTS);
        complaintsArea = createTextArea();
        JScrollPane complaintsScrollPane = createScrollPane(complaintsArea);
        addComplaintsButton = new JButton("Добавить");
        addComplaintsButton.addActionListener(event -> controller.addComplaintsButtonClicked());
        complaintsPanel = createPanel(complaintsLabel, complaintsScrollPane, addComplaintsButton);
    }

    // создает панель "Анамнез"
    private void createAnamnesisPanel() {
        JLabel anamnesisLabel = new JLabel("Анамнез:");
        anamnesisArea = createTextArea();
        JScrollPane anamnesisScrollPane = createScrollPane(anamnesisArea);
        anamnesisPanel = createPanel(anamnesisLabel, anamnesisScrollPane);
    }

    // создает панель "Кожные покровы"
    private void createSkinPanel() {
        JLabel skinLabel = new JLabel("Кожные покровы:");
        skinList = createComboBox(SKIN_LIST_ITEMS);
        skinList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        skinPanel = createPanel(new Component[]{skinLabel, skinList});
    }

    // создает панель "Лимфатические узлы"
    private void createLymphaticNodesPanel() {
        JLabel lymphaticNodesLabel = new JLabel("Лимфатические узлы:");
        lymphaticNodesList = createComboBox(LYMPHATIC_NODES_LIST_ITEMS);
        lymphaticNodesList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        lymphaticNodesPanel = createPanel(new Component[]{lymphaticNodesLabel, lymphaticNodesList});
    }

    // создает панель "Cуставы"
    private void createJointsPanel() {
        JLabel jointsLabel = new JLabel("Состояние:");
        jointsList = createComboBox(JOINTS_LIST_ITEMS);
        jointsTypeLabel = new JLabel("Какие:");
        jointsTypeList = createComboBox(JOINTS_TYPE_LIST_ITEMS);
        setEnabledJointsTypePanel(false); // переименовать на disableJointsTypeList() ???
        jointsList.addItemListener(e -> controller.jointsListItemSelected());
        jointsPanel = createPanel(jointsLabel, jointsList, jointsTypeList, jointsTypeLabel);
    }

    // блокирует/разблокировывает панель "какие" в "суставах"
    public void setEnabledJointsTypePanel(boolean enabled) {
        jointsTypeLabel.setEnabled(enabled);
        jointsTypeList.setEnabled(enabled);
        jointsTypeList.setSelectedItem(EMPTY_STRING); // что я тут хочу сделать? может лучше юзать setSelectedIndex(-1)?
    }

    // создает панель "Зев"
    private void createMouthPanel() {
        var mouthLabel = new JLabel("Зев:");
        mouthList = createComboBox(MOUTH_LIST_ITEMS);
        mouthList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        mouthPanel = createPanel(new Component[]{mouthLabel, mouthList});
    }

    // создает панель "Миндалины"
    private void createTonsilsPanel() {
        JLabel tonsilsLabel = new JLabel("Миндалины:");
        tonsilsList = createComboBox(TONSILS_LIST_ITEMS);
        tonsilsList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        tonsilsPanel = createPanel(new Component[]{tonsilsLabel, tonsilsList});
    }

    // создает панель "Тип дыхания" ("В легких выслушивается *такое-то* дыхание")
    private void createBreathingTypePanel() {
        var lungsLabel = new JLabel("В лёгких выслушивается");
        var breathingLabel = new JLabel("дыхание");
        breathingList = createComboBox(BREATHING_TYPE_LIST_ITEMS);

        lungsPanel = createPanel(lungsLabel, breathingList, breathingLabel);
    }

    // создает панель "Хрипы"
    private void createRalesPanel() {
        var ralesLabel = new JLabel("Хрипы:");
        ralesList = createComboBox(RALES_LIST_ITEMS);

        ralesPanel = createPanel(new Component[]{ralesLabel, ralesList});
    }

    // создает панель "Число дыханий"
    private void createBreathingAmountPanel() {
        var breathingAmountLabel = new JLabel("Число дыханий");
        var breathingMinuteLabel = new JLabel("в мин.");
        breathingAmountList = createComboBox(BREATHING_AMOUNT_LIST_ITEMS);

        breathingAmountPanel = createPanel(breathingAmountLabel, breathingAmountList, breathingMinuteLabel);
    }

    // создает панель "Дыхание"
    private void createBreathingPanel() {
        createBreathingTypePanel();
        createRalesPanel();
        createBreathingAmountPanel();

        breathingPanel = createPanel(lungsPanel, ralesPanel, breathingAmountPanel);
    }

    // создает панель "Сердце"
    private void createHeartPanel() {
        createHeartTonesPanel();
        createAccentPanel();

        heartPanel = createPanel(heartTonesPanel, accentPanel);
    }

    // создает панель "Тоны сердца"
    private void createHeartTonesPanel() {
        var heartTonesLabel = new JLabel("Тоны сердца:");
        heartTonesList = createComboBox(HEART_TONES_LIST_ITEMS);

        heartTonesPanel = createPanel(new Component[]{heartTonesLabel, heartTonesList});
    }

    // создает панель "Акцент II т."
    private void createAccentPanel() {
        accentList = createComboBox(ACCENT_LIST_ITEMS);
        accentList.setEnabled(false);
        accentBox = createCheckBox("Акцент II т.");
        accentBox.setBackground(Color.GRAY);
        accentBox.addChangeListener(e -> controller.accentCheckBoxChanged());
        accentPanel = createPanel(accentBox, accentList);
    }

    // создает панель "Шумы"
    private void createHeartNoisePanel() {
        createSystolicNoisePanel();
        createDiastolicNoisePanel();
        createTonesOnTopPanel();

        commonNoisePanel = createPanel(systolicNoisePanel, diastolicNoisePanel, tonesOnTopPanel);
    }

    // создает панель "Систолические шумы"
    private void createSystolicNoisePanel() {
        systolicNoiseBox = createCheckBox("систолический:");
        systolicNoiseLabel = new JLabel("на верхушке во II межреберье справа");
        systolicNoiseLabel.setEnabled(false);
        systolicNoiseBox.addChangeListener(e -> controller.systolicNoiseCheckBoxChanged());
        systolicNoisePanel = createPanel(systolicNoiseBox, systolicNoiseLabel);
    }

    // создает панель "Диастолические шумы"
    private void createDiastolicNoisePanel() {
        diastolicNoiseBox = createCheckBox("диастолический:");
        diastolicNoiseList = createComboBox(DIASTOLIC_NOISE_LIST_ITEMS);
        diastolicNoiseList.setEnabled(false);
        diastolicNoiseBox.addChangeListener(e -> controller.diastolicNoiseCheckBoxChanged());
        diastolicNoisePanel = createPanel(diastolicNoiseBox, diastolicNoiseList);
    }

    // создает панель "III и IV тоны на верхушке"
    private void createTonesOnTopPanel() {
        tonesOnTopBox = createCheckBox("III и IV тоны на верхушке");
        tonesOnTopPanel = createPanel(tonesOnTopBox);
    }

    // создает панель "А/Д"
    private void createArterialPressurePanel() {
        createRightHandPanel();
        createLeftHandPanel();

        arterialPressurePanel = createPanel(rightHandPanel, leftHandPanel);
    }

    private void createRightHandPanel() {
        var rightHandLabel = new JLabel("на правой руке:");
        rightHandList1 = createComboBox(RIGHT_HAND_LIST1_ITEMS);
        rightHandList2 = createComboBox(RIGHT_HAND_LIST2_ITEMS);
        rightHandAuxiliaryPanel = createAuxiliaryPanel(rightHandList1, slashLabel, rightHandList2);
        rightHandPanel = createPanel(rightHandLabel, rightHandAuxiliaryPanel);
    }

    private void createLeftHandPanel() {
        var leftHandLabel = new JLabel("на левой руке:");
        leftHandList1 = createComboBox(LEFT_HAND_LIST1_ITEMS);
        leftHandList2 = createComboBox(LEFT_HAND_LIST2_ITEMS);
        leftHandAuxiliaryPanel = createAuxiliaryPanel(leftHandList1, slashLabel, leftHandList2);
        leftHandPanel = createPanel(leftHandLabel, leftHandAuxiliaryPanel);
    }

    // TODO warning
    private JPanel createAuxiliaryPanel(JComboBox list1, JLabel label, JComboBox list2) {
        var panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.GRAY);
        list1.setPreferredSize(new Dimension(60, PREF_HEIGHT));
        label = new JLabel(" / ");
        Font font = new Font("Verdana", Font.PLAIN, 20);
        label.setFont(font);
        list2.setPreferredSize(new Dimension(60, PREF_HEIGHT));

        panel.add(list1);
        panel.add(label);
        panel.add(list2);

        return panel;
    }

    private void createPulsePanel() {
        createPulseFrequencyPanel();
        createPulseFeaturesPanel();
        createFillingPanel();
        createTensionPanel();

        pulsePanel = createPanel(pulseFrequencyPanel, pulseFeaturesPanel, fillingPanel, tensionPanel);
    }

    private void createPulseFrequencyPanel() {
        var pulseLabel = new JLabel("Частота:");
        pulseList = createComboBox(PULSE_LIST_ITEMS);
        pulseList.setPreferredSize(new Dimension(60, PREF_HEIGHT));

        var minutesLabel = new JLabel("ударов в мин.");

        pulseFrequencyPanel = createPanel(pulseLabel, pulseList, minutesLabel);
    }

    private void createPulseFeaturesPanel() {
        var pulseFeaturesLabel = new JLabel("Хар-ки пульса:");
        pulseFeaturesList = createComboBox(PULSE_FEATURES_LIST_ITEMS);
        pulseFeaturesPanel = createPanel(new Component[]{pulseFeaturesLabel, pulseFeaturesList});
    }

    private void createFillingPanel() {
        var fillingLabel = new JLabel("Наполнения:");
        fillingList = createComboBox(FILLING_LIST_ITEMS);
        fillingPanel = createPanel(fillingLabel, fillingList);
    }

    private void createTensionPanel() {
        var tensionLabel = new JLabel("Напряжения:");
        tensionList = createComboBox(TENSION_LIST_ITEMS);
        tensionPanel = createPanel(tensionLabel, tensionList);
    }

    private JPanel commonStomachPanel;
    private void createCommonStomachPanel() {
        createStomachPanel();
        createPalpationPanel();
        createWherePanel();
        createMuscleProtectionPanel();

        commonStomachPanel = createPanel(stomachPanel, palpationPanel, wherePanel, muscleProtectionPanel);
    }

    private void createStomachPanel() {
        var stomachLabel = new JLabel("Состояние:");
        stomachList = createComboBox(STOMACH_LIST_ITEMS);
        stomachPanel = createPanel(stomachLabel, stomachList);
    }

    private JLabel whereLabel;
    private JPanel palpationPanel;
    private void createPalpationPanel() {
        var palpationLabel = new JLabel("при пальпации:");

        palpationList = createComboBox(PALPATION_LIST_ITEMS);
        palpationList.addItemListener(e -> controller.palpationListItemSelected());

        palpationPanel = createPanel(palpationLabel, palpationList);
    }

    private JPanel wherePanel;
    private void createWherePanel() {
        whereLabel = new JLabel("где:");
        whereList = createComboBox(WHERE_LIST_ITEMS);
        setWherePanelEnabled(false);
        wherePanel = createPanel(whereLabel, whereList);
    }

    public void setWherePanelEnabled(boolean enabled) {
        whereLabel.setEnabled(enabled);
        whereList.setEnabled(enabled);
    }

    private void createMuscleProtectionPanel() {
        var muscleProtectionLabel = new JLabel("Мышечная защита:");
        muscleProtectionList = createComboBox(MUSCLE_PROTECTION_LIST_ITEMS);
        muscleProtectionPanel = createPanel(muscleProtectionLabel, muscleProtectionList);
    }

    private void createTonguePanel() {
        var tongueLabel = new JLabel("Язык:");
        tongueList = createComboBox(TONGUE_LIST_ITEMS);

        tonguePanel = createPanel(tongueLabel, tongueList);
    }

    // печень
    private void createLiverPanel() {
        var liverLabel = new JLabel("Состояние:");
        liverList = createComboBox(LIVER_LIST_ITEMS);
        createCmPanel();
        setCmPanelEnabled(false);

        liverList.addItemListener(e -> controller.liverListItemSelected());

        createEdgePanel();

        liverPanel = createPanel(liverLabel, liverList, cmPanel, edgePanel);
    }

    public void setCmPanelEnabled(boolean enabled) {
        cmLabel.setEnabled(enabled);
        cmList.setEnabled(enabled);
    }

    private JLabel cmLabel;
    private JPanel cmPanel;
    private void createCmPanel() {
        cmPanel = new JPanel(new FlowLayout());
        cmPanel.setBackground(Color.GRAY);

        cmLabel = new JLabel("см.");
        cmList = createComboBox(CM_LIST_ITEMS);
        cmList.setPreferredSize(new Dimension(50, 25));

        cmPanel.add(cmList);
        cmPanel.add(cmLabel);
    }

    private JPanel edgePanel;
    private void createEdgePanel() {
        var edgeLabel = new JLabel("Край:");
        edgeList = createComboBox(EDGE_LIST_ITEMS);

        edgePanel = createPanel(edgeLabel, edgeList);
    }

    private static final int PREF_WIDTH2 = 120;
    private void createBMPanel() {
        bMLabel = new JLabel("Стул:");
        bMList = createComboBox(BM_LIST_ITEMS);
        bMList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        bMPanel = createPanel(bMLabel, bMList);
    }

    private void createConstipationsPanel() {
        // запоры
        var constipationLabel = new JLabel("Запоры:");
        constipationList = createComboBox(CONSTIPATION_LIST_ITEMS);
        constipationList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        constipationPanel = createPanel(constipationLabel, constipationList);
    }

    private void createUrinationPanel() {
        // мочеиспускание
        var urinationLabel = new JLabel("Мочеиспускание:");
        urinationList = createComboBox(URINATION_LIST_ITEMS);
        urinationList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        urinationPanel = createPanel(urinationLabel, urinationList);
    }

    private void createEdemasPanel() {
        // отеки
        var edemasLabel = new JLabel("Отеки:");
        edemasList = createComboBox(EDEMAS_LIST_ITEMS);
        edemasList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        edemasPanel = createPanel(edemasLabel, edemasList);
    }

    private void createCareTypePanel() {
        var modeLabel = new JLabel("Режим:");
        modeList = createComboBox(MODE_LIST_ITEMS);
        modeList.setPreferredSize(new Dimension(PREF_WIDTH2 + 50, PREF_HEIGHT));
        modePanel = createPanel(modeList);
    }


    public JTextField getDiagnosisField() {
        return diagnosisField;
    }

    // создает панель "Диагноз"
    private JTextField diagnosisField;
    private JPanel diagnosisPanel;
    private void createDiagnosisPanel() {
        JLabel diagnosisLabel = new JLabel("Диагноз:");
        diagnosisField = new JTextField(15);
        diagnosisPanel = createPanel(diagnosisLabel, diagnosisField);
    }


    public JComboBox getbMList() {
        return bMList;
    }

    public JComboBox getBreathingList() {
        return breathingList;
    }

    public JComboBox getBreathingAmountList() {
        return breathingAmountList;
    }

    public JComboBox getCmList() {
        return cmList;
    }

    public JComboBox getConstipationList() {
        return constipationList;
    }

    public JComboBox getDiastolicNoiseList() {
        return diastolicNoiseList;
    }

    public JComboBox getDietList() {
        return dietList;
    }

    public JComboBox getEdemasList() {
        return edemasList;
    }

    public JComboBox getEdgeList() {
        return edgeList;
    }

    public JComboBox getExaminationTypeList() {
        return examinationTypeList;
    }

    public JComboBox getFillingList() {
        return fillingList;
    }

    public JComboBox getHeartTonesList() {
        return heartTonesList;
    }

    public JComboBox getJointsList() {
        return jointsList;
    }

    public JComboBox getJointsTypeList() {
        return jointsTypeList;
    }

    public JComboBox getLeftHandList1() {
        return leftHandList1;
    }

    public JComboBox getLeftHandList2() {
        return leftHandList2;
    }

    public JComboBox getLiverList() {
        return liverList;
    }

    public JComboBox getLymphaticNodesList() {
        return lymphaticNodesList;
    }

    public JComboBox getModeList() {
        return modeList;
    }

    public JComboBox getMouthList() {
        return mouthList;
    }

    public JComboBox getMuscleProtectionList() {
        return muscleProtectionList;
    }

    public JComboBox getPalpationList() {
        return palpationList;
    }

    public JComboBox getPulseFeaturesList() {
        return pulseFeaturesList;
    }

    public JComboBox getPulseList() {
        return pulseList;
    }

    public JComboBox getRalesList() {
        return ralesList;
    }

    public JComboBox getRightHandList1() {
        return rightHandList1;
    }

    public JComboBox getRightHandList2() {
        return rightHandList2;
    }

    public JComboBox getSkinList() {
        return skinList;
    }

    public JComboBox getStomachList() {
        return stomachList;
    }

    public JComboBox getTensionList() {
        return tensionList;
    }

    public JComboBox getTherapyList() {
        return therapyList;
    }

    public JComboBox getTongueList() {
        return tongueList;
    }

    public JComboBox getTonsilsList() {
        return tonsilsList;
    }

    public JComboBox getUrinationList() {
        return urinationList;
    }

    public JComboBox getWhereList() {
        return whereList;
    }

    public JComboBox getAccentList() {
        return accentList;
    }

    public JComboBox getKidneysAreaList() {
        return kidneysAreaList;
    }

    public JComboBox getKidneysList() {
        return kidneysList;
    }

    public JComboBox getUrinaList() {
        return urinaList;
    }

    public JComboBox getPasternatskySymptomList() {
        return pasternatskySymptomList;
    }

    // создает панель "Тип осмотра"
    private JComboBox examinationTypeList;
    private JPanel examinationTypePanel;
    private void createExaminationTypePanel() {
        var examinationTypeLabel = new JLabel("Тип осмотра");
        examinationTypeList = createComboBox(EXAMINATION_TYPES_LIST_ITEMS);
        examinationTypePanel = createPanel(examinationTypeLabel, examinationTypeList);
    }

    public JTextArea getExaminationArea() {
        return examinationArea;
    }

    // Панель "обследование"
    private JTextArea examinationArea;
    private JPanel examinationPanel;
    private void createExaminationPanel() {
        var examinationLabel = new JLabel("Обследование");
        examinationArea = createTextArea();
        var examinationScrollPane = new JScrollPane(examinationArea);
        examinationScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        var examinationAddButton = new JButton("Добавить");
        examinationAddButton.addActionListener(event -> controller.examinationAddButtonClicked());

        examinationPanel = createPanel(examinationLabel, examinationScrollPane, examinationAddButton);
    }

    // панель "Лечение"
    private JPanel healingPanel;
    private void createHealingPanel() {
        createHealingTabbedPane();

        healingPanel = createPanel(healingTabbedPane);
        healingPanel.setBorder(new TitledBorder("Лечение"));
    }

    private JPanel preparationsPanel;
    private JLabel preparationsLabel;
    private JTextArea therapyPreparationsArea;
    private JButton preparationsButton;

    public JTextArea getTherapyPreparationsArea() {
        return therapyPreparationsArea;
    }

    private TherapyPreparationsDialog therapyPreparationsDialog = new TherapyPreparationsDialog();

    private UrgentCarePreparationsDialog urgentCarePreparationsDialog = new UrgentCarePreparationsDialog();

    public UrgentCarePreparationsDialog getUrgentCarePreparationsDialog() {
        return urgentCarePreparationsDialog;
    }

    public void setUrgentCarePreparationsDialog(UrgentCarePreparationsDialog urgentCarePreparationsDialog) {
        this.urgentCarePreparationsDialog = urgentCarePreparationsDialog;
    }

    public TherapyPreparationsDialog getTherapyPreparationsDialog() {
        return therapyPreparationsDialog;
    }

    public void setTherapyPreparationsDialog(TherapyPreparationsDialog therapyPreparationsDialog) {
        this.therapyPreparationsDialog = therapyPreparationsDialog;
    }

    // медикаментозная терапия - препараты
    private void createTherapyPreparationsPanel() {
        preparationsLabel = new JLabel("Препараты");
        therapyPreparationsArea = createTextArea();
        preparationsButton = new JButton("Добавить");
        preparationsButton.addActionListener(event -> controller.addPreparationsButtonClicked());

        preparationsPanel = createPanel(preparationsLabel, createScrollPane(therapyPreparationsArea),
                preparationsButton);
    }

    private JPanel therapyPanel;
    private JComboBox therapyList;

    private void createTherapyPanel() {
        createTherapyPreparationsPanel();

        therapyList = createComboBox(THERAPY_LIST_ITEMS);
        therapyPanel = createPanel(therapyList, preparationsPanel);
    }

    // создает панель "Диета"
    private JPanel dietPanel;
    private void createDietPanel() {
        dietList = createComboBox(DIETS_LIST_ITEMS);
        dietPanel = createPanel(dietList);
    }

    // создает панель "Лечение"
    private JTabbedPane healingTabbedPane;
    private JComboBox dietList;
    private void createHealingTabbedPane() {
        createTherapyPanel();
        createDietPanel();
        createUrgentCarePanel();

        healingTabbedPane = tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        healingTabbedPane.addTab("Режим", modePanel);
        healingTabbedPane.addTab("Диета", dietPanel);
        healingTabbedPane.addTab("Медикаментозная терапия", therapyPanel);
        healingTabbedPane.addTab("Неотложная помощь", urgentCarePanel);
    }

    private JPanel urgentCarePanel;
    private JTextArea urgentCarePreparationsArea;

    public JTextArea getUrgentCarePreparationsArea() {
        return urgentCarePreparationsArea;
    }

    // Панель "неотложная помощь"
    private void createUrgentCarePanel() {
        preparationsLabel = new JLabel("Препараты");
        urgentCarePreparationsArea = createTextArea();
        var urgentCareAddButton = new JButton("Добавить");
        urgentCareAddButton.addActionListener(event -> controller.addUrgentCarePreparationsButtonClicked());

        urgentCarePanel = createPanel(preparationsLabel, createScrollPane(urgentCarePreparationsArea),
                urgentCareAddButton);
    }

    // почки
    private JPanel kidneysGeneralPanel;
    private void createKidneysGeneralPanel() {
        createKidneysPanel();
        createKidneysAreaPanel();
        createPasternatskySymptomPanel();
        createUrinaPanel();

        kidneysGeneralPanel = createPanel(kidneysAreaPanel, pasternatskySymptomPanel, kidneysPanel, urinaPanel);
    }

    // создает панель "Область почек"
    private JComboBox kidneysAreaList;
    private JPanel kidneysAreaPanel;
    private void createKidneysAreaPanel() {
        var label = new JLabel("Область почек");
        kidneysAreaList = createComboBox(KIDNEYS_AREA_LIST_ITEMS);
        kidneysAreaPanel = createPanel(label, kidneysAreaList);
    }

    // создает панель "Симптом Пастернацкого"
    private JComboBox pasternatskySymptomList;
    private JPanel pasternatskySymptomPanel;
    private void createPasternatskySymptomPanel() {
        var label = new JLabel("Симптом Пастернацкого");
        pasternatskySymptomList = createComboBox(PASTERNATSKY_SYMPTOM_LIST_ITEMS);
        pasternatskySymptomPanel = createPanel(label, pasternatskySymptomList);
    }

    // создает панель "Почки"
    private JComboBox kidneysList;
    private JPanel kidneysPanel;
    private void createKidneysPanel() {
        var label = new JLabel("Почки");
        kidneysList = createComboBox(KIDNEYS_LIST_ITEMS);
        kidneysPanel = createPanel(label, kidneysList);
    }

    // создает панель "Моча"
    private JComboBox urinaList;
    private JPanel urinaPanel;
    private void createUrinaPanel() {
        var label = new JLabel("Моча");
        urinaList = createComboBox(URINA_LIST_ITEMS);
        urinaPanel = createPanel(label, urinaList);
    }

    // создает кнопку "Создать документ"
    private void createButton() {
        createDocumentButton = new JButton("Создать документ");
        createDocumentButton.addActionListener(e -> controller.createDocumentButtonClicked());

        add(createDocumentButton, new GridBagConstraints(2, 0, 1, 1, 0.9, 0.9, CENTER, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

    }

    // создает текстовую область
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(5, 25);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        return textArea;
    }

    // создает колесо прокрутки у переданной в качестве параметра текстовой области area
    private JScrollPane createScrollPane(JTextArea area) {
        JScrollPane pane = new JScrollPane(area);
        pane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        return pane;
    }

    // создает выпадающий список, состоящий из элементов массива words
    private JComboBox<String> createComboBox(String[] words) {
        Vector<String> vector = new Vector<>();
        vector.addAll(Arrays.asList(words));

        JComboBox<String> comboBox = new JComboBox<>(vector);
        comboBox.setSelectedIndex(-1);
        //comboBox.setEditable(true);

        return comboBox;
    }

    //Map<JPanel, Component[]> - может быть структурировать компоненты так и будет проще ?

    private JPanel createPanel(Component... components) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);

        switch (components.length) {
            case 4:
                panel.add(components[3], new GridBagConstraints(1, 0, 1, 1, 0.9, 0.9, WEST, NONE,
                        new Insets(1, 1, 1, 1), 0, 0));
            case 3:
                panel.add(components[2], new GridBagConstraints(1, 1, 1, 1, 0.9, 0.9, SOUTHWEST, NONE,
                        new Insets(1, 1, 1, 1), 0, 0));
            case 2:
                panel.add(components[1], new GridBagConstraints(0, 1, 1, 1, 0.9, 0.9, WEST, NONE,
                        new Insets(1, 1, 1, 1), 0, 0));
            case 1:
                panel.add(components[0], new GridBagConstraints(0, 0, 1, 1, 0.9, 0.9, WEST, NONE,
                        new Insets(1, 1, 1, 1), 0, 0));
                break;

            default:
                System.out.println("Допиши метод createPanel(Component[]), Ден !");

        }

        return panel;
    }

    private JPanel createPanel(JPanel... panels) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);

        for (byte i = 0; i < panels.length; i++) {
            panel.add(panels[i], new GridBagConstraints(0, i, 1, 1, 0.9, 0.9, WEST, NONE,
                    new Insets(3, 1, 3, 1), 0, 0));
        }

        return panel;
    }

    // создает панель из текстовой метки и выпадающего списка
    private JPanel createPanel(JLabel label, JComboBox list) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);

        panel.add(label, new GridBagConstraints(0, 0, 1, 1, 0.1, 0.1, WEST, NONE,
                new Insets(1, 1, 1, 1), 0, 0));
        panel.add(list, new GridBagConstraints(0, 1, 1, 1, 0.9, 0.9, WEST, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

        return panel;
    }

    private JPanel boxPanel;
    private void createPanelBox() {
        boxPanel = new JPanel(new FlowLayout());

        Box box1 = Box.createVerticalBox();
        box1.add(skinPanel);
        box1.add(lymphaticNodesPanel);
        box1.add(mouthPanel);
        box1.add(tonsilsPanel);
        box1.add(tonguePanel);

        Box box2 = Box.createVerticalBox();
        box2.add(bMPanel);
        box2.add(constipationPanel);
        box2.add(urinationPanel);
        box2.add(edemasPanel);

        boxPanel.add(box1);
        boxPanel.add(box2);
    }

    private void addAllPanels() {
        addPanel(datePanel, 0, 0, 1, 1, CENTER);
        addPanel(examinationTypePanel, 1, 0, 1, 1, CENTER);
        addPanel(complaintsPanel, 0, 1, 2, 1, CENTER); // 2
        addPanel(boxPanel, 0, 2, 2, 2, CENTER); // 2
        addPanel(anamnesisPanel, 2, 1, 2, 1, CENTER); // 2
        addPanel(tabbedPane, 2, 2, 2, 2, CENTER); // 2
        addPanel(diagnosisPanel, 0, 5, 1, 1, CENTER);
        addPanel(examinationPanel, 0, 4, 2, 1, CENTER);
        addPanel(healingPanel, 2, 4, 2, 2, CENTER); // 4
    }

    private void addPanel(JPanel panel, int gridX, int gridY, int gridWidth, int gridHeight, int anchor) {
        add(panel, new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 0.9, 0.9, anchor, NONE,
                new Insets(1, 1, 1, 1), 0, 0));
    }

    private void addPanel(JTabbedPane panel, int gridX, int gridY, int gridWidth, int gridHeight, int anchor) {
        add(panel, new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 0.9, 0.9, anchor, NONE,
                new Insets(1, 1, 1, 1), 0, 0));
    }

    // создает чекбокс с текстом label
    private JCheckBox createCheckBox(String label) {
        JCheckBox box = new JCheckBox(label);
        box.setBackground(Color.GRAY);
        return box;
    }

    // создает панель с вкладками
    private JTabbedPane tabbedPane;
    private void createTabbedPane() {
        tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.addTab("А/Д", arterialPressurePanel);
        tabbedPane.addTab("Пульс", pulsePanel);
        tabbedPane.addTab("Сердце", heartPanel);
        tabbedPane.addTab("Почки", kidneysGeneralPanel);
        tabbedPane.addTab("Шумы", commonNoisePanel);
        tabbedPane.addTab("Дыхание", breathingPanel);
        tabbedPane.addTab("Живот", commonStomachPanel);
        tabbedPane.addTab("Суставы", jointsPanel);
        tabbedPane.addTab("Печень", liverPanel);
    }

    private class ExaminationPanelController {

        // обработчик нажатия кнопки "Добавить" в области "Жалобы"
        private void addComplaintsButtonClicked() {
            ComplaintsDialog complaintsDialog = new ComplaintsDialog();
            complaintsDialog.setVisible(true);
        }

        // обработчик выбора элемента в выпадающем списке "Суставы"
        private void jointsListItemSelected() {
            if (jointsList.getSelectedItem().equals("изменены")) {
                setEnabledJointsTypePanel(true);
            } else if (jointsList.getSelectedItem().equals("не изменены") ||
                    jointsList.getSelectedIndex() == -1) {
                jointsTypeList.setSelectedIndex(-1);
                setEnabledJointsTypePanel(false); // че за ебаное название метода ???
            }
        }

        // обработчик установки/сброса чекбокса с текстом "Акцент II т."
        private void accentCheckBoxChanged() {
            if (accentBox.isSelected()) {
                accentList.setEnabled(true);
            } else {
                accentList.setEnabled(false);
                accentList.setSelectedIndex(-1);
            }
        }

        // обработчик установки/сброса чекбокса с текстом "систолический:"
        private void systolicNoiseCheckBoxChanged() {
            systolicNoiseLabel.setEnabled(systolicNoiseBox.isSelected());
        }

        // обработчик установки/сброса чекбокса с текстом "диастолический:"
        private void diastolicNoiseCheckBoxChanged() {
            if (diastolicNoiseBox.isSelected()) {
                diastolicNoiseList.setEnabled(true);
            } else {
                diastolicNoiseList.setEnabled(false);
                diastolicNoiseList.setSelectedIndex(-1);
            }

            // diastolicNoiseList.setEnabled(diastolicNoiseBox.isSelected());
        }

        // обработчик выбора элемента в выпадающем списке "при пальпации"
        private void palpationListItemSelected() {
            if (palpationList.getSelectedItem().equals("безболезненный")) {
                setWherePanelEnabled(false);
                whereList.setSelectedIndex(-1);
            } else {
                setWherePanelEnabled(true);
            }
        }

        // обработчик выбора элемента в выпадающем списке "Состояние" в панели "Печень"
        private void liverListItemSelected() {
            if (liverList.getSelectedItem().equals("выступает из подреберья на")) {
                setCmPanelEnabled(true);
            } else if (liverList.getSelectedItem().equals("не пальпируется") ||
                    liverList.getSelectedIndex() == -1) {
                cmList.setSelectedIndex(-1);
                setCmPanelEnabled(false);
            }
        }

        // обработчик нажатия кнопки "Добавить" в панели "Обследование"
        private void examinationAddButtonClicked() {
            ExaminationDialog examinationDialog = new ExaminationDialog();
            examinationDialog.setVisible(true);
        }

        // переименовать кнопку на addPreparationsButton или как-то так
        // обработчик нажатия кнопки "Добавить" в панели "Медикаментозная терапия"
        private void addPreparationsButtonClicked() {
            therapyPreparationsDialog.setVisible(true);
        }

        // здесь тоже если есть текст в TextArea, то при повторном добавлении новый текст замещает
        // имеющийся, а не добавляется к нему

        // обработчик нажатия кнопки "Добавить" в панели "Неотложная помощь"
        private void addUrgentCarePreparationsButtonClicked() {
            urgentCarePreparationsDialog.setVisible(true);
        }

        // обработчик нажатия кнопки "Создать документ"
        private void createDocumentButtonClicked() {
            ExaminationCreator creator = new ExaminationCreator();
            creator.createDocument();
        }

    }
}