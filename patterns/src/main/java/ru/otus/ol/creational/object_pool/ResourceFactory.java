package ru.otus.ol.creational.object_pool;

/**
 * Created by tully.
 */
@FunctionalInterface
public interface ResourceFactory {
    Resource get();
}
