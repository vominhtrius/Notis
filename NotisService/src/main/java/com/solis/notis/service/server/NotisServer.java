package com.solis.notis.service.server;

import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.store.dao.NotisDAO;
import com.solis.notis.common.store.dao.NotisLogDAO;
import com.solis.notis.common.store.dto.NotisDTO;
import com.solis.protobuf.notis.CameraProto;
import com.solis.protobuf.notis.GetAllCameraRequest;
import com.solis.protobuf.notis.GetAllCameraResponse;
import com.solis.protobuf.notis.NotisServiceGrpc;
import com.solis.protobuf.notis.QueryCameraRequest;
import com.solis.protobuf.notis.QueryCameraResponse;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class NotisServer {

    private static final Logger LOG = Logger.getLogger(NotisServer.class.getName());

    private final String host;
    private final int port;
    private final Server server;
    private NotisDAO notisDAO;
    private NotisLogDAO notisLogDAO;

    public NotisServer(StorageService storageService, String host, int port) {
        this.host = host;
        this.port = port;
        this.server = NettyServerBuilder
                .forAddress(new InetSocketAddress(host, port))
                .addService(new NotisServiceImpl())
                .build();
        notisDAO = storageService.getNotisDAO();
        notisLogDAO = storageService.getNotisLogDAO();
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
                    NotisServer.this.stop();
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
                Logger.getLogger(NotisServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class NotisServiceImpl extends NotisServiceGrpc.NotisServiceImplBase {

        @Override
        public void getAllCamera(GetAllCameraRequest request, StreamObserver<GetAllCameraResponse> responseObserver) {
            GetAllCameraResponse.Builder builder = GetAllCameraResponse.newBuilder();

            try {
                List<NotisDTO> notisDTOs = notisDAO.getAll();

                for (NotisDTO notisDTO : notisDTOs) {
                    builder.addCameras(toCameraProto(notisDTO));
                }
                builder.setStatus(1);
            } catch (Exception ex) {
                builder.setStatus(2);
                LOG.log(Level.SEVERE, null, ex);
            } finally {
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
            }

        }

        @Override
        public void queryCamera(QueryCameraRequest request, StreamObserver<QueryCameraResponse> responseObserver) {
            QueryCameraResponse.Builder builder = QueryCameraResponse.newBuilder();

            try {
                String cameraId = request.getCameraId();
                long from = request.getFrom();
                long to = request.getTo();

                long total = notisLogDAO.queryNotis(cameraId, from, to);

                builder.setStatus(1);
                builder.setFrom(from);
                builder.setTo(to);
                builder.setCount(total);

            } catch (Exception ex) {
                builder.setStatus(2);
                LOG.log(Level.SEVERE, null, ex);
            } finally {
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
            }
        }

    }

    CameraProto toCameraProto(NotisDTO notisDTO) {
        CameraProto.Builder builder = CameraProto.newBuilder();

        builder.setCameraId(notisDTO.getCameraId());
        builder.setLocation(notisDTO.getLocation());

        return builder.build();
    }
}
