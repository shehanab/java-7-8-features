package com.learn.java;

import junit.framework.TestCase;

/**
 * Created by shehan on 3/16/16.
 */
public class BasicTest extends TestCase {

    public void testLiterals() throws Exception {

        long LONG = 10_000_000;
        byte b = (byte) 0b1_001_0001;

        int i =  076;
        System.out.println(LONG);
        System.out.println(b);
        System.out.println(i);

    }

    public void test8bit() throws Exception {
        assertEquals(071, 71);

    }

    public void testStringSwitch() throws Exception {

        String str = "Java";

        switch (str) {
            case "Java" :
                System.out.println("The language is java");
                break;
            case "Scala" :
                System.out.println("The language is scala");
                break;
            case "Rubi" :
                System.out.println("The language is rubi");
                break;
            default:
                System.out.println("Could not select language");
        }
    }

    public void testVarArgs() throws Exception {
//        varargMethod(1,2,3,4,5);
        Integer[] integers = {1,2,3,4,5};
        varargMethod(0, integers);

    }

    private void varargMethod(Integer a, Integer... integers) {
        System.out.println(integers);
    }

}