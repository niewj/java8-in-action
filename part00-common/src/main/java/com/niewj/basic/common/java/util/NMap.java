package com.niewj.basic.common.java.util;

import java.util.Collection;
import java.util.Set;

/**
 * Created by weijun.nie on 2017/10/19.
 */
public interface NMap<K, V> {

    // == Query Operations

    int size(); // 1. size大小
    boolean isEmpty(); // 是否内空
    V get(K key); // 获取指定key的Value
    void put(K key, V value);
    Set<K> keySet(); // 获取所有的K的集合
    Collection<V> values(); // 获取所有的Value的集合
    boolean containsKey(K key); // 遍历是否包含指定的key
    boolean containsValue(V value); // 遍历是否包含指定值
    void putAll(NMap<K, V> map);

    // == Modification Operations
}
