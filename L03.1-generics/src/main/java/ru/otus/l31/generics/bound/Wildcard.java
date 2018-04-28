package ru.otus.l31.generics.bound;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "unused", "UnnecessaryLocalVariable"})
public class Wildcard {
    public static void main(String[] args) {
        new Wildcard().run();
    }

    private void run() {
        bounds();

        List<Integer> list = new ArrayList<>();
        list.add(1);

        printListOld(list);

        int sum = 0;
        for(Integer integer: list){
            sum += integer;
        }

        printList(list);

    }

    private void bounds() {
        Integer aI = 0;
        Number bI = aI;

        List<Integer> a = new ArrayList<>();
        List<? extends Number> b = a;
    }

    private void printList(List<?> list) {
        list.forEach(System.out::println);
        //list.add(new Object()); // error
    }

    private void printListOld(List list) {
        list.forEach(System.out::println);
        list.add(new Object()); // ok?
        list.add(2);
    }
}
