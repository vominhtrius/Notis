package com.solis.notis.common.store.dao;

import org.mongodb.morphia.Datastore;

/**
 *
 * @author Võ Minh Trí
 */
public abstract class AbstractDAO<T> {

    protected Datastore datastore;

    public AbstractDAO(Datastore datastore) {
        this.datastore = datastore;
    }

    public void save(T t) {
        this.datastore.save(t);
    }
}
