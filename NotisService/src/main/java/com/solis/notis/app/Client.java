package com.solis.notis.app;

import com.solis.protobuf.notis.GetAllCameraRequest;
import com.solis.protobuf.notis.GetAllCameraResponse;
import com.solis.protobuf.notis.NotisServiceGrpc;
import com.solis.protobuf.notis.QueryCameraRequest;
import com.solis.protobuf.notis.QueryCameraResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author Võ Minh Trí
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:10101")
                .usePlaintext()
                .build();
        NotisServiceGrpc.NotisServiceBlockingStub stub = NotisServiceGrpc.newBlockingStub(channel);
        GetAllCameraRequest request = GetAllCameraRequest.newBuilder()
                .build();
        GetAllCameraResponse response = stub.getAllCamera(request);
        System.out.println(response);

        long prev = System.currentTimeMillis() / 1000L;
        prev = prev - 10;

        for (int i = 0; i < 100; i++) {
            long now = System.currentTimeMillis() / 1000L;

            System.out.println(prev + " - " + now);
            QueryCameraRequest cameraRequest = QueryCameraRequest.newBuilder()
                    .setCameraId("5b0b7bbe0e517b00119fd806")
                    .setFrom(prev)
                    .setTo(now)
                    .build();

            prev = now;

            QueryCameraResponse response1 = stub.queryCamera(cameraRequest);

            System.out.println(response1);
            Thread.sleep(1000 * 10);
        }
    }
}
