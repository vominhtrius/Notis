package com.solis.notis.common.core;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public abstract class AbstractWorker<T> implements Runnable {

    private final AbstractConsumer<MessageWraper<T>> consumer;

    private final AtomicInteger runningThread;

    public AbstractWorker(AbstractConsumer<MessageWraper<T>> consumer, AtomicInteger runningThread) {
        this.consumer = consumer;
        this.runningThread = runningThread;
    }

    @Override
    public void run() {
        try {
            while (true) {
                MessageWraper<T> message = consumer.getMessage();
                
                if (message.getStatus() == MessageWraper.Status.STOP) {
                    System.out.println("Worker call shutdown " + Thread.currentThread().getName());
                    runningThread.decrementAndGet();
                    break;
                }

                processMessage(message.getMessage());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract void processMessage(T message);
}
