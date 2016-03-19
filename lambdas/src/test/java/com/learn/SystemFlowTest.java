package com.learn;

import com.learn.system.*;
import org.junit.Test;

/**
 * Created by Shehan on 5/18/15.
 */
public class SystemFlowTest {

    private ImperativeFlow imperativeFlow;
    private CPSFlow cpsFlow;

    @Test
    public void testName() throws Exception {
        SessionManager sessionManager = new SessionManager();
        TPSController tpsController = new TPSController();
        Router router = new Router();

        imperativeFlow = new ImperativeFlow();
        imperativeFlow.setSessionManager(sessionManager);
        imperativeFlow.setTpsController(tpsController);
        imperativeFlow.setRouter(router);

        cpsFlow = new CPSFlow();
        cpsFlow.setSessionManager(sessionManager);
        cpsFlow.setTpsController(tpsController);
        cpsFlow.setRouter(router);
    }

    @Test
    public void testImperativeFlow() throws Exception {
        Request request = new Request();
        request.setRequestId(0);
        imperativeFlow.execute(request);
    }

    @Test
    public void testCPSFlow() throws Exception {
        Request request = new Request();
        request.setRequestId(0);
        cpsFlow.execute(request);
    }
}
