package com.niewj.basic.chap01;

import com.niewj.basic.common.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by weijun.nie on 2017/8/18.
 */
public class FirstDemo {

    private static final Logger logger = LoggerFactory.getLogger(FirstDemo.class);

    /**
     * jdk8之前排序
     */
    public static void traditionalSortList() {
        List<User> list = new ArrayList<User>();
        list.add(new User(32, "niewj"));
        list.add(new User(13, "maojie"));
        list.add(new User(31, "sunny"));
        list.add(new User(20, "lingxiao"));
        list.add(new User(21, "yanxin"));

        Collections.sort(list, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });

        displayAll(list); // just show
    }

    /**
     * jdk8排序
     */
    public static void jdk8SortList() {
        List<User> list = new ArrayList<User>();
        list.add(new User(32, "niewj"));
        list.add(new User(13, "maojie"));
        list.add(new User(31, "sunny"));
        list.add(new User(20, "lingxiao"));
        list.add(new User(21, "yanxin"));

//        list.sort(comparing(User::getAge)); // TODO
        displayAll(list); // just show
    }

    public static void main(String[] args) {
        traditionalSortList();

    }

    private static void displayAll(List<User> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

/**
 * 实体
 */
class User {
    private Integer age;
    private String name;

    public User() {
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
//        return "name = " + name + "; age = " + age;
        return JSONUtil.safeToJson(this);
    }
}