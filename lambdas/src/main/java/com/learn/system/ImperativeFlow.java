package com.learn.system;

import java.util.function.Function;

/**
 * Created by Shehan on 5/18/15.
 */
public class ImperativeFlow {

    private SessionManager sessionManager;
    private TPSController tpsController;
    private Router router;

    public void execute(Request request) {
        Session session = sessionManager.getSession(request);
        request.setSession(session);

        boolean allowed = tpsController.isAllowed(request);
        session.setAllowed(allowed);
        if (allowed) {

            Function<Request, Response> route = router.getRoute(request);

            Response response = route.apply(request);
            System.out.println(response);
        }
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setTpsController(TPSController tpsController) {
        this.tpsController = tpsController;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}
