package com.solis.notis.common.core;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Võ Minh Trí
 */
public abstract class AbstractProducer<T> {

    private BlockingQueue<T> queue;

    public AbstractProducer(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void putMessage(T message) throws InterruptedException {
        this.queue.put(message);
    }

}
