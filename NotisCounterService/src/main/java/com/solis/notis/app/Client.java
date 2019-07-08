package com.solis.notis.app;

import com.google.protobuf.ByteString;
import com.solis.notis.counter.protobuf.NotisCounterRequest;
import com.solis.notis.counter.protobuf.NotisCounterResponse;
import com.solis.notis.counter.protobuf.NotisCounterServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
public class Client {

    public static void main(String[] args) { //113.161.89.108
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("113.161.89.108:6007")
                .usePlaintext()
                .build();
        NotisCounterServiceGrpc.NotisCounterServiceBlockingStub stub = NotisCounterServiceGrpc.newBlockingStub(channel);

        ByteString image = downImage("https://api.notis.vn/v3/cameras/5a824ee15058170011f6eab6/snapshot");

        NotisCounterRequest request = NotisCounterRequest.newBuilder()
                .setImage(image)
                .build();

        NotisCounterResponse response = stub.countVehicle(request);

        System.out.println(response);
    }

    private static ByteString downImage(String imageURL) {
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

            return byteString;
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
