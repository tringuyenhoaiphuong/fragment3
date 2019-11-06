package com.lemycanh.citycriminal;

/**
 * Created by lemycanh on 6/11/2019.
 */

public class ListChangedEvent {
    private String message;


    public ListChangedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
