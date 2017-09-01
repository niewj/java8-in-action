package com.niewj.basic;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MathOper addition = (int a, int b) -> a + b;
        System.out.println("Hello World!");
    }
}

interface MathOper {
    int op(int a, int b);
}