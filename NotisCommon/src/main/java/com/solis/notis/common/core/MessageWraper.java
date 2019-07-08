package com.solis.notis.common.core;

/**
 *
 * @author Võ Minh Trí
 */
public class MessageWraper<M> {

    public enum Status {
        HANDLE,
        STOP
    }

    private Status status;
    private M message;

    public MessageWraper(M message) {
        this.message = message;
        this.status = Status.HANDLE;
    }

    public MessageWraper(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public M getMessage() {
        return message;
    }

}
