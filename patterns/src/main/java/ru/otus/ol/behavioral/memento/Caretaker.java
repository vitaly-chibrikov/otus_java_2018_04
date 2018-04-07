package ru.otus.ol.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 */
class Caretaker {
    private final List<Memento> savedStates = new ArrayList<Memento>();

    public void add(Memento state){
        savedStates.add(state);
    }

    public Memento get(int index){
        return savedStates.get(index);
    }
}
