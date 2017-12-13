package com.niewj.basic.chap01;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 《精通lambda表达式》第一章开始
 */
public class Demo1 {

    public static void main(String[] args) {
        List<String> pointList = Arrays.asList("Hello", "Java", "Json", "dfadk");
        /* 写法1 */
//        for (String p : pointList) {
//            System.out.println(p.substring(0, 4));
//        }

        /* 写法2 */
//        for (Iterator<String> iter = pointList.iterator();
//             iter.hasNext(); ) {
//            System.out.println(iter.next().substring(0, 4));
//        }
        /* 写法3：不推荐--因为扩大了 iter 的作用域 */
        Iterator<String>  iter = pointList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().substring(0, 4));
        }
    }

}