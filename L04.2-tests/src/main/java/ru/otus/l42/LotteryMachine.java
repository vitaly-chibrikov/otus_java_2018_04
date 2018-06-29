package ru.otus.l42;

import java.util.*;

/**
 * Created by tully.
 */
class LotteryMachine {
    final static long DEFAULT_SEED = 0;

    private final int winnersCount;
    private long seed = DEFAULT_SEED;

    LotteryMachine(int winnersCount) {
        this.winnersCount = winnersCount;
    }

    List<String> draw(List<String> emails) {
        System.out.println("Draw for the seed: " + getSeed());
        Random rnd = new Random(getSeed());
        Set<String> winners = new HashSet<>();
        while (winners.size() < Math.min(winnersCount, emails.size())) {
            int index = rnd.nextInt(emails.size());
            System.out.println("Ball: " + index);
            winners.add(emails.get(index));
        }
        return new ArrayList<>(winners);
    }

    LotteryMachine setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    LotteryMachine setSeed(String str) {
        this.seed = str.hashCode();
        return this;
    }

    long getSeed() {
        return seed;
    }

    void dispose() {
        System.out.println("Disposed");
    }
}
