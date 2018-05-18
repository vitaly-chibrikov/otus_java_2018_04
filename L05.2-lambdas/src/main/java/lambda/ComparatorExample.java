package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class ComparatorExample {

    // Описание в отдельном классе
    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("AA", "BBB", "C");

        // создание инстанса
        Collections.sort(list, new MyComparator());

        // анонимный класс
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        // Lambda
        Collections.sort(list, (String s1, String s2) -> {return s1.length() - s2.length();});

        // Типы выводятся
        Collections.sort(list, (s1, s2) -> {return s1.length() - s2.length();});

        Collections.sort(list, Comparator.comparingInt(s -> s.length()));


        // Без аргументоа
        Runnable r1 = () -> System.out.println("from runnable lambda");


        int a = 10;
        Runnable r2 = () -> {
            throw new RuntimeException();
        };
    }
}
