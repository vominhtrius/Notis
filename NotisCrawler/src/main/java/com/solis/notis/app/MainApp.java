package com.solis.notis.app;

import com.google.protobuf.ByteString;
import com.solis.common.utils.ConfigPropertiesReader;
import com.solis.notis.crawler.ImageDownloader;
import com.solis.notis.crawler.ImageHandler;
import com.solis.notis.crawler.ImageIndexer;
import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.rabbit.RabbitReceiver;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class MainApp {

    private static final Logger LOG = Logger.getLogger(MainApp.class.getName());

    private static String STORAGE_CONNECT_URL = null;
    private static String STORAGE_DB_NAME = null;
    private static int STORAGE_NWORKER = 4;

    private static String NOTIS_HOST = null;
    private static int NOTIS_PORT = 6007;

    private static String RABBIT_HOST = null;
    private static int RABBIT_PORT = 5672;
    private static String RABBIT_QUEUE = null;
    private static String RABBIT_USERNAME = null;
    private static String RABBIT_PASSWORD = null;

    public static void main(String[] args) {
        run();
    }

    private static boolean loadConfig(String configPath) {
        try {
            ConfigPropertiesReader reader = ConfigPropertiesReader.getInstance();
            reader.load(configPath);
            STORAGE_CONNECT_URL = reader.getProperty("storage.connectUrl");
            STORAGE_DB_NAME = reader.getProperty("storage.dbName");
            STORAGE_NWORKER = reader.getIntegerProperty(configPath, 4);

//            MONITOR_NWORKER = reader.getIntegerProperty("monitor.nWorker", 4);
            NOTIS_HOST = reader.getProperty("notis.host");
            NOTIS_PORT = reader.getIntegerProperty("notis.port", 6007);

            RABBIT_HOST = reader.getProperty("rabbit.host");
            RABBIT_PORT = reader.getIntegerProperty("rabbit.port", 5672);
            RABBIT_QUEUE = reader.getProperty("rabbit.queue");
            RABBIT_USERNAME = reader.getProperty("rabbit.username");
            RABBIT_PASSWORD = reader.getProperty("rabbit.password");

            return !(STORAGE_CONNECT_URL == null || STORAGE_DB_NAME == null
                    || RABBIT_HOST == null || RABBIT_PASSWORD == null
                    || RABBIT_QUEUE == null || RABBIT_USERNAME == null
                    || NOTIS_HOST == null);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static void run() {
        boolean loaded = loadConfig("./config.properties");
        if (loaded == false) {
            LOG.log(Level.SEVERE, "Can't load config properties file");
            return;
        }

        StorageService.getInstance().initialize(STORAGE_CONNECT_URL, STORAGE_DB_NAME, STORAGE_NWORKER);
        StorageService.getInstance().serve();

        ImageIndexer.getInstance().initialize(StorageService.getInstance(), STORAGE_NWORKER);
        ImageIndexer.getInstance().serve();

        ImageHandler.getInstance().initialize(ImageIndexer.getInstance(), 4, NOTIS_HOST, NOTIS_PORT);
        ImageHandler.getInstance().serve();

        ImageDownloader.getInstance().initialize(4, ImageHandler.getInstance());
        ImageDownloader.getInstance().serve();

        RabbitReceiver rabbitReceiver = new RabbitReceiver(ImageDownloader.getInstance());

        rabbitReceiver.initialize(RABBIT_HOST, RABBIT_PORT, RABBIT_QUEUE, RABBIT_USERNAME, RABBIT_PASSWORD);
        rabbitReceiver.serve();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                rabbitReceiver.shutdown();
                LOG.info("Call shutdown StorageService");
                StorageService.getInstance().shutdown();

                ImageDownloader.getInstance().shutdown();
                ImageHandler.getInstance().shutdown();
                ImageIndexer.getInstance().shutdown();
            }
        });
    }
}
