package com.niewj.basic.blogs.others;

/**
 * Created by niewj on 2017/10/26.
 */
@FunctionalInterface
public interface FunctionalInterfaceSummary {

    int doSum(int x, int y);

    String toString();

    boolean equals(Object obj);

    default void display() {
        System.out.println("show sth..");
    }
}