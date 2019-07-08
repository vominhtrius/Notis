package com.solis.notis.crawler;

/**
 *
 * @author Võ Minh Trí
 */
public class IndexTask {

    String cameraId;
    long date;
    long counter;

    public IndexTask() {
    }

    public IndexTask(String cameraId, long date, long counter) {
        this.cameraId = cameraId;
        this.date = date;
        this.counter = counter;
    }

    public String getCameraId() {
        return cameraId;
    }

    public long getDate() {
        return date;
    }

    public long getCounter() {
        return counter;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}
