package com.learn.conditionally;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by Shehan on 5/25/15.
 */
public class Cache<K, V> {

    private Map<K, V> dataMap = new ConcurrentHashMap<>();

    public synchronized V getOrCalculate(K key, Function<K, V> generator) {
        V value = dataMap.get(key);
        if (value == null) {
            value = generator.apply(key);
            dataMap.put(key, value);
        }
        return value;
    }

    public V get(K key) {
        return dataMap.get(key);
    }

}
