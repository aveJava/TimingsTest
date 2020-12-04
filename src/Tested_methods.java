import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.regex.Pattern;

/** В данном классе размещаются методы, для которых нужно протестировать время выполнения
 *  Все 5 написанных здесь методов - это варианты решения задачи с нахождением неповторяющихся букв
 *  Кроме них здесь ничего нет*/

public class Tested_methods {
    // через составление карты типа:   буква : кол-во повторений
    public static void method1(String input){
        HashMap<Character,Integer> counts = new HashMap<>();
        for (int index=0; index < input.length(); index++) {
            Character ch = input.charAt(index);
            if (counts.containsKey(ch)) {
                counts.put(ch, counts.get(ch) + 1);
            }
            else {
                counts.put(ch, 1);
            }
        }
        counts.forEach((ch, cnt) -> {
            if (cnt == 1) System.out.println(ch);
        });
    }

    // через метод replaceAll (если после удаления из строки всех включений такой-то
    // буквы строка становится короче на 1, то такая буква там была одна)
    public static void method2(String line){
        String c,s=line;
        int l=s.length();
        while(l>0){
            c=s.substring(0, 1);
            s=s.replaceAll(c,"");
            if((l-s.length())==1)System.out.println(c);
            l=s.length();
        }
    }

    // через сравнение результатов indexOf() и lastIndexOf()
    public static void method3(String s){
        for (char c : s.toCharArray()) {
            if (s.indexOf(c) == s.lastIndexOf(c)) System.out.println(c);
        }
    }

    // через RegEx
    public static void method4(String line){
        for (char c : line.toCharArray()) {
            if (!Character.isAlphabetic(c)) continue;
            if (Pattern.compile("^[^" + c + "]*" + c + "[^" + c + "]*$").matcher(line).find()) System.out.println(c);
        }
    }

    // через Stream API
    public static void method5(String s){
        s.chars()
                .distinct()
                .filter(x -> s.indexOf(x) == s.lastIndexOf(x))
                .forEach(x -> System.out.println((char) x));
    }


    // сюда можно добавить еще методы для тестирования

    public static void method6(String line){

    }

    public static void method7(String line){

    }
}
