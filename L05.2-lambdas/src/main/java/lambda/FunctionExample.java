package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Изобретаем свои лямбды
 */
public class FunctionExample {


    // Integer -> Double
    static class SquareRoot implements Function<Integer, Double> {
        @Override
        public Double apply(Integer val) {
            return Math.sqrt(val);
        }
    }

    static Double mySqrt(Integer val) {
        return Math.sqrt(val);
    }

    // Трансформация каждого элемента
    static <T, R> Collection<R> map(Collection<T> src, Function<T, R> op) {
        List<R> r = new ArrayList<>();
        for (T t : src) {
            r.add(op.apply(t));
        }
        return r;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 8, 16, 25);
        System.out.println(map(list, new SquareRoot()));

        System.out.println(map(list, e -> Math.sqrt(e)));

        System.out.println(map(list, (Function<Integer, Double>) Math::sqrt));


        Function<Integer, Double> func1 = v -> mySqrt(v);
        // mySqrt() is Integer -> Double so it can be referenced as Function<Double, Integer>
        Function<Integer, Double> func2 = FunctionExample::mySqrt;
    }

}
