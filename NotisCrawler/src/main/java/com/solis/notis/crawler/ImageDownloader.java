package com.solis.notis.crawler;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.solis.common.utils.Utils;
import com.solis.notis.app.MainApp;
import com.solis.notis.common.core.AbstractConsumer;
import com.solis.notis.common.core.AbstractProducer;
import com.solis.notis.common.core.AbstractService;
import com.solis.notis.common.core.AbstractWorker;
import com.solis.notis.common.core.MessageWraper;
import com.solis.notis.common.rabbit.MessageHandler;
import com.solis.notis.protobuf.rabbit.CrawlImagePayload;
import com.solis.notis.protobuf.rabbit.MessageRequestProto;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class ImageDownloader extends AbstractService<CrawlImagePayload> implements MessageHandler {

    private static final Logger LOG = Logger.getLogger(ImageDownloader.class.getName());

    private static String CAMERA_URL_FORMAT = "https://api.notis.vn/v3/cameras/%s/snapshot";

    private static ImageDownloader instance = null;
    private static ImageHandler imageHandler;

    public static ImageDownloader getInstance() {
        if (instance == null) {
            synchronized (ImageHandler.class) {
                if (instance == null) {
                    instance = new ImageDownloader();
                }
            }
        }

        return instance;
    }

    private ImageDownloader() {
    }

    public void initialize(int nWorker, ImageHandler imageHandler) {
        super.initialize(nWorker);
        this.imageHandler = imageHandler;
    }

    @Override
    protected AbstractConsumer<MessageWraper<CrawlImagePayload>> newConsumer() {
        return new AbstractConsumer<MessageWraper<CrawlImagePayload>>(queue) {
        };
    }

    @Override
    protected AbstractProducer<MessageWraper<CrawlImagePayload>> newProducer() {
        return new AbstractProducer<MessageWraper<CrawlImagePayload>>(queue) {
        };
    }

    @Override
    protected AbstractWorker<CrawlImagePayload> newWorker(AbstractConsumer<MessageWraper<CrawlImagePayload>> consumer) {
        return new ImageDownloadWorker(consumer);
    }

    @Override
    public void handleMesssage(MessageRequestProto mess) {
        try {
            CrawlImagePayload crawlImagePayload = mess.getPayload().unpack(CrawlImagePayload.class);
            LOG.info("Receive a message with cameraId=" + crawlImagePayload.getCameraId());
            this.putMessage(crawlImagePayload);
        } catch (InvalidProtocolBufferException ex) {
            Logger.getLogger(ImageDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class ImageDownloadWorker extends AbstractWorker<CrawlImagePayload> {

        public ImageDownloadWorker(AbstractConsumer<MessageWraper<CrawlImagePayload>> consumer) {
            super(consumer, runningThread);
        }

        @Override
        protected void processMessage(CrawlImagePayload imagePayload) {
            try {
                final String cameraId = imagePayload.getCameraId();
                String imageURL = getCameraURL(cameraId);

                long date = Utils.getCurrentUnix();

                ByteString byteString = downImage(imageURL);

                HandleTask task = new HandleTask(byteString, cameraId, date);
                imageHandler.putMessage(task);
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getCameraURL(String cameraId) {
        return String.format(CAMERA_URL_FORMAT, cameraId);
    }

    private ByteString downImage(String imageURL) {
        if (imageURL == null || imageURL.equals("") == true) {
            return null;
        }

        try {

            URL url = new URL(imageURL);
            InputStream is = url.openStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[4 * 1024];

            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] res = byteArrayOutputStream.toByteArray();
            ByteString byteString = ByteString.copyFrom(res);

            LOG.info("Download " + imageURL + " with " + res.length + " bytes");

            return byteString;
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
