package com.learn;

/**
 * Created by Shehan on 6/3/15.
 */
public interface DvdDrive {

    default void write(String s) {
        System.out.println("DVD Drive : " + s);
    }
}
