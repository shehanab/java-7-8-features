package com.learn.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shehan on 5/27/15.
 */
public class Engine<T> {

    private List<Rule<T>> rules;

    public void execute(T t) {
        doExecute(t, new ArrayList<>());
    }

    private void doExecute(T t, List<Rule> executed) {
        rules.stream()
                .filter(Rule::isEnabled)
                .filter(rule -> !executed.contains(rule))
                .filter(rule -> rule.match(t))
                .limit(1)
                .forEach(rule -> {
                    rule.execute(t);
                    if (rule.getState() == Rule.State.RECUR) {
                        executed.add(rule);
                        doExecute(t, executed);
                    }
                });
    }

    public void setRules(List<Rule<T>> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }
}
