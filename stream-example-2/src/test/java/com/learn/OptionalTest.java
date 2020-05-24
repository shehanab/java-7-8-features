package com.learn;

import org.junit.Test;

import java.util.OptionalDouble;

/**
 * Created by Shehan on 6/15/15.
 */
public class OptionalTest {

    @Test
    public void testOptional() throws Exception {

        OptionalDouble devide = devide(1, 2);
        System.out.println(devide);

        OptionalDouble devide1 = devide(0, 1);
        System.out.println(devide1);

        OptionalDouble devide2 = devide(1, 0);
        System.out.println(devide2);

    }

    private OptionalDouble devide(int a, int b) {
        if (b == 0) {
            return OptionalDouble.empty();
        }
        return OptionalDouble.of(a / (double) b);
    }
}
