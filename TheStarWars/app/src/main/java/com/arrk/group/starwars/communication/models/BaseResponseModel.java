package com.arrk.group.starwars.communication.models;

import com.google.gson.annotations.Expose;

/**
 * @author SANDY
 */
public class BaseResponseModel {

    @Expose
    private int count;
    @Expose
    private String next;
    @Expose
    private String previous;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
