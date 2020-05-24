package com.learn.system;

import java.util.function.Consumer;

/**
 * Created by Shehan on 5/18/15.
 */
public class TPSController {

    public boolean isAllowed(Request request) {
        boolean allowed = request.getRequestId() % 2 == 0;
        System.out.println("Request is " + (allowed ? "" : "Not ") + "Allowed to continue");
        return allowed;
    }

    public void doIfAllowed(Request request, Consumer<Request> next) {
        boolean allowed = isAllowed(request);
        request.getSession().setAllowed(allowed);
        next.accept(request);
    }

}
