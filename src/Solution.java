import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MICROS;

public class Solution {
    /** Ниже приведены 3 строки, на которых будут тестироваться имеющиеся решения
     * (решения описаны в классе Tested_methods) */

    static String controlString1 = "In studies conducted across a range of industries, Christine has found that people who" +
            " experience a state of thriving are healthier, more resilient, and more able to focus on their work. When" +
            " people feel even an inkling of thriving, it tends to buffer them from distractions, stress, and negativity. " +
            "In a study of six organizations across six different industries, employees characterized as highly thriving " +
            "demonstrated 1.2 times less burnout compared with their peers. They were also 52% more confident in themselves " +
            "and their ability to take control of a situation. They were far less likely to have negativity drag them into " +
            "distraction or self-doubt.";
    static String controlString2 = "В российском законодательстве не предусмотрен штраф за отсутствие зимних шин. Но за использование " +
            "чрезмерно изношенных, то есть с глубиной протектора менее 4 мм, а также за установку на одну ось шипованных и нешипованных " +
            "покрышек, разных размеров, моделей может применяться ответственность по ч. 2 ст. 12.5 КоАП «Управление транспортным средством " +
            "при наличии неисправностей или условий, при которых эксплуатация транспортных средств запрещена» (500 рублей)";
    static String controlString3 = "some line 3";


    // это для того, чтобы переключать стандартный поток вывода
    // чтобы результаты 100000 вызовов каждого метода не падали в консоль
    static PrintStream out = System.out;
    static PrintStream newOut = new PrintStream(new ByteArrayOutputStream());


    /** Данный метод является входом в проект - именно его нужно запускать */
    public static void main(String[] args) throws Exception {
        // проведение тестов и получение отчетов в виде StringBuilder
        StringBuilder sb1 = test("method1", "Вариант №1, через составление карты", 100);
        StringBuilder sb2 = test("method2", "Вариант №2, через метод replaceAll", 100);
        StringBuilder sb3 = test("method3", "Вариант №3, через indexOf() и lastIndexOf()", 100);
        StringBuilder sb4 = test("method4", "Вариант №4, через RegEx", 100);
        StringBuilder sb5 = test("method5", "Вариант №5, через Stream API", 100);
        StringBuilder sb = sb1.append(sb2).append(sb3).append(sb4).append(sb5);

        // возвращение вывода на консоль
        System.setOut(out);

        // печать результатов в виде итоговой таблицы
        printResultTable(sb);
    }

    /** Данный метод проводит тест указанного метода */
    // метод получает на вход следующие параметры:
    //      methodName  -  имя метода в классе Tested_methods, который нужно протестировать
    //      name  -  условное имя метода, которое будет использовано при формировании отчета
    //      it  -  количество вызовов данного метода, которое нужно сделать.
    //             т.е. засекаем время, вызываем метод methodName it раз, отмечаем время еще раз,
    //             находим сколько ушло времени на it вызовов и потом находим среднее время одного вызова
    // метод возвращает:
    //      отчет в микросекундах, сколько в среднем метод работает с каждой из тестовых строк
    //      в виде StringBuilder (это сделано, чтобы потом сформировать таблицу результатов)
    public static StringBuilder test (String methodName, String name, int it) throws Exception {
        // переназначаем вывод, чтобы не захломлять консоль выводом 10000 итераций
        System.setOut(newOut);

        // вспомогательные переменные
        LocalTime startTime, endTime;
        long requiredTime;

        // получение нужного метода
        Method method = Tested_methods.class.getMethod("method1", String.class);
        method.setAccessible(true);

        String description = name + ". Отрабатывает в среднем за:";
        StringBuilder sb = new StringBuilder(String.format("| %-71s |", description));

        startTime = LocalTime.now();
        for (int i = 0; i < it; i++) {
            method.invoke(new Tested_methods(), controlString1);
        }
        endTime = LocalTime.now();
        requiredTime = MICROS.between(startTime, endTime);
        sb.append(String.format(" %7d |", requiredTime/it).toString());

        startTime = LocalTime.now();
        for (int i = 0; i < it; i++) {
            method.invoke(new Tested_methods(), controlString2);;
        }
        endTime = LocalTime.now();
        requiredTime = MICROS.between(startTime, endTime);
        sb.append(String.format(" %7d |", requiredTime/it));

        startTime = LocalTime.now();
        for (int i = 0; i < it; i++) {
            method.invoke(new Tested_methods(), controlString3);
        }
        endTime = LocalTime.now();
        requiredTime = MICROS.between(startTime, endTime);
        sb.append(String.format(" %7.3f |", requiredTime/1.0/it) + "\n");

        // выведем на консоль результаты работы каждого метода по одному разу,
        // чтобы знать, что методы действительно отрабатывают и какой результат они выдают
        System.setOut(out);
        System.out.printf("\n\nРезультат работы метода %s для строки 1:\n", name);
        method.invoke(new Tested_methods(), controlString1);
        System.out.printf("\n\nРезультат работы метода %s для строки 2:\n", name);
        method.invoke(new Tested_methods(), controlString2);
        System.out.printf("\n\nРезультат работы метода %s для строки 3:\n", name);
        method.invoke(new Tested_methods(), controlString3);
        System.setOut(newOut);

        return sb;
    }


    // метод распечатки результатов в виде таблицы
    public static void printResultTable(StringBuilder sb) {
        sb.deleteCharAt(sb.length()-1);   // убираем лишний перенос строки

        // формируем бордюры таблицы
        char[] border = new char[105];  // большой бордюр
        Arrays.fill(border, '-');
        border[0] = border[border.length - 1] = '+';
        String split = "+-----------------------------+";   // малый бордюр

        // Формируем шапку таблицы
//        String argStr = "| Cтрока1 | Cтрока2 | Cтрока3 |";
        String argStr = "| Длин en | Длин ru | Корт ru |";
        String cap = String.format("| %1$-71s | %2$-27s |\n| %3$72s%4$s\n|%3$73s%5$s",
                "Тестируемый метод:", "Аргументы тестирования", "", split, argStr);

        // Печать таблицы результатов
        System.out.println("\nСводная таблица по результатам работы методов\n" +
                "\tЧисло на пересечении тесвовой строки и варианта решения показывает,\n" +
                "\tсколько микросекунд в среднем работает данный вариант решения с данной строкой");
        System.out.println(String.valueOf(border));      // печать верхнего бордюра
        System.out.println(cap);
        System.out.println(String.valueOf(border));             // бордюр после шапки
        System.out.println(sb.toString());
        System.out.println(String.valueOf(border));             // печать нижнего бордюра
    }
}
