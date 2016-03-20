package com.learn.rule;

import com.learn.rule.Engine;
import com.learn.rule.Message;
import com.learn.rule.Rule;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Shehan on 5/27/15.
 */
public class EngineTest {

    private Engine<Message> ruleEngine;

    @Before
    public void setUp() throws Exception {
        ruleEngine = new Engine<>();

        List<Rule<Message>> rules = Arrays.asList(
                Rule.recuringRule("remove 0",
                        lengthEquals(10).and(startsWith("0")),
                        trimFrontDa(1)),

                Rule.recuringRule("remove 94",
                        lengthEquals(11).and(startsWith("94")),
                        trimFrontDa(2)),

                Rule.recuringRule("add +94",
                        lengthEquals(9).and(startsWith("+94").negate()),
                        appendtoDa("+94").andThen(m -> m.setTon(0))),

                Rule.endRule("set gsm",
                        startsWith("+94").and(lengthAbove(7)),
                        setRoute("GSM")),

                Rule.endRule("set smpp",
                        lengthBelow(8),
                        setRoute("SMPP"))
        );
        ruleEngine.setRules(rules);
    }

    private Predicate<Message> startsWith(String prefix) {
        return message -> message.getDa().startsWith(prefix);
    }

    private Predicate<Message> lengthEquals(int value) {
        return message -> message.getDa().length() == value;
    }

    private Predicate<Message> lengthBelow(int value) {
        return message -> message.getDa().length() < value;
    }

    private Predicate<Message> lengthAbove(int value) {
        return message -> message.getDa().length() > value;
    }

    private Consumer<Message> appendtoDa(String prefix) {
        return message -> message.setDa(prefix + message.getDa());
    }

    private Consumer<Message> trimFrontDa(int length) {
        return message -> message.setDa(message.getDa().substring(length));
    }

    private Consumer<Message> setRoute(String route) {
        return message -> message.setRoute(route);
    }

    @Test
    public void testExecute() throws Exception {
        Message message = new Message();
        message.setDa("94771212121");
        ruleEngine.execute(message);
        System.out.println(message);
    }
}