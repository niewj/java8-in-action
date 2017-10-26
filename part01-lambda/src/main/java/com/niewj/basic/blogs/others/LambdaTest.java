package com.niewj.basic.blogs.others;

import java.util.*;
import java.util.function.*;

/**
 * lambda
 * Created by weijun.nie on 2017/10/25.
 */
public class LambdaTest {

    public static void main(String[] args) {
//        int sum = (x, y) -> x + y;
        // 1. 消费型接口示例
        donation(1000, money -> System.out.println("好心的麦乐迪为Blade捐赠了" + money + "元"));

        // 2.供给型接口示例
        List<Integer> list = supply(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);

        // 3.函数型接口示例- 转换字符串为Integer
        Integer value = convert("28", x -> Integer.parseInt(x));

        // 4. 断言型接口示例 - 筛选出只有2个字的水果
        List<String> fruit = Arrays.asList("香蕉", "哈密瓜", "榴莲", "火龙果", "水蜜桃");
        List<String> newFruit = filter(fruit, (f) -> f.length() == 2);
        System.out.println(newFruit);


        // test FunctionalInterface
        new FunctionalInterfaceSummary(){
            @Override
            public int doSum(int x, int y) {
                return x+y;
            }
        };

        Runnable r = () -> System.out.println("do something.");
        FunctionalInterfaceSummary fis = (x, y) -> x+y;
    }

    // 1. 消费型接口示例
    public static void donation(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
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
     *
     * @see Map#forEach(BiConsumer)
     * @see Map#replace(Object, Object)
     * @see Map#replaceAll(BiFunction)
     * @see Map#remove(Object, Object)
     *
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
