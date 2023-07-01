package com.example.demodesignpattern.memento;

import java.util.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Test3 {
}



class Originator<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public void printValue() {
        System.out.println(value);
    }

    public Memento<T> getMemento() {
        return new Memento<>(value);
    }

    public void setMemento(Memento<T> memento) {
        this.value = memento.value;
    }

    static class Memento<T> {
        private final T value;

        private Memento(T value) {
            this.value = value;
        }
    }
}

class Caretaker<T> {
    private final Originator<T> originator;
    private final Deque<Originator.Memento<T>> undoStack = new ArrayDeque<>();
    private final Deque<Originator.Memento<T>> redoStack = new ArrayDeque<>();

    Caretaker(Originator<T> originator) {
        this.originator = originator;
    }

    public void beforeValueChanged() {
        undoStack.addLast(originator.getMemento());
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.addLast(originator.getMemento());
            this.originator.setMemento(undoStack.removeLast());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.addLast(originator.getMemento());
            this.originator.setMemento(redoStack.removeLast());
        }
    }
}

class Main1 {
    public static void main(String[] args) {
        Originator<String> editor = new Originator<>();
        Caretaker<String> history = new Caretaker<>(editor);

        history.beforeValueChanged();
        editor.setValue("1");

        history.beforeValueChanged();
        editor.setValue("2");

        history.beforeValueChanged();
        editor.setValue("3");

        editor.printValue();


        history.undo();
        editor.printValue();

        history.undo();
        editor.printValue();

        history.undo();
        editor.printValue();
    }
}
