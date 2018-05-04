package ru.otus.l32;

import com.google.common.collect.Collections2;

import java.util.*;

/**
 * Created by tully.
 */

// CollectionHelper
// Collections
// Arrays
// Array
// Collections2
// Lists
// Sets
// CollectionUtils

public class Main {
    private static final int MEASURE_COUNT = 100;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static void main(String... args) {
        Collection<Integer> example = new ArrayList<>();
        int min = 0;
        int max = 9_999_999;
        for (int i = min; i < max + 1; i++) {
            example.add(i);
        }

        //calcTime(() ->Collections.shuffle((List<Integer>)example));
        //Collections.frequency(example, min);
        //Collections.min(example);
        //Collections.binarySearch((List<Integer>) example, min);
        //Collections.rotate((List<Integer>)example,10);
        //Collections.reverse((List<Integer>)example);
        //boolean a = example.contains(max);
        Set<Integer> set = new HashSet<>(example);
        calcTime(() -> {

            boolean a = set.contains(max);
            //System.out.println(a);
        });
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
