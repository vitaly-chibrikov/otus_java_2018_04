package ru.otus.l42;

import org.junit.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tully.
 */
public class LotteryMachineTest {

    LotteryMachine lotteryMachine;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before all LotteryMachineTest tests");
    }

    //before() is overridden in sub class
    @Before
    public void beforeLotteryMachineTest() {
    //public void before() {
        System.out.println("Before LotteryMachineTest");
        lotteryMachine = new LotteryMachine(5);
    }

    @After
    public void afterLotteryMachineTest() {
        System.out.println("After LotteryMachineTest");
        lotteryMachine.dispose();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After all LotteryMachineTest tests");
    }


    @Test(timeout = 10)
    public void oneEmail() throws InterruptedException {
        List<String> result = lotteryMachine.draw(Collections.singletonList("test@mail.ru"));
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test@mail.ru", result.get(0));
    }

    @Ignore("todo: 2323")
    @Test(timeout = 100)
    public void fiveEmails() {
        List<String> result = lotteryMachine
                .draw(Arrays.asList("0", "1", "2", "3", "4"));
        assertEquals(5, result.size());
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("2"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("4"));
    }


    @Test(expected = NullPointerException.class)
    public void NPETest() {
        List<String> result = lotteryMachine.setSeed(100500)
                .draw(null);
        Assert.assertEquals(0, result.size());
    }

}
