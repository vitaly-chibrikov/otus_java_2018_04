package lambda;

import java.util.Optional;
import java.util.Random;

/**
 *
 */
public class OptionalExample {

    public static void main(String[] args) {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(get(rnd.nextBoolean()).orElse("default"));
        }
    }

    static Optional<String> get(boolean cond) {
        if (cond) {
            return Optional.of("hello");
        } else {
            return Optional.empty();
        }
    }
}
