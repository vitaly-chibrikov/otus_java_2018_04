package ru.otus.l1;

/**
 * Created by tully.
 * <p>
 * javap -c name.class
 * javap -v name.class
 */
@SuppressWarnings("WeakerAccess")
public class BytecodeExample {
    private final String name;

    public BytecodeExample(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        String name = "Tully";
        BytecodeExample example = new BytecodeExample(name);
        long size = example.getAnswer();
        System.out.println("The answer for " + example.name + " is: " + size);
    }

    public long getAnswer() {
        int a = 1;
        int b = 5;
        int c = a << b;
        return c + 10;
    }
}
