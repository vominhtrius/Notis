package com.solis.notis.common.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public abstract class AbstractService<T> {

    protected int nWorker;

    protected ExecutorService executor;

    protected final BlockingQueue<MessageWraper<T>> queue;

    protected final AbstractProducer<MessageWraper<T>> producer;

    protected final AtomicInteger runningThread;

    protected List<AbstractConsumer<MessageWraper<T>>> listConsumers = new ArrayList<>();

    public AbstractService(int nWorker) {
        this.nWorker = nWorker;
        this.queue = new LinkedBlockingQueue<>();
        this.producer = newProducer();
        this.runningThread = new AtomicInteger();
    }

    public AbstractService() {
        this.queue = new LinkedBlockingQueue<>();
        this.producer = newProducer();
        this.runningThread = new AtomicInteger();
    }

    protected void initialize(int nWorker) {
        this.setNWorker(nWorker);
    }

    public void serve() {
        this.executor = Executors.newFixedThreadPool(nWorker);

        for (int i = 0; i < nWorker; i++) {
            AbstractConsumer<MessageWraper<T>> consumer = newConsumer();
            listConsumers.add(consumer);
            AbstractWorker<T> worker = newWorker(consumer);

            executor.execute(worker);
            runningThread.incrementAndGet();
        }
    }

    public void shutdown() {
        while (runningThread.get() != 0) {
            try {

                producer.putMessage(new MessageWraper<T>(MessageWraper.Status.STOP));
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setNWorker(int nWorker) {
        this.nWorker = nWorker;
    }

    public boolean putMessage(T message) {
        try {
            this.producer.putMessage(new MessageWraper<T>(message));

            return true;
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    protected abstract AbstractConsumer<MessageWraper<T>> newConsumer();

    protected abstract AbstractProducer<MessageWraper<T>> newProducer();

    protected abstract AbstractWorker<T> newWorker(AbstractConsumer<MessageWraper<T>> consumer);
}
