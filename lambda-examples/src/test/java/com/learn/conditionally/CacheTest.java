package com.learn.conditionally;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Shehan on 5/25/15.
 */
public class CacheTest {

    private Cache<Integer, Integer> cache;

    @Before
    public void setUp() throws Exception {
        cache = new Cache<>();
    }

    @Test
    public void testCalculateFibonacci() throws Exception {
        for (int i = 0; i < 20; i++) {

            Integer value = cache.getOrCalculate(i, this::calculateNext);

            System.out.println(value);
        }
    }

    private Integer calculateNext(Integer key) {
//        System.out.println("Calculating value for index " + key);
        if (key < 2) return 1;
        return cache.get(key - 1) + cache.get(key - 2);
    }


}