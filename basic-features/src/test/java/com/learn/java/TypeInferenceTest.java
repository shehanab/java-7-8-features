package com.learn.java;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeInferenceTest extends TestCase {

    private List<String> list;

    public void testTypeInferenceInConstructor() throws Exception {

        list = new ArrayList<>();

    }

    public void testTypeInferenceWithFactoryMethod() throws Exception {

        list = Collections.emptyList();

    }

    public void testTypeInferenceAsParameter() throws Exception {

        ArrayList<String> list = new ArrayList<>();
        doSomething(list);

    }

    private void doSomething(List<String> list) {
        for (String s : list) {
            System.out.println(s.length());
        }
    }
}





