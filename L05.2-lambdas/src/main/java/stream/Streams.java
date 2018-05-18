package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 */
public class Streams {

    static List<Student> students = new ArrayList<>();
    static Map<String, Student> studentMap = new HashMap<>();

    static {
        Student s1 = new Student("Alex", 22, 5, 4.5);
        Student s2 = new Student("Maria", 22, 5, 3.5);
        Student s3 = new Student("John", 12, 4, 4.7);
        Student s4 = new Student("Bob", 22, 5, 4.8);
        Student s5 = new Student("Anna", 20, 3, 4.5);
        students = Arrays.asList(s1, s2, s3, s4, s5);

        // List -> Map
        studentMap = students.stream()
                .collect(Collectors.toMap(
                        st -> st.getName(), // key mapper
                        st -> st            // value mapper
                ));
//        System.out.println(studentMap);
    }

    public static void main(String[] args) {

        getAvgMark(students);
//        sumRange(0, 10);
//        customCollect(students);
//        infStream();
//        groupBy(students);
    }


    // TODO: use filter as arg
    static double getAvgMark(List<Student> students) {
        OptionalDouble avg = students.stream()
                .filter(st -> st.getCourse() == 5)
                .mapToDouble(st -> st.getAvgMark()) // Есть специальные стримы DoubleStream/IntStream/LongStream
                .average();

        return avg.orElse(0.0);
    }

    static int sumRange(int low, int high) {
        //return IntStream.range(low, high).sum();

        /**
         * {@link IntStream#reduce(int, IntBinaryOperator)}
         */
        int sum = IntStream.range(low, high)
                .reduce(0, (v1, v2) -> v1 + v2);

//        int product = IntStream.range(low, high)
//                .reduce(1, (v1, v2) -> v1 * v2);

        System.out.println(sum);
        return sum;
    }

    // Пишем свои коллекторы
    static void customCollect(List<Student> students) {

        Collector<Student, Map<String, Student>, Map<String, Student>> c = Collector.of(
                // Initial state
                new Supplier<Map<String, Student>>() {
                    @Override
                    public Map<String, Student> get() {
                        return new HashMap<>();
                    }
                },
                // Accumulate values in state
                new BiConsumer<Map<String, Student>, Student>() {
                    @Override
                    public void accept(Map<String, Student> stringStudentMap, Student student) {
                        stringStudentMap.put(student.getName(), student);
                    }
                },
                // Combine multiple states into one (for splitted streams)
                new BinaryOperator<Map<String, Student>>() {
                    @Override
                    public Map<String, Student> apply(Map<String, Student> stringStudentMap, Map<String, Student> stringStudentMap2) {
                        stringStudentMap.putAll(stringStudentMap2);
                        return stringStudentMap;
                    }
                }
        );

        Map<String, Student> studentMap = students.stream()
                .collect(c);
        System.out.println(studentMap);


        /**
         * {@link java.util.stream.Stream#collect(Supplier, BiConsumer, BiConsumer)}
         *
         * Стадии
         * supplier - initial value
         * accumulate - store value in dst
         * combine - reducing
         *
         *
         * {@link Supplier#get() T get()}
         * {@link BiConsumer#accept(Object, Object) void accept(U, V)}
         */
        Map<String, Student> studentMap1 = students.stream()
                .collect(
                        HashMap::new, // initial
                        (map, val) -> map.put(val.getName(), val), // foreach do
                        (m1, m2) -> m1.putAll(m2) // combine sub results
                );

        System.out.println(studentMap1);
    }

    // Бесконечный стрим
    static void infStream() {
        AtomicLong val = new AtomicLong(0);
        long v = Stream.generate(() -> val.getAndIncrement())
//                .skip(100)
                .findFirst()
                .orElse(Long.MIN_VALUE);

        System.out.println(v);
    }

    static void groupBy(List<Student> students) {
        Map<Integer, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(s -> s.getCourse()));
        System.out.println(map);
    }

}
