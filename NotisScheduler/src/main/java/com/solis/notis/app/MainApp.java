package com.solis.notis.app;

import com.solis.common.utils.ConfigPropertiesReader;
import com.solis.notis.common.rabbit.RabbitSender;
import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.store.dto.NotisDTO;
import com.solis.notis.scheduler.NotisJob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Võ Minh Trí
 */
public class MainApp {

    private static final Logger LOG = Logger.getLogger(MainApp.class.getName());

    private static String STORAGE_CONNECT_URL = null;
    private static String STORAGE_DB_NAME = null;
    private static int STORAGE_NWORKER = 4;

    private static int RABBIT_NWORKER;

    private static String RABBIT_HOST = null;
    private static int RABBIT_PORT = 5672;
    private static String RABBIT_QUEUE = null;
    private static String RABBIT_USERNAME = null;
    private static String RABBIT_PASSWORD = null;

    public static void main(String[] args) throws InterruptedException, SchedulerException {
        boolean loaded = loadConfig("./config.properties");
        if (loaded == false) {
            System.err.println("Can't load config.properties file");
            return;
        }

        RabbitSender.getInstance().initialize(RABBIT_HOST, RABBIT_PORT, RABBIT_QUEUE,
                RABBIT_NWORKER, RABBIT_USERNAME, RABBIT_PASSWORD);
        RabbitSender.getInstance().serve();

        StorageService.getInstance().initialize(STORAGE_CONNECT_URL, STORAGE_DB_NAME, STORAGE_NWORKER);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(NotisJob.class)
                .withIdentity("FacebookMonitorJob", "com.solis.facebook")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("FacebookMonitorJob", "com.solis.facebook")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10)) // start 00:00 
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    scheduler.shutdown();
                    RabbitSender.getInstance().shutdown();
                    StorageService.getInstance().shutdown();
                } catch (SchedulerException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static boolean loadConfig(String configPath) {
        try {
            ConfigPropertiesReader reader = ConfigPropertiesReader.getInstance();
            reader.load(configPath);
            STORAGE_CONNECT_URL = reader.getProperty("storage.connectUrl");
            STORAGE_DB_NAME = reader.getProperty("storage.dbName");
            STORAGE_NWORKER = reader.getIntegerProperty(configPath, 4);

            RABBIT_NWORKER = reader.getIntegerProperty("rabbit.nWorker", 4);
            RABBIT_HOST = reader.getProperty("rabbit.host");
            RABBIT_PORT = reader.getIntegerProperty("rabbit.port", 5672);
            RABBIT_QUEUE = reader.getProperty("rabbit.queue");
            RABBIT_USERNAME = reader.getProperty("rabbit.username");
            RABBIT_PASSWORD = reader.getProperty("rabbit.password");

            return !(STORAGE_CONNECT_URL == null || STORAGE_DB_NAME == null
                    || RABBIT_HOST == null || RABBIT_PASSWORD == null
                    || RABBIT_QUEUE == null || RABBIT_USERNAME == null);

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
