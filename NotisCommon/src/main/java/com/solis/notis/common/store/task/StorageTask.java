package com.solis.notis.common.store.task;

import com.solis.notis.common.store.StorageService;



/**
 *
 * @author Võ Minh Trí
 */
public abstract class StorageTask {

    public abstract void process(StorageService service);
}
