package com.solis.notis.common.core;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Võ Minh Trí
 */
public abstract class AbstractConsumer<T> {

    protected BlockingQueue<T> queue;

    public AbstractConsumer(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    public T getMessage() throws InterruptedException {
        return this.queue.take();
    }
}
