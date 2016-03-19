package com.learn.conditionally;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shehan on 5/25/15.
 */
public class RetrierTest {

    private Retrier retrier;

    @Before
    public void setUp() throws Exception {
        retrier = new Retrier();
        retrier.init();
    }

    @Test
    public void testRetry() throws Exception {
        retrier.retry(index -> {
            System.out.println("Called at " + System.currentTimeMillis() + " " + index + " times");
            return Retrier.Status.CONTINUE;
        }, 5, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
    }
}