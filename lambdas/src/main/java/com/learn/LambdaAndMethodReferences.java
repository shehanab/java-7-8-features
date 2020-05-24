package com.learn;

import java.util.stream.Stream;

/**
 * Created by Shehan on 6/12/15.
 */
public class LambdaAndMethodReferences {

    private Stream<String> stream = Stream.of("");

    private Stream<Integer> withoudMethodRef() {
        return stream.map(a -> Integer.valueOf(a));
    }

    private Stream<Integer> WithMethodRef() {
        return stream.map(this::toInt);
    }

    private Integer toInt(String str) {
        return Integer.parseInt(str);
    }
}
