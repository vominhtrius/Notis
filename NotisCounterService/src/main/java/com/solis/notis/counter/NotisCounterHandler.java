package com.solis.notis.counter;

import com.google.protobuf.ByteString;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class NotisCounterHandler {
    
    private static final Logger LOG = Logger.getLogger(NotisCounterHandler.class.getName());
    
    static AtomicInteger instanceCnt = new AtomicInteger(1);
    
    private int id;
    private String pathScript, configScript, py, output;
    int sleep;
    
    public NotisCounterHandler(String py, String path, String config, String output, int sleep) {
        this.id = instanceCnt.getAndIncrement();
        this.py = py;
        this.pathScript = path;
        this.sleep = sleep;
        this.output = output;
        this.configScript = config;
    }
    
    public long countImage(ByteString image) {
        
        String imagePath = getImagePath();
        String resultPath = getResultPath();
        
        saveImage(image, imagePath);
        runScript(imagePath, resultPath);
        
        return getCounter(resultPath);
    }
    
    private String getImagePath() {
        return output + "/image_" + id + ".jpg";
    }
    
    private String getResultPath() {
        return output + "/result_" + id + ".txt";
    }

    // save image to file
    public void saveImage(ByteString image, String imagePath) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(imagePath));
            
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            image.writeTo(bufferedOutputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // co image
    // run script

    public void runScript(String imagePath, String outputPath) {
        try {
            
            String command = py + " " + pathScript + " " + imagePath + " " + configScript + " --result " + outputPath;

//            ProcessBuilder pb = new ProcessBuilder(py,
//                    pathScript,
//                    imagePath,
//                    configScript,
//                    "--result",
//                    outputPath);
//            ProcessBuilder pb = new ProcessBuilder(py);
//            LOG.info(pb.command().toString());
            LOG.info(command);
            LOG.info("script start!");
            
            Process p = Runtime.getRuntime().exec(command);

//            Process p = pb.start();
            p.waitFor();
            LOG.info("script done!");
            
            Thread.sleep(sleep);
            LOG.info("Sleeping..." + sleep);
        } catch (IOException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long getCounter(String outputPath) {
        try {
            
            BufferedReader fileReader = new BufferedReader(new FileReader(outputPath));
            
            String line = fileReader.readLine();
            
            return Long.parseLong(line);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(NotisCounterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
