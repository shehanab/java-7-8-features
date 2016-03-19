package com.learn.system;

import java.util.function.Function;

/**
 * Created by Shehan on 5/18/15.
 */
public class Session {

    private boolean allowed;
    private Function<Request, Response> execution;

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public Function<Request, Response> getExecution() {
        return execution;
    }

    public void setExecution(Function<Request, Response> execute) {
        this.execution = execute;
    }
}
