package com.learn.rule;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Shehan on 5/27/15.
 */
public class Rule<T> {

    public enum State {
        END, RECUR
    }

    private String name;
    private boolean enabled = true;
    private Predicate<T> matcher;
    private Consumer<T> consumer;
    private State state;

    private Rule(String name, Predicate<T> matcher, Consumer<T> consumer, State state) {
        this.name = name;
        this.matcher = matcher;
        this.consumer = consumer;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public State getState() {
        return state;
    }

    public boolean match(T message) {
        return matcher.test(message);
    }

    public void execute(T message) {
        consumer.accept(message);
    }

    public static <T> Rule recuringRule(String name,
                                    Predicate<T> matcher,
                                    Consumer<T> consumer) {

        return new Rule<>(name, matcher, consumer, State.RECUR);
    }

    public static <T> Rule endRule(String name,
                               Predicate<T> matcher,
                               Consumer<T> consumer) {
        return new Rule<>(name, matcher, consumer, State.END);
    }
}
