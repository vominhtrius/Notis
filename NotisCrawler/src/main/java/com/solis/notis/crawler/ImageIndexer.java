package com.solis.notis.crawler;

import com.solis.notis.common.core.AbstractConsumer;
import com.solis.notis.common.core.AbstractProducer;
import com.solis.notis.common.core.AbstractService;
import com.solis.notis.common.core.AbstractWorker;
import com.solis.notis.common.core.MessageWraper;
import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.store.dto.NotisLogDTO;
import com.solis.notis.common.store.task.SaveNotisLogTask;

/**
 *
 * @author Võ Minh Trí
 */
public class ImageIndexer extends AbstractService<IndexTask> {

    StorageService storageService;

    private static ImageIndexer instance = null;

    public static ImageIndexer getInstance() {
        if (instance == null) {
            synchronized (ImageHandler.class) {
                if (instance == null) {
                    instance = new ImageIndexer();
                }
            }
        }

        return instance;
    }

    private ImageIndexer() {
    }

    public void initialize(StorageService storageService, int nWorker) {
        super.initialize(nWorker);
        this.storageService = storageService;
    }

    @Override
    protected AbstractConsumer<MessageWraper<IndexTask>> newConsumer() {
        return new AbstractConsumer<MessageWraper<IndexTask>>(queue) {
        };
    }

    @Override
    protected AbstractProducer<MessageWraper<IndexTask>> newProducer() {
        return new AbstractProducer<MessageWraper<IndexTask>>(queue) {
        };
    }

    @Override
    protected AbstractWorker<IndexTask> newWorker(AbstractConsumer<MessageWraper<IndexTask>> consumer) {
        return new ImageIndexWorker(consumer);
    }

    class ImageIndexWorker extends AbstractWorker<IndexTask> {

        public ImageIndexWorker(AbstractConsumer<MessageWraper<IndexTask>> consumer) {
            super(consumer, runningThread);
        }
        
        @Override
        protected void processMessage(IndexTask task) {
            String cameraId = task.getCameraId();
            long counter = task.getCounter();
            long date = task.getDate();
            
            NotisLogDTO notisLogDTO = new NotisLogDTO(cameraId, counter, date);
            storageService.putMessage(new SaveNotisLogTask(notisLogDTO));
        }
    }
}
