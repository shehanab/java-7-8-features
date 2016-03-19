package com.learn;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Shehan on 5/17/15.
 */
public class LambdaTestHelper {

    private List<Integer> integerList;

    public LambdaTestHelper(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public void iterateAndPrint(DoToValue doToValue) {
        for (Integer integer : integerList) {
            doToValue.onValue(integer);
        }
    }

    public void iterateAndPrint2(Function<Integer, String> doToValue) {
        for (Integer integer : integerList) {
            System.out.println(doToValue.apply(integer));
        }
    }

    public interface DoToValue {
        void onValue(Integer i);
    }
}
