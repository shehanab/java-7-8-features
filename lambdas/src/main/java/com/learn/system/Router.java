package com.learn.system;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Shehan on 5/18/15.
 */
public class Router {
    public Function<Request, Response> getRoute(Request request) {
        if (request.getRequestId() % 2 == 0) {
            System.out.println("Route 1 found");
            return request1 -> new Response("Route 1");
        } else {
            System.out.println("Route 2 found");
            return request2 -> new Response("Route 2");
        }
    }

    public void withRoute(Request request, Consumer<Request> next) {
        Function<Request, Response> route = getRoute(request);
        request.getSession().setExecution(route);
        next.accept(request);
    }
}
