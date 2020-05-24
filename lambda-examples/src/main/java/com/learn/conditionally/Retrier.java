package com.learn.conditionally;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Shehan on 5/25/15.
 */
public class Retrier {

    private ScheduledExecutorService scheduledExecutorService;

    public void init() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void retry(Function<Integer, Status> callable, int times, long timeDelay, TimeUnit timeUnit) {
        retry(callable, times, times, timeDelay, timeUnit);
    }

    public void retry(Function<Integer, Status> callable, int times, int initialTimes, long timeDelay, TimeUnit timeUnit) {
        try {
            times--;
            Status status = callable.apply(initialTimes - times);
            if (status == Status.CONTINUE && times != 0) {
                final int finalTimes = times;
                scheduledExecutorService
                        .schedule(() -> retry(callable, finalTimes, initialTimes, timeDelay, timeUnit), timeDelay, timeUnit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum Status {
        STOP, CONTINUE
    }

    private static class Context {
        private Callable<Status> callable;
        private int times;

        public Context(Callable<Status> callable, int times) {
            this.callable = callable;
            this.times = times;
        }

        public Callable<Status> getCallable() {
            return callable;
        }

        public int getTimes() {
            return times;
        }
    }
}
