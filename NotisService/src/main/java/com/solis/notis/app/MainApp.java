package com.solis.notis.app;

import com.solis.common.utils.ConfigPropertiesReader;
import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.store.dto.NotisDTO;
import com.solis.notis.service.server.NotisServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class MainApp {

    private static String STORAGE_CONNECT_URL = null;
    private static String STORAGE_DB_NAME = null;
    private static int STORAGE_NWORKER = 4;

    private static String SERVICE_HOST = null;
    private static int SERVICE_PORT = 123;

    public static void main(String[] args) {
        boolean done = loadConfig("./config.properties");
        if (done == false) {
            System.out.println("Can't load config.properties file");
            return;
        }

        StorageService.getInstance().initialize(STORAGE_CONNECT_URL, STORAGE_DB_NAME, STORAGE_NWORKER);
        
        NotisServer server = new NotisServer(StorageService.getInstance(), SERVICE_HOST, SERVICE_PORT);
        server.start();
        server.blockUntilShutdown();
    }

    private static boolean loadConfig(String configPath) {
        try {
            ConfigPropertiesReader reader = ConfigPropertiesReader.getInstance();
            reader.load(configPath);

            STORAGE_CONNECT_URL = reader.getProperty("storage.connectUrl");
            STORAGE_DB_NAME = reader.getProperty("storage.dbName");
            STORAGE_NWORKER = reader.getIntegerProperty("storage.nWorker", 4);

            SERVICE_HOST = reader.getProperty("service.host");
            SERVICE_PORT = reader.getIntegerProperty("service.port", 10010);

            return !(STORAGE_CONNECT_URL == null || STORAGE_DB_NAME == null
                    || SERVICE_HOST == null);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
