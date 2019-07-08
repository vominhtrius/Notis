package com.solis.notis.counter;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Võ Minh Trí
 */
public class HandlerPool {

    static private HandlerPool instance;

    private Queue<NotisCounterHandler> pool = new LinkedList<>();
    private String py;
    private String path;
    private String config;
    private String output;
    private int sleep;

    public static HandlerPool getInstance() {
        if (instance == null) {
            synchronized (HandlerPool.class) {
                instance = new HandlerPool();
            }
        }
        return instance;
    }

    private HandlerPool() {
    }

    public void setScipt(String py, String path, String config, String output, int sleep) {
        this.py = py;
        this.path = path;
        this.config = config;
        this.sleep = sleep;
        this.output = output;
    }

    public synchronized NotisCounterHandler getNotisCounterHandler() {
        NotisCounterHandler facebookClient = pool.poll();
        if (facebookClient == null) {
            return new NotisCounterHandler(py, path, config, output, sleep);
        }

        return facebookClient;
    }

    public synchronized void release(NotisCounterHandler client) {
        pool.add(client);
    }
}
