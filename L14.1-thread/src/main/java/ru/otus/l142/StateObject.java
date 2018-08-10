package ru.otus.l142;

/**
 * Steps of the solution.
 * <p>
 * 1. static
 * 2. volatile
 * 3. synchronized
 * 4. synchronized(object)
 * 5. synchronized(class)
 * 6. synchronized StateObject
 */

public class StateObject {
    private int i;

    public synchronized void increment() {
            i++;
    }

    public synchronized int getI() {
        return i;
    }
}
