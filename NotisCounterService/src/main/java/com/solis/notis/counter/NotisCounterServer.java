package com.solis.notis.counter;

import com.google.protobuf.ByteString;
import com.solis.notis.counter.protobuf.NotisCounterRequest;
import com.solis.notis.counter.protobuf.NotisCounterResponse;
import com.solis.notis.counter.protobuf.NotisCounterServiceGrpc;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class NotisCounterServer {

    private static final Logger LOG = Logger.getLogger(NotisCounterServer.class.getName());
    HandlerPool handlerPool = HandlerPool.getInstance();

    private String host;
    private int port;
    private Server server;
    private String scriptPy;
    private String scriptPath;
    private String scriptConfig;
    private String output;

    public NotisCounterServer(String host, int port, String scriptPy, String scriptPath, String scriptConfig, String output, int sleep) {
        this.host = host;
        this.port = port;
        this.scriptPy = scriptPy;
        this.scriptPath = scriptPath;
        this.scriptConfig = scriptConfig;
        this.output = output;
        
        handlerPool.setScipt(scriptPy, scriptPath, scriptConfig, output, sleep);

        this.server = NettyServerBuilder
                .forAddress(new InetSocketAddress(host, port))
                .addService(new NotisCounterServiceImpl())
                .build();
    }

    public void start() {
        if (server == null) {
            return;
        }

        try {
            server.start();
            LOG.info("Server started, listening on " + host + ":" + port);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.err.println("*** shutting down gRPC server since JVM is shutting down");
                    NotisCounterServer.this.stop();
                    System.err.println("*** server shutdown");
                }
            });
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() {
        if (server != null) {
            try {
                server.awaitTermination();
            } catch (InterruptedException ex) {
                Logger.getLogger(NotisCounterServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class NotisCounterServiceImpl extends NotisCounterServiceGrpc.NotisCounterServiceImplBase {

        @Override
        public void countVehicle(NotisCounterRequest request, StreamObserver<NotisCounterResponse> responseObserver) {
            ByteString image = request.getImage();
            NotisCounterHandler handler = handlerPool.getNotisCounterHandler();
            try {
                long counter = handler.countImage(image);

                NotisCounterResponse response = NotisCounterResponse.newBuilder()
                        .setCounter(counter)
                        .setStatus(1)
                        .build();

                responseObserver.onNext(response);
            } catch (Exception ex) {
                NotisCounterResponse response = NotisCounterResponse.newBuilder()
                        .setCounter(0)
                        .setStatus(2)
                        .build();

                responseObserver.onNext(response);
                LOG.log(Level.SEVERE, null, ex);

            } finally {
                responseObserver.onCompleted();
                handlerPool.release(handler);
            }
        }
    }
}
