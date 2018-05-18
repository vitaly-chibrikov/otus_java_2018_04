package stream;

import java.util.stream.Stream;

/**
 *
 */
public class Order {

    public static void main(String[] args) {
        lazy();
//        filtermap();
    }

    static void lazy() {

        // lazy
        Stream.of("a1", "a2", "b1", "b2", "c1")
                .filter(s -> {
                    System.out.println("filtering: " + s);
                    return true;
                });

        // with terminal
        // TODO: vertical pipeline anyMatch()
//        Stream.of("a1", "a2", "b1", "b2", "c1")
//                .filter(s -> {
//                    System.out.println("filtering: " + s);
//                    return true;
//                })
//                .forEach(s -> System.out.println("for: " + s));
    }

    static void filtermap() {
        Stream.of("a1", "b1", "a2", "b2", "c1")
//                .sorted((o1, o2) -> {
//                    System.out.println("sort");
//                    return o1.compareTo(o2);
//                })

                .map(e -> {
                    System.out.println("map: " + e); return e;
                })

                .filter(e -> {
                    System.out.println("filter: " + e); return e.startsWith("a");
                })

                .forEach(s -> System.out.println("for: " + s));
    }
}
