package com.learn.rule;

/**
 * Created by Shehan on 5/27/15.
 */
public class Message {

    private String da;
    private int ton;
    private int npi;
    private String message;
    private String route;

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public int getTon() {
        return ton;
    }

    public void setTon(int ton) {
        this.ton = ton;
    }

    public int getNpi() {
        return npi;
    }

    public void setNpi(int npi) {
        this.npi = npi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Message{" +
                "da='" + da + '\'' +
                ", ton=" + ton +
                ", npi=" + npi +
                ", message='" + message + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
