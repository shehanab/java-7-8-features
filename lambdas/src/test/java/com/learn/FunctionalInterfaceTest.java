package com.learn;

import org.junit.Test;

import java.util.function.*;

/**
 * Created by Shehan on 5/18/15.
 */
public class FunctionalInterfaceTest {

    @Test
    public void testFunctionComposition() throws Exception {

        Function<Integer, String> convert = String::valueOf;
        Function<String, String> addColon = s -> s + ":";

        Function<Integer, String> function = convert.andThen(addColon);
        System.out.println(function.apply(1));

        Function<Integer, String> composed = addColon.compose(convert);
        System.out.println(composed.apply(1));
    }

    @Test
    public void testTypes() throws Exception {
        Supplier<String> supplier = () -> "";
        Function<String, String> function = (s) -> s;
        Consumer<String> consumer = (s) -> {
        };
        Predicate<String> predicate = s -> Boolean.FALSE;
    }

    @Test
    public void testBiFunctionComposition() throws Exception {
        BiFunction<Integer, Integer, Integer> biFunction = Math::addExact;
        Function<Integer, String> toString = String::valueOf;
        System.out.println(biFunction.andThen(toString).apply(1, 2));

    }

    @Test
    public void testConsumer() throws Exception {
        Consumer<String> consumer = System.out::println;
        Consumer<String> consumer1 = s -> System.out.println(s + ",");
        consumer.andThen(consumer1);
    }
}
