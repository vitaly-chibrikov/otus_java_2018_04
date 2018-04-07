package ru.otus.ol.creational.object_pool;

public class RealResource implements Resource {
    private final long creationTime;

    public RealResource() {
        creationTime = System.nanoTime();
    }

    @Override
    public void print() {
        System.out.println(creationTime);
    }

    @Override
    public void close() {

    }
}
