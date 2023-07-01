package cn.fd.hyperswing;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Cat {
    private String name;
    private String color;
    private int size;

    public Cat(String name, String color, int size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public class Bow {
        String color;
        int size;

        public Bow(String color, int size) {
            this.color = color;
            this.size = size;
        }

        public void printColor() {
            System.out.print("Cat " + name + " is " + color + ". ");
            System.out.print("The size of " + name + " is " + size + ". ");
            System.out.print("Cat " + name + " has " + this.color + " bow. ");
            System.out.print("The size of bow is " + this.size + ".");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Deque<Character> deque = new ArrayDeque<>();
        char[] chars = input.toCharArray();
        for (char ch : chars) {
            switch (ch) {
                case '[':
                case '{':
                case '(':
                    deque.addLast(ch);
                    break;
                case ')':
                    if (!deque.isEmpty() && deque.getLast() == '(') {
                        deque.removeLast();
                    } else {
                        System.out.println(false);
                        return;
                    }
                    break;
                case ']':
                    if (!deque.isEmpty() && deque.getLast() == '[') {
                        deque.removeLast();
                    } else {
                        System.out.println(false);
                        return;
                    }
                    break;
                case '}':
                    if (!deque.isEmpty() && deque.getLast() == '{') {
                        deque.removeLast();
                    } else {
                        System.out.println(false);
                        return;
                    }
                    break;
            }
        }
        if (deque.isEmpty()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
