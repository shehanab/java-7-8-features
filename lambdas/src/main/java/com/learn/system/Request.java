package com.learn.system;

/**
 * Created by Shehan on 5/18/15.
 */
public class Request {
    private long requestId;
    private Session session;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
