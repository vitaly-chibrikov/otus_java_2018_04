package ru.otus.ol.behavioral.memento;

public class Main {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();

        Originator originator = new Originator();
        originator.set("A");
        originator.set("B");
        caretaker.add(originator.saveToMemento());
        originator.set("C");
        caretaker.add(originator.saveToMemento());
        originator.set("D");

        originator.restoreFromMemento(caretaker.get(1));
        originator.restoreFromMemento(caretaker.get(0));
    }

}
