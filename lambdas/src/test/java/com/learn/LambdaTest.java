package com.learn;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Shehan on 5/17/15.
 */
public class LambdaTest {
    private LambdaTestHelper helper;

    @Before
    public void setUp() throws Exception {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        helper = new LambdaTestHelper(integers);
    }

    @Test
    public void testAnonymousClass() throws Exception {
        helper.iterateAndPrint(new LambdaTestHelper.DoToValue() {
            @Override
            public void onValue(Integer i) {
                System.out.println(i);
            }
        });
    }

    @Test
    public void testLambda() throws Exception {
        helper.iterateAndPrint((Integer i) -> {
            System.out.println(i);
        });

        helper.iterateAndPrint(System.out::println);
    }

    @Test
    public void testPrintPrint() throws Exception {
        Consumer<Object> consumer = System.out::println;
        System.out.println(consumer.getClass().getName());
    }

    @Test
    public void testRef() throws Exception {
        Comparator<Integer> integerComparator = Math::max;
        String s = "Hello";
        Function<Integer, Character> function = s::charAt;

        Consumer<Integer> printValue = this::printValue;
    }



    @Test
    public void testMethodReference() throws Exception {
        helper.iterateAndPrint2(Object::toString);
    }

    @Test
    public void testMethodReference2() throws Exception {
        helper.iterateAndPrint(this::printValue);
    }

    private void printValue(Integer integer) {
        System.out.println(integer);
    }

    @Test
    public void testCons() throws Exception {

        BiFunction<Long, String, Holder> f = Holder::new;
    }

    private class Holder {
        private Long id;
        private String value;

        public Holder(Long id, String value) {
            this.id = id;
            this.value = value;
        }
    }
}

//ContainingClass::staticMethodName
//containingObject::instanceMethodName
//ContainingType::methodName
//ClassName::new