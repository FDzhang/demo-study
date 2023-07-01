package com.example.demodesignpattern.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test1 {
}

class Editor {
    private String text = "";
    private int selectionStart = 0;
    private int selectionEnd = 0;

    public void setText(String text) {
        select(0, 0);
        this.text = text;
    }

    public void select(int from, int to) {
        selectionStart = from;
        selectionEnd = to;
    }

    public String getSelectedText() {
        return text.substring(selectionStart, selectionEnd);
    }

    public EditorState getState() {
        return new EditorState(text, selectionStart, selectionEnd);
    }

    public void setState(EditorState state) {
        this.text = state.text;
        this.selectionStart = state.selectionStart;
        this.selectionEnd = state.selectionEnd;
    }

    static class EditorState {
        private final String text;
        private final int selectionStart;
        private final int selectionEnd;

        private EditorState(String text, int selectionStart, int selectionEnd) {
            this.text = text;
            this.selectionStart = selectionStart;
            this.selectionEnd = selectionEnd;
        }
    }
}

// complete implementation of this class
class Undo {
    private final Editor1 editor;
    private final Deque<Editor1.EditorState> history = new ArrayDeque<>();

    public Undo(Editor1 editor) {
        this.editor = editor;
    }

    public void takeSnapshot() {
        history.addLast(editor.getState());
    }

    public void rollback() {
        if (!history.isEmpty()) {
            this.editor.setState(history.removeLast());
        }
    }
}