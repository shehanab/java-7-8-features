package com.learn;

/**
 * Created by Shehan on 6/3/15.
 */
public interface CdDrive {

    static boolean isNull(Object o) {
        return o == null;
    }

    default void write(String s) {
        System.out.println("CD Drive : " + s);
    }
}
