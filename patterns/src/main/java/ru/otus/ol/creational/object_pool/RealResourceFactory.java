package ru.otus.ol.creational.object_pool;

public class RealResourceFactory implements ResourceFactory {
    @Override
    public Resource get() {
        return new RealResource();
    }
}
