package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Shehan on 5/18/15.
 */
public class LambdaMapTest {

    private HashMap<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
        map.put("Hello world", 9);
    }

    @Test
    public void testCompute() throws Exception {
        map.computeIfAbsent("Test", String::length);
        System.out.println(map);

        map.computeIfPresent("Hello world", (k, v) -> k.length());
        System.out.println(map);

        map.compute("Hello", (k, v) -> (v == null) ? k.length() : v);
    }
}
