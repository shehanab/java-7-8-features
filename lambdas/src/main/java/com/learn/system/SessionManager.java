package com.learn.system;

import java.util.function.Consumer;

/**
 * Created by Shehan on 5/18/15.
 */
public class SessionManager {

    public Session getSession(Request request) {
        System.out.println("Creating session for request " + request);
        return new Session();
    }

    public void withSession(Request request, Consumer<Request> next) {
        Session session = getSession(request);
        request.setSession(session);
        next.accept(request);
    }
}
