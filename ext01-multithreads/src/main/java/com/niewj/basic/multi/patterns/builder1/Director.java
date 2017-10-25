package com.niewj.basic.multi.patterns.builder1;

/**
 * Created by weijun.nie on 2017/10/19.
 */
public class Director {
    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Product construct(){
        builder.buildTitle("title-1");
        builder.buildContent("this is message content");
        return builder.getProduct();
    }
}
