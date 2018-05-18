package stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 */
public class Parallel {

    public static void main(String[] args) throws Exception {
        parallel();
//        pool();
    }

    // Using commonPool()
    // -Djava.util.concurrent.ForkJoinPool.common.parallelism=n
    // Caller policy
    static void parallel() {
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");
        System.out.println(String.format("parallel() on %d threads. Available CPU: %d\n",
                ForkJoinPool.commonPool().getParallelism(), Runtime.getRuntime().availableProcessors()));

        int result = IntStream.range(0, 5)
                .parallel()
                .peek(it -> System.out.printf("Thread [%s] peek: %d\n", Thread.currentThread().getName(), it))
                .sum();
        System.out.println("sum: " + result);
    }

    static void pool() throws Exception {
        System.out.println("pool()");
        ForkJoinPool fjPool = new ForkJoinPool(2);
        fjPool.submit(() -> {
            int result = IntStream.range(0, 5)
                    .parallel()
                    .peek(it -> System.out.printf("Thread [%s] peek: %d\n", Thread.currentThread().getName(), it))
                    .sum();
            System.out.println("sum: " + result);
        });
        fjPool.awaitTermination(1, TimeUnit.SECONDS);
    }

}
