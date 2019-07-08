package com.solis.notis.common.store;

import com.solis.notis.common.core.AbstractConsumer;
import com.solis.notis.common.core.AbstractProducer;
import com.solis.notis.common.core.AbstractService;
import com.solis.notis.common.core.AbstractWorker;
import com.solis.notis.common.core.MessageWraper;
import com.solis.notis.common.store.dao.NotisDAO;
import com.solis.notis.common.store.dao.NotisLogDAO;
import com.solis.notis.common.store.mongodb.MorphiaDB;
import com.solis.notis.common.store.task.StorageTask;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class StorageService extends AbstractService<StorageTask> {

    private static final Logger LOG = Logger.getLogger(StorageService.class.getName());
    private static StorageService instance = null;

    MorphiaDB morphiaDB;

    NotisDAO notisDAO;
    NotisLogDAO notisLogDAO;

    public static StorageService getInstance() {
        if (instance == null) {
            synchronized (StorageService.class) {
                if (instance == null) {
                    instance = new StorageService();
                }
            }
        }

        return instance;
    }

    private StorageService() {
    }

    public void initialize(String connectURL, String dbName, int nWorker) {
        LOG.info("StorageService initialize with connectURK=" + connectURL + " dbName=" + dbName);
        super.initialize(nWorker);
        morphiaDB = new MorphiaDB(connectURL, dbName);

        notisDAO = new NotisDAO(morphiaDB.getDatastore());
        notisLogDAO = new NotisLogDAO(morphiaDB.getDatastore());
    }

    @Override
    protected AbstractConsumer<MessageWraper<StorageTask>> newConsumer() {
        return new AbstractConsumer<MessageWraper<StorageTask>>(queue) {
        };
    }

    @Override
    protected AbstractProducer<MessageWraper<StorageTask>> newProducer() {
        return new AbstractProducer<MessageWraper<StorageTask>>(queue) {
        };
    }

    @Override
    protected AbstractWorker<StorageTask> newWorker(AbstractConsumer<MessageWraper<StorageTask>> consumer) {
        return new StorageWorker(consumer);
    }

    class StorageWorker extends AbstractWorker<StorageTask> {

        public StorageWorker(AbstractConsumer<MessageWraper<StorageTask>> consumer) {
            super(consumer, runningThread);
        }

        @Override
        protected void processMessage(StorageTask task) {
            task.process(StorageService.this);
        }
    }

    public NotisLogDAO getNotisLogDAO() {
        return notisLogDAO;
    }

    public NotisDAO getNotisDAO() {
        return notisDAO;
    }
}
