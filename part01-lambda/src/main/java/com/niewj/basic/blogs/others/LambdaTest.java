package com.niewj.basic.blogs.others;

import java.util.*;
import java.util.function.*;

/**
 * lambda
 *
 * @see Consumer 消费型： -- 提供1/2个参数供消费，且不需要返回;
 * @see Supplier 供给型： -- 工厂方法, 获取返回值;
 * @see Function 函数型： -- 参数T, 返回R;
 * @see Predicate 断言型: -- 表示一个参数的boolean值；
 * <p>
 * Created by weijun.nie on 2017/10/25.
 */
public class LambdaTest {

    /***
     * 四大函数式接口
     */
    public static void main(String[] args) {
        // 1. 消费型：Consumer -- 提供1/2个参数供消费，且不需要返回;
        share(30, bookCount -> System.out.println("共享了" + bookCount + "本图书"));
        share(30, "图书", (bookCount, name) -> System.out.println("共享了" + name + bookCount + "本"));

        Consumer<List<Integer>> consumer = (x) -> System.out.println(x.size());
        consumer.accept(Arrays.asList(3,34,54));

        // 2.供给型：Supplier -- 工厂方法, 获取返回值;
        Supplier strUtil = () -> new String("Supplier#get");
        System.out.println(strUtil.get());
        strUtil = String::new;
        System.out.println(strUtil.get());

//        List<Integer> list = supply(10, () -> 20);
//        list.forEach(System.out::println);
//
//
//        // 3.函数型：Function<T, R> -- 参数T, 返回R;
//        Integer value = convert("28", x -> Integer.parseInt(x));
//
//        // 4. 断言型: Predicate -- 筛选出只有2个字的水果
//        List<String> fruit = Arrays.asList("香蕉", "哈密瓜", "榴莲", "火龙果", "水蜜桃");
//        List<String> newFruit = filter(fruit, (f) -> f.length() == 2);
//        System.out.println(newFruit);
//
//
//        // test FunctionalInterface
//        new FunctionalInterfaceSummary() {
//            @Override
//            public int doSum(int x, int y) {
//                return x + y;
//            }
//        };
//
//        Runnable r = () -> System.out.println("do something.");
//        FunctionalInterfaceSummary fis = (x, y) -> x + y;
    }

    // 1-1. 消费型接口示例
    public static void share(Integer count, Consumer<Integer> consumer) {
        consumer.accept(count);
    }

    // 1-2. 消费型接口示例2
    public static void share(Integer count, String name, BiConsumer<Integer, String> consumer) {
        consumer.accept(count, name);
    }

    // 2.供给型接口示例
    public static List<Integer> supply(Integer num, Supplier<Integer> supplier) {
        List<Integer> resultList = new ArrayList<Integer>();
        for (int x = 0; x < num; x++)
            resultList.add(supplier.get());
        return resultList;
    }

    // 3.函数型接口示例- 转换字符串为Integer
    public static Integer convert(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    // 4. 断言型接口示例 - 筛选出只有2个字的水果
    public static List<String> filter(List<String> fruit, Predicate<String> predicate) {
        List<String> f = new ArrayList<>();
        for (String s : fruit) {
            if (predicate.test(s)) {
                f.add(s);
            }
        }
        return f;
    }

    /**
     * @see List#sort(Comparator)
     * @see Iterable#forEach(Consumer)
     * @see Map#forEach(BiConsumer)
     * @see Map#replace(Object, Object)
     * @see Map#replaceAll(BiFunction)
     * @see Map#remove(Object, Object)
     * @see Collection#stream()
     */
    public static void testDefaultMethod() {
        // 5.1 - 比如List接口 sort方法:
        // 5.2 - Iterable接口 forEach方法
        /**  按之前做法，所有的实现类都要实现sort方法，这样的default method在接口中声明方法并提供实现,令人激动不已*/
    }
    /*
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
    */
}
