package ru.otus.l42;

/**
 * Created by tully.
 */
public class Main {
    private static final int MAX_WINNERS_COUNT = 2;

    public static void main(String[] args) {
        String pathToFile;
        if (args.length >= 1) {
            pathToFile = args[0];
        } else {
            pathToFile = "emails.csv";
        }

        String seedString = "May the Force be with you";

        new Lottery(
                new EmailsReader(pathToFile),
                new LotteryMachine(MAX_WINNERS_COUNT),
                seedString
        ).run();
    }
}
