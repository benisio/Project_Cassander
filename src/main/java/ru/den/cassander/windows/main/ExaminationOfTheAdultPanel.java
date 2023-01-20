package ru.den.cassander.windows.main;

import ru.den.cassander.document_creators.ExaminationCreator;
import ru.den.cassander.windows.ComplaintsDialog;
import ru.den.cassander.windows.ExaminationDialog;
import ru.den.cassander.windows.TherapyPreparationsDialog;
import ru.den.cassander.windows.UrgentCarePreparationsDialog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import static java.awt.GridBagConstraints.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static ru.den.cassander.Constants.*;
import static ru.den.cassander.windows.main.ExaminationOfTheAdultPanelConstants.*;

/**
 * Created on 13.09.2015.
 *
 * @author Denis Vereshchagin
 */
public class ExaminationOfTheAdultPanel extends AbstractPanel {

    private static final int PREF_WIDTH_1 = 170; // предпочтительная высота выпадающих списков в box1
    private static final int PREF_HEIGHT = 25; // предпочтительная (она же стандартная) высота выпадающего списка

    // private JPanel rootPanel;
    // TODO сделать корневую панель с граничным расположением, в центр - элементы, снизу - кнопка создать документ

    // TODO !!! ЗАДАНИЕ НА ВЕРСИЮ 2.1 !!!
    // TODO сделать проверку опциональной, если она есть, то сообщение должно перечислять пустые поля,
    // TODO status localis после мочи JTextArea

    private JButton createDocumentButton;

    private JLabel dateLabel;
    private JLabel complaintsLabel;
    private JLabel anamnesisLabel;
    private JLabel skinLabel;
    private JLabel lymphaticNodesLabel;
    private JLabel jointsLabel;
    private JLabel jointsTypeLabel;
    private JLabel mouthLabel;
    private JLabel tonsilsLabel;
    private JLabel lungsLabel;
    private JLabel breathingLabel;
    private JLabel ralesLabel;
    private JLabel breathingNumberLabel;
    private JLabel breathingMinuteLabel;
    private JLabel heartTonesLabel;
    private JLabel systolicNoiseLabel;
    private JLabel rightHandLabel;
    private JLabel leftHandLabel;
    private JLabel slashLabel;
    private JLabel millimetresLabel;
    private JLabel pulseLabel;
    private JLabel minutesLabel;
    private JLabel pulseFeaturesLabel;
    private JLabel fillingLabel;
    private JLabel tensionLabel;
    private JLabel tongueLabel;
    private JLabel stomachLabel;
    private JLabel palpationLabel;
    private JLabel muscleProtectionLabel;
    private JLabel liverLabel;
    private JLabel edgeLabel;
    private JLabel bMLabel; // стул
    private JLabel constipationLabel; // запоры
    private JLabel urinationLabel; // мочеиспускание
    private JLabel edemasLabel; // отеки
    private JLabel modeLabel;

    //private JTextField dateField;

    private JTextArea complaintsArea;
    private JTextArea anamnesisArea;

    private JScrollPane complaintsScrollPane;
    private JScrollPane anamnesisScrollPane;

    private JComboBox skinList;
    private JComboBox lymphaticNodesList;
    private JComboBox jointsList;
    private JComboBox jointsTypeList;
    private JComboBox mouthList;
    private JComboBox tonsilsList;
    private JComboBox breathingList;
    private JComboBox ralesList;
    private JComboBox breathingNumberList;
    private JComboBox heartTonesList;
    private JComboBox accentList;
    private JComboBox systolicNoiseList;
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
    private JPanel breathingPanel;
    private JPanel commonBreathingPanel;
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

        createDatePanel();
        createComplaintsPanel();
        createAnamnesisPanel();
        createSkinPanel();
        createLymphaticNodesPanel();
        createJointsPanel();
        createMouthPanel();
        createTonsilsPanel();
        createCommonBreathingPanel();
        createHeartPanel();
        createCommonNoisePanel();
        createArterialPressurePanel();
        createPulsePanel();
        createCommonStomachPanel();
        createTonguePanel();
        createLiverPanel();
        createBMPanel();
        createConstipationsPanel();
        createUrinationPanel();
        createEdemasPanel();
        createModePanel();
        createDiagnosisPanel();
        createExaminationTypePanel();
        createExaminationPanel();
        createHealingPanel();

        createKidneysGeneralPanel();

        createPanelBox();
        createTabbedPane();

        addAllPanels();
        createButton();
    }

    public JTextArea getComplaintsArea() {
        return complaintsArea;
    }

    public JTextArea getAnamnesisArea() {
        return anamnesisArea;
    }

    private void createDatePanel() {
        dateLabel = new JLabel(DATE);
        dateField = new JTextField(10);
        fillTheDateField();

        datePanel = createPanel(dateLabel, dateField);
    }

    private JButton addComplaintsButton;
    private void createComplaintsPanel() {
        complaintsLabel = new JLabel(COMPLAINTS);
        complaintsArea = createTextArea();
        complaintsScrollPane = createScrollPane(complaintsArea);
        addComplaintsButton = new JButton("Добавить");
        addComplaintsButton.addActionListener(event -> {
            ComplaintsDialog complaintsDialog = new ComplaintsDialog();
            complaintsDialog.setVisible(true);
        });
        complaintsPanel = createPanel(complaintsLabel, complaintsScrollPane, addComplaintsButton);
    }

    private void createAnamnesisPanel() {
        anamnesisLabel = new JLabel("Анамнез:");
        anamnesisArea = createTextArea();
        anamnesisScrollPane = createScrollPane(anamnesisArea);
        anamnesisPanel = createPanel(anamnesisLabel, anamnesisScrollPane);
    }

    private void createSkinPanel() {
        skinLabel = new JLabel("Кожные покровы:");
        skinList = createComboBox(SKIN_LIST_ITEMS);
        skinList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        skinPanel = createPanel(new Component[]{skinLabel, skinList});
    }

    private void createLymphaticNodesPanel() {
        lymphaticNodesLabel = new JLabel("Лимфатические узлы:");
        lymphaticNodesList = createComboBox(LYMPHATIC_NODES_LIST_ITEMS);
        lymphaticNodesList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        lymphaticNodesPanel = createPanel(new Component[]{lymphaticNodesLabel, lymphaticNodesList});
    }

    private void createJointsPanel() {
        jointsLabel = new JLabel("Состояние:");
        jointsList = createComboBox(JOINTS_LIST_ITEMS);
        jointsTypeLabel = new JLabel("Какие:");
        jointsTypeList = createComboBox(JOINTS_TYPE_LIST_ITEMS);
        setEnabledJointsType(false);

        jointsList.addItemListener(e -> {
            if (jointsList.getSelectedItem().equals("изменены")) {
                setEnabledJointsType(true);
            } else if (jointsList.getSelectedItem().equals("не изменены") ||
                    jointsList.getSelectedIndex() == -1) {
                setEnabledJointsType(false);
            }
        });

        jointsPanel = createPanel(jointsLabel, jointsList, jointsTypeList, jointsTypeLabel);
    }

    private void setEnabledJointsType(boolean enabled) {
        jointsTypeLabel.setEnabled(enabled);
        jointsTypeList.setEnabled(enabled);
        jointsTypeList.setSelectedItem(EMPTY_STRING);
    }

    private void createMouthPanel() {
        mouthLabel = new JLabel("Зев:");
        mouthList = createComboBox(MOUTH_LIST_ITEMS);
        mouthList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        mouthPanel = createPanel(new Component[]{mouthLabel, mouthList});
    }

    private void createTonsilsPanel() {
        tonsilsLabel = new JLabel("Миндалины:");
        tonsilsList = createComboBox(TONSILS_LIST_ITEMS);
        tonsilsList.setPreferredSize(new Dimension(PREF_WIDTH_1, PREF_HEIGHT));
        tonsilsPanel = createPanel(new Component[]{tonsilsLabel, tonsilsList});
    }

    private void createLungsPanel() {
        lungsLabel = new JLabel("В лёгких выслушивается");
        breathingLabel = new JLabel("дыхание");
        breathingList = createComboBox(BREATHING_LIST_ITEMS);

        lungsPanel = createPanel(lungsLabel, breathingList, breathingLabel);
    }

    private void createRalesPanel() {
        ralesLabel = new JLabel("Хрипы:");
        ralesList = createComboBox(RALES_LIST_ITEMS);

        ralesPanel = createPanel(new Component[]{ralesLabel, ralesList});
    }

    private void createBreathingPanel() {
        breathingNumberList = createComboBox(BREATHING_NUMBER_LIST_ITEMS);

        breathingNumberLabel = new JLabel("Число дыханий");
        breathingMinuteLabel = new JLabel("в мин.");
        breathingPanel = createPanel(breathingNumberLabel, breathingNumberList, breathingMinuteLabel);
    }

    private void createCommonBreathingPanel() {
        createLungsPanel();
        createRalesPanel();
        createBreathingPanel();

        commonBreathingPanel = createPanel(lungsPanel, ralesPanel, breathingPanel);
        //commonBreathingPanel.setBorder(new TitledBorder("Дыхание"));
    }

    private void createHeartPanel() {
        createTonesPanel();
        createAccentPanel();

        heartPanel = createPanel(heartTonesPanel, accentPanel);
        //heartPanel.setBorder(new TitledBorder("Сердце"));
    }

    private void createTonesPanel() {
        heartTonesLabel = new JLabel("Тоны сердца:");
        heartTonesList = createComboBox(HEART_TONES_LIST_ITEMS);

        heartTonesPanel = createPanel(new Component[]{heartTonesLabel, heartTonesList});
    }

    private void createAccentPanel() {
        accentList = createComboBox(ACCENT_LIST_ITEMS);
        accentList.setEnabled(false);
        accentBox = createCheckBox("Акцент II т.");
        accentBox.setBackground(Color.GRAY);

        accentBox.addChangeListener(e -> {
            if (accentBox.isSelected()) {
                accentList.setEnabled(true);
            } else {
                accentList.setEnabled(false);
            }
        });

        accentPanel = createPanel(accentBox, accentList);
    }

    private void createCommonNoisePanel() {
        createSystolicNoisePanel();
        createDiastolicNoisePanel();
        createTonesOnTopPanel();

        commonNoisePanel = createPanel(systolicNoisePanel, diastolicNoisePanel, tonesOnTopPanel);
        //commonNoisePanel.setBorder(new TitledBorder("Шумы"));
    }

    private void createSystolicNoisePanel() {
        systolicNoiseBox = createCheckBox("систолический:");
        systolicNoiseLabel = new JLabel("на верхушке во II межреберье справа");
        systolicNoiseLabel.setEnabled(false);

        systolicNoiseBox.addChangeListener(e -> {
            if (systolicNoiseBox.isSelected()) {
                systolicNoiseLabel.setEnabled(true);
            } else {
                systolicNoiseLabel.setEnabled(false);
            }
        });

        systolicNoisePanel = createPanel(systolicNoiseBox, systolicNoiseLabel);
    }

    private void createDiastolicNoisePanel() {
        diastolicNoiseBox = createCheckBox("диастолический:");
        diastolicNoiseList = createComboBox(DIASTOLIC_NOISE_LIST_ITEMS);
        diastolicNoiseList.setEnabled(false);

        diastolicNoiseBox.addChangeListener(e -> {
            if (diastolicNoiseBox.isSelected()) {
                diastolicNoiseList.setEnabled(true);
            } else {
                diastolicNoiseList.setEnabled(false);
            }
        });

        diastolicNoisePanel = createPanel(diastolicNoiseBox, diastolicNoiseList);
    }

    private void createTonesOnTopPanel() {
        tonesOnTopBox = createCheckBox("III и IV  тоны на верхушке");
        tonesOnTopPanel = createPanel(tonesOnTopBox);
    }

    private void createArterialPressurePanel() {
        createRightHandPanel();
        createLeftHandPanel();

        arterialPressurePanel = createPanel(rightHandPanel, leftHandPanel);
        //arterialPressurePanel.setBorder(new TitledBorder("АД"));
    }

    private void createRightHandPanel() {
        rightHandLabel = new JLabel("на правой руке:");
        rightHandList1 = createComboBox(RIGHT_HAND_LIST1_ITEMS);
        rightHandList2 = createComboBox(RIGHT_HAND_LIST2_ITEMS);
        rightHandAuxiliaryPanel = createAuxiliaryPanel(rightHandList1, slashLabel, rightHandList2);
        rightHandPanel = createPanel(rightHandLabel, rightHandAuxiliaryPanel);
    }

    private void createLeftHandPanel() {
        leftHandLabel = new JLabel("на левой руке:");
        leftHandList1 = createComboBox(LEFT_HAND_LIST1_ITEMS);
        leftHandList2 = createComboBox(LEFT_HAND_LIST2_ITEMS);
        leftHandAuxiliaryPanel = createAuxiliaryPanel(leftHandList1, slashLabel, leftHandList2);
        leftHandPanel = createPanel(leftHandLabel, leftHandAuxiliaryPanel);
    }

    // TODO warning
    private JPanel createAuxiliaryPanel(JComboBox list1, JLabel label, JComboBox list2) {

        JPanel panel = new JPanel(new FlowLayout());
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
        //pulsePanel.setBorder(new TitledBorder("Пульс"));
    }

    private void createPulseFrequencyPanel() {
        pulseLabel = new JLabel("Частота:");
        pulseList = createComboBox(PULSE_LIST_ITEMS);
        pulseList.setPreferredSize(new Dimension(60, PREF_HEIGHT));

        minutesLabel = new JLabel("ударов в мин.");

        pulseFrequencyPanel = createPanel(pulseLabel, pulseList, minutesLabel);
    }

    private void createPulseFeaturesPanel() {
        pulseFeaturesLabel = new JLabel("Хар-ки пульса:");
        pulseFeaturesList = createComboBox(PULSE_FEATURES_LIST_ITEMS);
        pulseFeaturesPanel = createPanel(new Component[]{pulseFeaturesLabel, pulseFeaturesList});
    }

    private void createFillingPanel() {
        fillingLabel = new JLabel("Наполнения:");
        fillingList = createComboBox(FILLING_LIST_ITEMS);
        fillingPanel = createPanel(fillingLabel, fillingList);
    }

    private void createTensionPanel() {
        tensionLabel = new JLabel("Напряжения:");
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
        //commonStomachPanel.setBorder(new TitledBorder("Живот"));
    }

    private void createStomachPanel() {
        stomachLabel = new JLabel("Состояние:");
        stomachList = createComboBox(STOMACH_LIST_ITEMS);
        stomachPanel = createPanel(stomachLabel, stomachList);
    }

    private JLabel whereLabel;
    private JPanel palpationPanel;
    private void createPalpationPanel() {
        palpationLabel = new JLabel("при пальпации:");

        palpationList = createComboBox(PALPATION_LIST_ITEMS);
        palpationList.addItemListener(e -> {
            if (palpationList.getSelectedItem().equals("безболезненный")) {
                setWherePanelEnabled(false);
                whereList.setSelectedIndex(-1);
            } else {
                setWherePanelEnabled(true);
            }
        });

        palpationPanel = createPanel(palpationLabel, palpationList);
    }

    private JPanel wherePanel;
    private void createWherePanel() {
        whereLabel = new JLabel("где:");
        whereList = createComboBox(WHERE_LIST_ITEMS);
        setWherePanelEnabled(false);
        wherePanel = createPanel(whereLabel, whereList);
    }

    private void setWherePanelEnabled(boolean enabled) {
        whereLabel.setEnabled(enabled);
        whereList.setEnabled(enabled);
    }

    private void createMuscleProtectionPanel() {
        muscleProtectionLabel = new JLabel("Мышечная защита:");
        muscleProtectionList = createComboBox(MUSCLE_PROTECTION_LIST_ITEMS);
        muscleProtectionPanel = createPanel(muscleProtectionLabel, muscleProtectionList);
    }

    private void createTonguePanel() {
        tongueLabel = new JLabel("Язык:");
        tongueList = createComboBox(TONGUE_LIST_ITEMS);

        tonguePanel = createPanel(tongueLabel, tongueList);
    }

    private void createLiverPanel() {
        liverLabel = new JLabel("Состояние:");
        liverList = createComboBox(LIVER_LIST_ITEMS);
        createCmPanel();
        setCmPanelEnabled(false);

        liverList.addItemListener(e -> {
            if (liverList.getSelectedItem().equals("выступает из подреберья на")) {
                setCmPanelEnabled(true);
            } else if (liverList.getSelectedItem().equals("не пальпируется") ||
                    liverList.getSelectedIndex() == -1) {
                cmList.setSelectedIndex(-1);
                setCmPanelEnabled(false);
            }
        });

        createEdgePanel();

        liverPanel = createPanel(liverLabel, liverList, cmPanel, edgePanel);
    }

    private void setCmPanelEnabled(boolean enabled) {
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
        edgeLabel = new JLabel("Край:");
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
        constipationLabel = new JLabel("Запоры:");
        constipationList = createComboBox(CONSTIPATION_LIST_ITEMS);
        constipationList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        constipationPanel = createPanel(constipationLabel, constipationList);
    }

    private void createUrinationPanel() {
        urinationLabel = new JLabel("Мочеиспускание:");
        urinationList = createComboBox(URINATION_LIST_ITEMS);
        urinationList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        urinationPanel = createPanel(urinationLabel, urinationList);
    }

    private void createEdemasPanel() {
        edemasLabel = new JLabel("Отеки:");
        edemasList = createComboBox(EDEMAS_LIST_ITEMS);
        edemasList.setPreferredSize(new Dimension(PREF_WIDTH2, PREF_HEIGHT));
        edemasPanel = createPanel(edemasLabel, edemasList);
    }

    private void createModePanel() {
        modeLabel = new JLabel("Режим:");
        modeList = createComboBox(MODE_LIST_ITEMS);
        modeList.setPreferredSize(new Dimension(PREF_WIDTH2 + 50, PREF_HEIGHT));
        modePanel = createPanel(modeList);
    }

    private JTextField diagnosisField;
    private JPanel diagnosisPanel;

    public JTextField getDiagnosisField() {
        return diagnosisField;
    }

    private void createDiagnosisPanel() {
        JLabel diagnosisLabel = new JLabel("Диагноз:");
        diagnosisField = new JTextField(15);
        diagnosisPanel = createPanel(diagnosisLabel, diagnosisField);
    }

    private JLabel examinationTypeLabel;

    public JComboBox getbMList() {
        return bMList;
    }

    public JComboBox getBreathingList() {
        return breathingList;
    }

    public JComboBox getBreathingNumberList() {
        return breathingNumberList;
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

    public JComboBox getMethodsList() {
        return methodsList;
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

    public JComboBox getPeriodicityList() {
        return periodicityList;
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

    public JComboBox getSystolicNoiseList() {
        return systolicNoiseList;
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

    private JComboBox examinationTypeList;
    private JPanel examinationTypePanel;

    private void createExaminationTypePanel() {
        examinationTypeLabel = new JLabel("Тип осмотра");
        examinationTypeList = createComboBox(EXAMINATION_TYPES_LIST_ITEMS);
        examinationTypePanel = createPanel(examinationTypeLabel, examinationTypeList);
    }

    private JLabel examinationLabel;
    private JTextArea examinationArea;
    private JScrollPane examinationScrollPane;
    private JButton examinationButton;
    private JPanel examinationPanel;

    public JTextArea getExaminationArea() {
        return examinationArea;
    }

    private void createExaminationPanel() {
        examinationLabel = new JLabel("Обследование");
        examinationArea = createTextArea();
        examinationScrollPane = new JScrollPane(examinationArea);
        examinationScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        examinationButton = new JButton("Добавить");
        examinationButton.addActionListener(event -> (new ExaminationDialog()).setVisible(true));
        examinationPanel = createPanel(examinationLabel, examinationScrollPane, examinationButton);
    }

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

    private void createTherapyPreparationsPanel() {
        preparationsLabel = new JLabel("Препараты");
        therapyPreparationsArea = createTextArea();
        preparationsButton = new JButton("Добавить");
        preparationsButton.addActionListener(event -> therapyPreparationsDialog.setVisible(true));

        preparationsPanel = createPanel(preparationsLabel, createScrollPane(therapyPreparationsArea),
                preparationsButton);
    }

    private JPanel therapyPanel;
    private JComboBox therapyList;
    private JComboBox periodicityList;
    private JComboBox methodsList;

    private void createTherapyPanel() {
        createTherapyPreparationsPanel();

        therapyList = createComboBox(THERAPY_LIST_ITEMS);
        therapyPanel = createPanel(therapyList, preparationsPanel);
    }

    private JPanel dietPanel;
    private void createDietPanel() {
        dietList = createComboBox(DIETS_LIST_ITEMS);
        dietPanel = createPanel(dietList);
    }

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

    private JButton urgentCareButton;
    private void createUrgentCarePanel() {
        preparationsLabel = new JLabel("Препараты");
        urgentCarePreparationsArea = createTextArea();
        urgentCareButton = new JButton("Добавить");
        urgentCareButton.addActionListener(event -> urgentCarePreparationsDialog.setVisible(true));

        urgentCarePanel = createPanel(preparationsLabel, createScrollPane(urgentCarePreparationsArea),
                urgentCareButton);
    }

    private ExaminationCreator creator;
    public ExaminationCreator getCreator() {
        return creator;
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
        JLabel label = new JLabel("Область почек");
        kidneysAreaList = createComboBox(KIDNEYS_AREA_LIST_ITEMS);
        kidneysAreaPanel = createPanel(label, kidneysAreaList);
    }

    // создает панель "Симптом Пастернацкого"
    private JComboBox pasternatskySymptomList;
    private JPanel pasternatskySymptomPanel;
    private void createPasternatskySymptomPanel() {
        JLabel label = new JLabel("Симптом Пастернацкого");
        pasternatskySymptomList = createComboBox(PASTERNATSKY_SYMPTOM_LIST_ITEMS);
        pasternatskySymptomPanel = createPanel(label, pasternatskySymptomList);
    }

    // создает панель "Почки"
    private JComboBox kidneysList;
    private JPanel kidneysPanel;
    private void createKidneysPanel() {
        JLabel label = new JLabel("Почки");
        kidneysList = createComboBox(KIDNEYS_LIST_ITEMS);
        kidneysPanel = createPanel(label, kidneysList);
    }

    // создает панель "Моча"
    private JComboBox urinaList;
    private JPanel urinaPanel;
    private void createUrinaPanel() {
        JLabel label = new JLabel("Моча");
        urinaList = createComboBox(URINA_LIST_ITEMS);
        urinaPanel = createPanel(label, urinaList);
    }

    private void createButton() {
        createDocumentButton = new JButton("Создать документ");
        createDocumentButton.addActionListener(e -> {
            creator = new ExaminationCreator();
            creator.createDocument();
        });
        add(createDocumentButton, new GridBagConstraints(2, 0, 1, 1, 0.9, 0.9, CENTER, NONE,
                new Insets(1, 1, 1, 1), 0, 0));

    }

    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(5, 25);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        return textArea;
    }

    private JScrollPane createScrollPane(JTextArea area) {
        JScrollPane pane = new JScrollPane(area);
        pane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        return pane;
    }

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
        //box2.add(modePanel);

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

    private JCheckBox createCheckBox(String label) {
        JCheckBox box = new JCheckBox(label);
        box.setBackground(Color.GRAY);
        return box;
    }

    private JTabbedPane tabbedPane;
    private void createTabbedPane() {
        tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.addTab("АД", arterialPressurePanel);
        tabbedPane.addTab("Пульс", pulsePanel);
        tabbedPane.addTab("Сердце", heartPanel);
        tabbedPane.addTab("Почки", kidneysGeneralPanel);
        tabbedPane.addTab("Шумы", commonNoisePanel);
        tabbedPane.addTab("Дыхание", commonBreathingPanel);
        tabbedPane.addTab("Живот", commonStomachPanel);
        tabbedPane.addTab("Суставы", jointsPanel);
        tabbedPane.addTab("Печень", liverPanel);
    }
}