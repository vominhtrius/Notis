package com.solis.notis.crawler;

import com.google.protobuf.ByteString;
import com.solis.notis.common.core.AbstractConsumer;
import com.solis.notis.common.core.AbstractProducer;
import com.solis.notis.common.core.AbstractService;
import com.solis.notis.common.core.AbstractWorker;
import com.solis.notis.common.core.MessageWraper;
import com.solis.notis.counter.protobuf.NotisCounterRequest;
import com.solis.notis.counter.protobuf.NotisCounterResponse;
import com.solis.notis.counter.protobuf.NotisCounterServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class ImageHandler extends AbstractService<HandleTask> {

    private static final Logger LOG = Logger.getLogger(ImageHandler.class.getName());

    private static ImageHandler instance = null;

    private String host;
    private int port;
    ManagedChannel channel;

    ImageIndexer imageIndexer;

    public static ImageHandler getInstance() {
        if (instance == null) {
            synchronized (ImageHandler.class) {
                if (instance == null) {
                    instance = new ImageHandler();
                }
            }
        }

        return instance;
    }

    private ImageHandler() {
    }

    public void initialize(ImageIndexer imageIndexer, int nWorker, String notisHost, int notisPort) {
        super.initialize(nWorker);
        this.imageIndexer = imageIndexer;
        this.host = notisHost;
        this.port = notisPort;
        channel = ManagedChannelBuilder.forTarget(host + ":" + port)
                .usePlaintext()
                .build();

    }

    @Override
    protected AbstractConsumer<MessageWraper<HandleTask>> newConsumer() {
        return new AbstractConsumer<MessageWraper<HandleTask>>(queue) {
        };
    }

    @Override
    protected AbstractProducer<MessageWraper<HandleTask>> newProducer() {
        return new AbstractProducer<MessageWraper<HandleTask>>(queue) {
        };
    }

    @Override
    protected AbstractWorker<HandleTask> newWorker(AbstractConsumer<MessageWraper<HandleTask>> consumer) {
        return new ImageHandlerWorker(consumer);
    }

    class ImageHandlerWorker extends AbstractWorker<HandleTask> {

        NotisCounterServiceGrpc.NotisCounterServiceBlockingStub stub;

        public ImageHandlerWorker(AbstractConsumer<MessageWraper<HandleTask>> consumer) {
            super(consumer, runningThread);
            stub = NotisCounterServiceGrpc.newBlockingStub(channel);
        }

        @Override
        protected void processMessage(HandleTask task) {
            final ByteString byteString = task.getByteString();
            final String cameraId = task.getCameraId();
            final long date = task.getDate();

            long count = countVehicle(byteString);
            LOG.info("counter: " + count);

            IndexTask indexTask = new IndexTask(cameraId, date, count);

            imageIndexer.putMessage(indexTask);
        }

        private long countVehicle(ByteString byteString) {

            if (byteString == null) {
                return 0;
            }

            try {
                NotisCounterRequest request = NotisCounterRequest.newBuilder()
                        .setImage(byteString)
                        .build();

                NotisCounterResponse response = stub.countVehicle(request);
                return response.getCounter();

            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
            }

            return 0;
        }
    }

}
