package ru.otus.l31.collections;

import java.util.*;

/**
 * Created by tully.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Main {
    private static final int MEASURE_COUNT = 1;

    public static void main(String... args) {
        Collection<Integer> example = new LinkedList<>();
        int min = 0;
        int max = 9_999_999;
        for (int i = min; i < max + 1; i++) {
            example.add(i);
        }

        //Collections.shuffle((List<Integer>) example);
        calcTime(() -> example.contains(max));
    }

    private static void calcTime(Runnable runnable) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MEASURE_COUNT; i++)
            runnable.run();
        long finishTime = System.nanoTime();
        long timeNs = (finishTime - startTime) / MEASURE_COUNT;
        System.out.println("Time spent: " + timeNs + "ns (" + timeNs / 1_000_000 + "ms)");
    }
}
