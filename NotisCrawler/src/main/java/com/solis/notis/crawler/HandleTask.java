package com.solis.notis.crawler;

import com.google.protobuf.ByteString;

/**
 *
 * @author Võ Minh Trí
 */
public class HandleTask {

    ByteString byteString;
    String cameraId;
    long date;

    public HandleTask() {
    }

    public HandleTask(ByteString byteString, String cameraId, long date) {
        this.byteString = byteString;
        this.cameraId = cameraId;
        this.date = date;
    }

    public ByteString getByteString() {
        return byteString;
    }

    public String getCameraId() {
        return cameraId;
    }

    public long getDate() {
        return date;
    }

}
