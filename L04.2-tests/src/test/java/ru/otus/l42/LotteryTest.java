package ru.otus.l42;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by tully.
 */
public class LotteryTest {

    /**
     * mock in mockito is a normal mock in other mocking frameworks
     * (allows you to stub incovations = return specific values out of method calls).
     */

    @Test
    public void mockedLottery() {
        EmailsReader emailsReader = Mockito.mock(EmailsReader.class);
        LotteryMachine lotteryMachine = Mockito.mock(LotteryMachine.class);

        String seedString = "May the Test be with you";
        when(emailsReader.get()).thenReturn(Arrays.asList("0", "1", "2", "3", "4"));
        when(lotteryMachine.setSeed(seedString)).thenReturn(lotteryMachine);

        Lottery lottery = new Lottery(emailsReader, lotteryMachine, seedString);
        lottery.run();

        Mockito.verify(emailsReader, Mockito.times(1)).get();
        Mockito.verify(lotteryMachine, Mockito.times(1)).setSeed(seedString);
        Mockito.verify(lotteryMachine, Mockito.never()).dispose();
    }

    /**
     * A spy in mockito is a partial mock in other mocking frameworks
     * (part of the object will be mocked and part will use real method invocations).
     */
    @Test
    public void mockedWithSpy() {
        EmailsReader emailsReader = Mockito.mock(EmailsReader.class);
        LotteryMachine lotteryMachine = new LotteryMachine(5);

        LotteryMachine spyMachine = Mockito.spy(lotteryMachine);

        String seedString = "Test";
        long longSeed = 42L;

        when(spyMachine.getSeed()).thenReturn(longSeed);

        Lottery lottery = new Lottery(emailsReader, spyMachine, seedString);
        lottery.run();

        Assert.assertEquals(LotteryMachine.DEFAULT_SEED, lotteryMachine.getSeed());
        Assert.assertEquals(longSeed, spyMachine.getSeed()); //not to seedString.hashCode();
    }
}
