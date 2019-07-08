package com.solis.notis.app;

import com.solis.common.utils.ConfigPropertiesReader;
import com.solis.notis.counter.NotisCounterServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class MainApp {

    private static String HOST = null;
    private static int PORT = 20001;
    private static int SLEEP = 20001;

    private static String SCRIPT_PY = null;
    private static String SCRIPT_PATH = null;
    private static String SCRIPT_CONFIG = null;
    private static String SCRIPT_OUTPUT = null;

    public static void main(String[] args) throws InterruptedException {
        loadConfig("./config.properties");

        NotisCounterServer server = new NotisCounterServer(HOST, PORT, SCRIPT_PY, SCRIPT_PATH, SCRIPT_CONFIG, SCRIPT_OUTPUT, SLEEP);
        server.start();
        server.blockUntilShutdown();
    }

    private static boolean loadConfig(String configPath) {
        try {
            ConfigPropertiesReader reader = ConfigPropertiesReader.getInstance();
            reader.load(configPath);
            HOST = reader.getProperty("service.host");
            PORT = reader.getIntegerProperty("service.port", 20001);
            SLEEP = reader.getIntegerProperty("service.sleep", 5000);

            SCRIPT_PY = reader.getProperty("script.py");
            SCRIPT_PATH = reader.getProperty("script.path");
            SCRIPT_CONFIG = reader.getProperty("script.config");
            SCRIPT_OUTPUT = reader.getProperty("script.output");

            return !(HOST == null || SCRIPT_PATH == null
                    || SCRIPT_CONFIG == null || SCRIPT_PY == null);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
