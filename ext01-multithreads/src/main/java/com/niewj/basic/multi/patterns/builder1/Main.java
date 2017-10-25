package com.niewj.basic.multi.patterns.builder1;

/**
 * Created by weijun.nie on 2017/10/25.
 */
public class Main {

    public static void main(String[] args) {
//        Director director = new Director(new TextBuilder());
        Director director = new Director(new HtmlBuilder());
        Product result = director.construct();
        System.out.println(result.getMessage());
    }

}
