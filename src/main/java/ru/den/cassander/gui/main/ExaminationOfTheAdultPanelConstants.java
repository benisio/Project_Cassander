package ru.den.cassander.gui.main;

import java.lang.reflect.Array;
import java.math.BigDecimal;

import static ru.den.cassander.Utilities.*;

/**
 * Created on 26.09.2015.
 */

// TODO забить все эти константы в XML ?
public final class ExaminationOfTheAdultPanelConstants {

    // запрещаем создавать экземпляры этого класса
    private ExaminationOfTheAdultPanelConstants() {
    }

    public static final String[] ACCENT_LIST_ITEMS = new String[] {
            "на аорте", "на легочной артерии"
    };

    public static final String[] SKIN_LIST_ITEMS = new String[] {
            "обычной окраски",
            "гиперемированные",
            "цианотичные",
            "желтушные"
    };

    public static final String[] LYMPHATIC_NODES_LIST_ITEMS = new String[] {
            "не увеличены", "увеличены"
    };

    public static final String[] JOINTS_LIST_ITEMS = new String[] {
            "не изменены", "изменены"
    };

    public static final String[] JOINTS_TYPE_LIST_ITEMS = new String[] {
            "коленные", "локтевые", "тазобедренные"
    };

    public static final String[] MOUTH_LIST_ITEMS = new String[] {
            "чистый",
            "ярко гиперемированный",
            "сочный",
            "гиперемия дужек"
    };

    public static final String[] TONSILS_LIST_ITEMS = new String[] {
            "обычные", "рыхлые", "увеличены", "уменьшены"
    };

    public static final String[] BREATHING_TYPE_LIST_ITEMS = new String[] {
            "везикулярное",
            "жесткое",
            "ослабленное везикулярное",
            "ларинго-трахеальное",
            "бронхиальное",
            "амфорическое"
    };

    public static final String[] RALES_LIST_ITEMS = new String[] {
            "немногочисленные хрипы",
            "многочисленные хрипы",
            "сухие хрипы",
            "жужжащие хрипы",
            "свистящие хрипы",
            "влажные хрипы",
            "звонкие хрипы",
            "хрипов нет"
    };

    public static final String[] HEART_TONES_LIST_ITEMS = new String[] {
            "чистые", "ясные", "приглушенные"
    };

    public static final String[] DIASTOLIC_NOISE_LIST_ITEMS = new String[] {
            "на верхушке", "в точке Боткина"
    };

    public static final String[] PULSE_FEATURES_LIST_ITEMS = new String[] {
            "ритмичный", "аритмичный", "нитевидный", "pulsus differens"
    };

    public static final String[] FILLING_LIST_ITEMS = new String[] {
            "удовлетворительного", "хорошего", "пустой"
    };

    public static final String[] TENSION_LIST_ITEMS = new String[] {
            "хорошего", "плохого", "напряжен"
    };

    public static final String[] STOMACH_LIST_ITEMS = new String[] {
            "мягкий", "вздут"
    };

    public static final String[] PALPATION_LIST_ITEMS = new String[] {
            "безболезненный",
            "разлитая болезненность",
            "ограниченная болезненность"
    };

    public static final String[] WHERE_LIST_ITEMS = new String[] {
            "в эпигастрии",
            "в средней трети живота справа",
            "в средней трети живота слева",
            "в средней трети живота по средней линии",
            "в нижней трети живота справа",
            "в нижней трети живота слева",
            "в нижней трети живота по средней линии",
            "в правом подреберье",
            "в зоне Шоффара"
    };

    public static final String[] MUSCLE_PROTECTION_LIST_ITEMS = new String[] {
            "отсутствует", "слабо выражена", "дефанс"
    };

    public static final String[] TONGUE_LIST_ITEMS = new String[] {
            "чистый", "влажный", "обложен белым налетом", "обложен желтовато-белым налетом"
    };

    public static final String[] LIVER_LIST_ITEMS = new String[] {
            "не пальпируется", "выступает из подреберья на"
    };

    public static final String[] EDGE_LIST_ITEMS = new String[] {
            "уплотненный", "безболезненный", "болезненный"
    };

    public static final String[] BM_LIST_ITEMS = new String[] {
            "нормальный", "неустойчивый"
    };

    public static final String[] CONSTIPATION_LIST_ITEMS = new String[] {
            "спастические", "атонические", "отсутствуют"
    };

    public static final String[] URINATION_LIST_ITEMS = new String[] {
            "нормальное", "болезненное", "учащенное"
    };

    public static final String[] EDEMAS_LIST_ITEMS = new String[] {
            "на стопах",
            "на голенях",
            "на бедрах",
            "на лице",
            "анасарка",
            "отсутствуют"
    };

    public static final String[] MODE_LIST_ITEMS = new String[] {
            "общий",
            "домашний",
            "амбулаторный",
            "постельный",
            "строгий постельный",
            "расширение двигательного"
    };

    public static final String[] COMPLAINTS_LIST_ITEMS = new String[] {
            "жалоб нет",
            "в связи с дефицитом сознания сбор жалоб не представляется возможным",
            "в связи с речевыми расстройствами сбор жалоб не представляется возможным",
            "в связи с тяжестью состояния сбор жалоб не представляется возможным",
            "«зябкость» в",
            "боль в верхних отделах живота",
            "боль в гипогастрии",
            "боль в груди слева",
            "боль в груди справа",
            "боль в левой подвздошной области",
            "боль в левом подреберье",
            "боль в мезогастрии",
            "боль в нижних отделах живота",
            "боль в правой подвздошной области",
            "боль в правом подреберье",
            "боль в проекции",
            "боль в средних отделах живота",
            "боль в эпигастрии",
            "боль за грудиной",
            "боль колющего характера в обл-ти",
            "боль ноющего характера",
            "боль острого характера",
            "боль периодического характера",
            "боль постоянного характера",
            "боль пульсирующего характера",
            "боль режущего характера",
            "боль схваткообразного характера",
            "дискомфорт",
            "ощущение инородного тела в области ануса",
            "зуд и жжение в заднем проходе",
            "кровотечение",
            "неоднократную рвоту",
            "общую слабость",
            "ограничение движения в",
            "однократную рвоту",
            "одышку",
            "онемение",
            "отёк",
            "отрыжку",
            "отрыжку горечью",
            "сухость во рту",
            "тошноту",
            "чувство нехватки воздуха",
            "головная боль",
            "повышение температуры",
            "озноб",
            "слабость",
            "головные боли",
            "головокружение",
            "разбитость во всем теле",
            "сухость",
            "боли и першение в горле",
            "боли в глазных яблоках",
            "слезотечение",
            "насморк",
            "сухой кашель"
    };

    public static final String[] DIETS_LIST_ITEMS = new String[] {
            "Соблюдение диеты №1", "Соблюдение диеты №2", "Соблюдение диеты №3",
            "Соблюдение диеты №4", "Соблюдение диеты №5", "Соблюдение диеты №6",
            "Соблюдение диеты №7", "Соблюдение диеты №8", "Соблюдение диеты №9",
            "Соблюдение диеты №10", "Соблюдение диеты №11", "Соблюдение диеты №12",
            "Соблюдение диеты №13", "Соблюдение диеты №14", "Соблюдение диеты №15"
    };

    public static final String[] EXAMINATION_TYPES_LIST_ITEMS = new String[] {
            "Прием", "Актив", "Вызов на дом", "Диспансерный осмотр"
    };

    public static final String[] EXAMINATION_LIST_ITEMS = new String[] {
            "обследование согласно пр. №50",
            "обследование согласно пр. № 302н",
            "Общий анализ крови с подсчетом тромбоцитов и гематокрита",
            "ан. крови на RW, ВИЧ, HBsAg, HCV",
            "Общий анализ мочи",
            "Группа крови, резус фактор",
            "МНО, АПТВ",
            "Коагулограмма",
            "Глюкоза крови",
            "Гликемический профиль",
            "Тропонины, КФК-МВ",
            "Амилаза крови",
            "КЩС",
            "Рентгенография органов грудной клетки",
            "УЗИ ОБП",
            "УЗИ простаты",
            "УЗИ почек",
            "ЭХО-КГ",
            "Холтер-мониторирование ЭКГ",
            "Суточное мониторирование артериального давления",
            "Люмбальная пункция",
            "Электрокардиография",
            "Электроэнцефалография",
            "Электронейромиография",
            "Консультация кардиолога",
            "Консультация пульмонолога",
            "Консультация эндокринолога",
            "Консультация нефролога",
            "Консультация гастроэнтеролога",
            "Консультация офтальмолога",
            "Консультация невролога",
            "Консультация ревматолога",
            "Консультация терапевта",
            "Консультация гематолога",
            "Консультация уролога",
            "Консультация ЛОР-врача",
            "Консультация ангиохирурга"
    };

    public static final String[] BIOCHEMICAL_LIST_ITEMS = new String[] {
            "холестерин", "триглицериды",
            "билирубин", "креатинин",
            "мочевина", "натрий", "калий",
            "хлор", "АлТ", "АсТ"
    };

    public static final String[] LIPIDOGRAM_LIST_ITEMS = new String[] {
            "общий холестерин",
            "холестерин ЛПНП",
            "холестерин ЛПВП",
            "триглицериды"
    };

    public static final String[] THERAPY_LIST_ITEMS = new String[] {
            "Отказ от курения",
            "Отказ от употребления алкоголя",
            convertToMultiline("Отказ от управления автомобилем\nввиду высокой опасности ДТП"),
            "Занятия лечебной физкультурой",
            convertToMultiline("Ежедневная ходьба в среднем\nтемпе на свежем воздухе"),
            convertToMultiline("Ограничение психо-эмоциональных\nстрессов")
    };

    // TODO переделать так, чтобы в коде была только одна копия этого списка
    public static final String[] THERAPY_LIST_FOR_DOCUMENT_ITEMS = new String[] {
            "Отказ от курения",
            "Отказ от употребления алкоголя",
            "Отказ от управления автомобилем ввиду высокой опасности ДТП",
            "Занятия лечебной физкультурой",
            "Ежедневная ходьба в среднем темпе на свежем воздухе",
            "Ограничение психо-эмоциональных стрессов"
    };

    public static final String[] PERIODICITY_LIST_ITEMS = new String[] {
            "1 раз в день (утром)",
            "1 раз в день (днем)",
            "1 раз в день (вечером)",
            "2 раза в день (утром и вечером)",
            "2 раза в день (утром и днём)",
            "3 раза в день",
            "4 раза в день",
            "п/к",
            "п/к 2 раза в день",
            "в/м",
            "в/м 2 раэа в день",
            "в/в струйно",
            "в/в струйно 2 раза в день",
            "в/в кап"
    };

    public static final String[] THERAPY_METHODS_LIST_ITEMS = new String[] {
            "до еды", "после еды", "во время еды", "препарат разжевывать", "препарат не разжевывать"
    };

    public static final String[] URGENT_CARE_METHODS_LIST_ITEMS = new String[] {
            "per os", "п/к", "в/м", "в/в струйно", "в/в кап", "КПВ", "ВВЛ", "ПРК"
    };

    // препараты для неотложки
    public static final String[] URGENT_CARE_PREPARATIONS_LIST_ITEMS = new String[] {
            "Acidi acetylsalicilici 0.5 g",
            "Acidi aminocapronici",
            "Acidi ascorbini 5%",
            "Adrenalini 0.1%",
            "Aethamsilati 12.5%",
            "Аminasini 2.5%",
            "Amiodaroni",
            "Analgini 50%",
            "Asametonii bromidi 5%",
            "Atropini sulphatis 0.1% ",
            "Beroteci",
            "Budesonidi",
            "Calcii chloridi 10%",
            "Capoteni 25 mg",
            "Carbo activatis 0.5",
            "Cephtriaxoni 1,0 g",
            "Cetorolaci",
            "Chloraethyli 30 ml",
            "Cianocobalamini 0.01%",
            "Ciprofloxacini",
            "Clophelini 0.01%",
            "Clopidogreli 300 mg",
            "Coffeini-natrii benzoatis 20%",
            "Cordiamini 2 мл",
            "Corglyconi 0,06%",
            "Diasepami 0.5%",
            "Dibasoli 1%",
            "Digoxini",
            "Dimedroli 1%",
            "Disoli 250 ml",
            "Dopamini 0.5%",
            "Drotaverini 2%",
            "Euphyllini 2.4%",
            "Furosemidi 1%",
            "Gelatinoli",
            "Glucosae 5%",
            "Haemodesi N",
            "Heparini-natrii",
            "Hydrocortisoni 0.05",
            "Hydrogenii peroxydi 3%",
            "Infucoli-GEA 250 ml",
            "Insulini",
            "Isoprenalini 0.5",
            "Isosorbidi dinitratis",
            "Kalii permanganatis 1.0",
            "Laevomycetini 0.25%",
            "Lidocaini 2%",
            "Liq. Ammonii caustici 10 ml",
            "Magnii sulphatis 25%",
            "Mesatoni 1%",
            "Naloxoni 0.5%",
            "Natrii chloridi 0.9%",
            "Niphedipini 10 mg",
            "Nitroglycerini 0,1%",
            "Noradrenalini 0.2%",
            "Novocainamidi 10%",
            "Novocaini 0.5%",
            "Oxytocini 5 ЕД",
            "Panagini",
            "Papaverini hydrochloridi 2%",
            "Pipolpheni 2.5%",
            "Poliglucini",
            "Prednisoloni 30 mg",
            "Propranololi",
            "Protamini sulfatis",
            "Pyridoxini hydrochloridi 5%",
            "Reamberini",
            "Reopoliglucini",
            "Salbutamoli",
            "Scoplamini 0.05%",
            "Semaxi",
            "Sol. Iodi spirituosa 10%",
            "Spiriti aethylici 70%",
            "Spiriti aethylici 96%",
            "Strophanthini-K 0.05%",
            "Suprastini 2%",
            "Tavegili",
            "Thiamini chloridi 2.5 %",
            "Trisamini 3.66%",
            "Unitioli 5%",
            "Valocordini",
            "Verapamili 0.25%",
            "Vicasoli"
    };

    public static final String[] DOSES_LIST_ITEMS = numbersToStringArray(join(
            getNumbersInRange(0.1, 0.9, 0.1),
            getNumbersInRange(1, 9, 1),
            getNumbersInRange(10, 45, 5),
            getNumbersInRange(50, 400, 50)
    ));

    // вставить в неотложку и в медикаментозное перед препаратом
    public static final String[] URGENT_CARE_PREPARATIONS_FORM_LIST_ITEMS = new String[] {
            "Sol.", "Tab."
    };

    // TODO kidneys - почки
    // область почек
    public static final String[] KIDNEYS_AREA_LIST_ITEMS = new String[] {
            "визуально не изменена",
            "пастозна",
            "гиперемирована",
            "пальпаторно безболезненна",
            "пальпаторно умеренно болезненна",
            "пальпаторно болезненна справа",
            "пальпаторно болезненна слева",
            "пальпаторно болезненна с обеих сторон"
    };

    // симптом Пастернацкого
    public static final String[] PASTERNATSKY_SYMPTOM_LIST_ITEMS  = new String[] {
            "отрицательный с обеих сторон",
            "положительный справа",
            "положительный слева",
            "положительный с обеих сторон"
    };

    // почки
    public static final String[] KIDNEYS_LIST_ITEMS = new String[] {
            "не пальпируются",
            "пальпируется увеличенная правая почка",
            "пальпируется увеличенная левая почка",
            "безболезненны при пальпации",
            "справа пальпация болезненна",
            "слева пальпация болезненна"
    };

    // urina
    public static final String[] URINA_LIST_ITEMS = new String[] {
            "светлая, б/о",
            "соломенного цвета",
            "желтого цвета",
            "желтого насыщенного цвета",
            "гематурия",
            "с солевым осадком",
            "с эритроцитами в осадке",
            "гноевидная",
            "цвета темного пива",
            "черного цвета"
    };

    public static final String[] AUXILIARY_LIST_ITEMS        = numbersToStringArray(getNumbersInRange(0,  250, 10 ));
    public static final String[] BREATHING_AMOUNT_LIST_ITEMS = numbersToStringArray(getNumbersInRange(20, 60,  2  ));
    public static final String[] PULSE_LIST_ITEMS            = numbersToStringArray(getNumbersInRange(30, 180, 4  ));
    public static final String[] CM_LIST_ITEMS               = numbersToStringArray(getNumbersInRange(1,  12,  0.5));
    public static final String[] RIGHT_HAND_LIST1_ITEMS               = numbersToStringArray(getNumbersInRange(0,  250,  10));
    public static final String[] RIGHT_HAND_LIST2_ITEMS               = numbersToStringArray(getNumbersInRange(0,  250,  10));
    public static final String[] LEFT_HAND_LIST1_ITEMS                = numbersToStringArray(getNumbersInRange(0,  250,  10));
    public static final String[] LEFT_HAND_LIST2_ITEMS                = numbersToStringArray(getNumbersInRange(0,  250,  10));

    // возвращает массив чисел в диапазоне от первого до второго, включая их, с заданным шагом
    // из-за проблем с точностью у double сложение в цикле производится с использованием BigDecimal
    private static Number[] getNumbersInRange(double minValue, double maxValue, double step) {
        int arraySize = (int) ((maxValue - minValue) / step + 1);
        Number[] numbers = new Number[arraySize];

        BigDecimal currentNumber;
        BigDecimal bigDecimalMinValue;
        BigDecimal bigDecimalStep;
        for (byte i = 0; i < numbers.length; i++) {
            bigDecimalMinValue = BigDecimal.valueOf(minValue);
            bigDecimalStep = BigDecimal.valueOf(step);

            currentNumber = bigDecimalMinValue.add(bigDecimalStep.multiply(BigDecimal.valueOf(i))); // numbers[i] = minValue + i * step;
            numbers[i] = currentNumber.doubleValue();

            Double d = (Double) numbers[i];
            if (d.intValue() == d) {
                numbers[i] = d.intValue();
            }
        }

        return numbers;
    }

    // конвертирует массив Number[] в массив String[]
    private static String[] numbersToStringArray(Number[] numbers) {
        String[] resultArray = new String[numbers.length];

        for (byte i = 0; i < resultArray.length; i++) {
            resultArray[i] = (numbers[i]).toString();
        }

        return resultArray;
    }

    // объединяет несколько массивов в один
    private static <T> T[] join(T[]... arrays) {
        int resultArrayLength = 0;

        for (T[] array : arrays) {
            resultArrayLength += array.length;
        }

        @SuppressWarnings("unchecked")
        T[] resultArray = (T[]) Array.newInstance(arrays.getClass().getComponentType().getComponentType(), resultArrayLength); // тут последним параметром задаем конечную длину массива

        int currentNumberOfElements = 0;

        for (T[] array : arrays) {
            System.arraycopy(array, 0, resultArray, currentNumberOfElements, array.length);
            currentNumberOfElements += array.length;
        }

        return resultArray;
    }
}