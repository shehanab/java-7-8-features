package com.learn.system;

/**
 * Created by Shehan on 5/18/15.
 */
public class Response {

    private String value;

    public Response(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Response{" +
                "value='" + value + '\'' +
                '}';
    }
}
