package com.learn.system;

/**
 * Created by Shehan on 5/18/15.
 */
public class CPSFlow {


    private SessionManager sessionManager;
    private TPSController tpsController;
    private Router router;

    public void execute(Request request) {
        sessionManager.withSession(request, this::checkAllowed);
    }

    private void checkAllowed(Request request) {
        tpsController.doIfAllowed(request, this::selectRouteIfAllowed);
    }

    private void selectRouteIfAllowed(Request request) {
        if (request.getSession().isAllowed()) {
            router.withRoute(request, this::onRoute);
        }
    }

    private void onRoute(Request request) {
        Response response = request.getSession().getExecution().apply(request);
        System.out.println(response);
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
