package com.solis.protobuf.notis;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: notis_service.proto")
public final class NotisServiceGrpc {

  private NotisServiceGrpc() {}

  public static final String SERVICE_NAME = "solis.service.notis.api.proto.NotisService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.solis.protobuf.notis.GetAllCameraRequest,
      com.solis.protobuf.notis.GetAllCameraResponse> getGetAllCameraMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllCamera",
      requestType = com.solis.protobuf.notis.GetAllCameraRequest.class,
      responseType = com.solis.protobuf.notis.GetAllCameraResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.solis.protobuf.notis.GetAllCameraRequest,
      com.solis.protobuf.notis.GetAllCameraResponse> getGetAllCameraMethod() {
    io.grpc.MethodDescriptor<com.solis.protobuf.notis.GetAllCameraRequest, com.solis.protobuf.notis.GetAllCameraResponse> getGetAllCameraMethod;
    if ((getGetAllCameraMethod = NotisServiceGrpc.getGetAllCameraMethod) == null) {
      synchronized (NotisServiceGrpc.class) {
        if ((getGetAllCameraMethod = NotisServiceGrpc.getGetAllCameraMethod) == null) {
          NotisServiceGrpc.getGetAllCameraMethod = getGetAllCameraMethod = 
              io.grpc.MethodDescriptor.<com.solis.protobuf.notis.GetAllCameraRequest, com.solis.protobuf.notis.GetAllCameraResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "solis.service.notis.api.proto.NotisService", "getAllCamera"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solis.protobuf.notis.GetAllCameraRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solis.protobuf.notis.GetAllCameraResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new NotisServiceMethodDescriptorSupplier("getAllCamera"))
                  .build();
          }
        }
     }
     return getGetAllCameraMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.solis.protobuf.notis.QueryCameraRequest,
      com.solis.protobuf.notis.QueryCameraResponse> getQueryCameraMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryCamera",
      requestType = com.solis.protobuf.notis.QueryCameraRequest.class,
      responseType = com.solis.protobuf.notis.QueryCameraResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.solis.protobuf.notis.QueryCameraRequest,
      com.solis.protobuf.notis.QueryCameraResponse> getQueryCameraMethod() {
    io.grpc.MethodDescriptor<com.solis.protobuf.notis.QueryCameraRequest, com.solis.protobuf.notis.QueryCameraResponse> getQueryCameraMethod;
    if ((getQueryCameraMethod = NotisServiceGrpc.getQueryCameraMethod) == null) {
      synchronized (NotisServiceGrpc.class) {
        if ((getQueryCameraMethod = NotisServiceGrpc.getQueryCameraMethod) == null) {
          NotisServiceGrpc.getQueryCameraMethod = getQueryCameraMethod = 
              io.grpc.MethodDescriptor.<com.solis.protobuf.notis.QueryCameraRequest, com.solis.protobuf.notis.QueryCameraResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "solis.service.notis.api.proto.NotisService", "queryCamera"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solis.protobuf.notis.QueryCameraRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solis.protobuf.notis.QueryCameraResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new NotisServiceMethodDescriptorSupplier("queryCamera"))
                  .build();
          }
        }
     }
     return getQueryCameraMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NotisServiceStub newStub(io.grpc.Channel channel) {
    return new NotisServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NotisServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NotisServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NotisServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NotisServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NotisServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAllCamera(com.solis.protobuf.notis.GetAllCameraRequest request,
        io.grpc.stub.StreamObserver<com.solis.protobuf.notis.GetAllCameraResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllCameraMethod(), responseObserver);
    }

    /**
     */
    public void queryCamera(com.solis.protobuf.notis.QueryCameraRequest request,
        io.grpc.stub.StreamObserver<com.solis.protobuf.notis.QueryCameraResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryCameraMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAllCameraMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.solis.protobuf.notis.GetAllCameraRequest,
                com.solis.protobuf.notis.GetAllCameraResponse>(
                  this, METHODID_GET_ALL_CAMERA)))
          .addMethod(
            getQueryCameraMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.solis.protobuf.notis.QueryCameraRequest,
                com.solis.protobuf.notis.QueryCameraResponse>(
                  this, METHODID_QUERY_CAMERA)))
          .build();
    }
  }

  /**
   */
  public static final class NotisServiceStub extends io.grpc.stub.AbstractStub<NotisServiceStub> {
    private NotisServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NotisServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NotisServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NotisServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAllCamera(com.solis.protobuf.notis.GetAllCameraRequest request,
        io.grpc.stub.StreamObserver<com.solis.protobuf.notis.GetAllCameraResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllCameraMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryCamera(com.solis.protobuf.notis.QueryCameraRequest request,
        io.grpc.stub.StreamObserver<com.solis.protobuf.notis.QueryCameraResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryCameraMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NotisServiceBlockingStub extends io.grpc.stub.AbstractStub<NotisServiceBlockingStub> {
    private NotisServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NotisServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NotisServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NotisServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.solis.protobuf.notis.GetAllCameraResponse getAllCamera(com.solis.protobuf.notis.GetAllCameraRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAllCameraMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.solis.protobuf.notis.QueryCameraResponse queryCamera(com.solis.protobuf.notis.QueryCameraRequest request) {
      return blockingUnaryCall(
          getChannel(), getQueryCameraMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NotisServiceFutureStub extends io.grpc.stub.AbstractStub<NotisServiceFutureStub> {
    private NotisServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NotisServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NotisServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NotisServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.solis.protobuf.notis.GetAllCameraResponse> getAllCamera(
        com.solis.protobuf.notis.GetAllCameraRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllCameraMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.solis.protobuf.notis.QueryCameraResponse> queryCamera(
        com.solis.protobuf.notis.QueryCameraRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryCameraMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALL_CAMERA = 0;
  private static final int METHODID_QUERY_CAMERA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NotisServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NotisServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ALL_CAMERA:
          serviceImpl.getAllCamera((com.solis.protobuf.notis.GetAllCameraRequest) request,
              (io.grpc.stub.StreamObserver<com.solis.protobuf.notis.GetAllCameraResponse>) responseObserver);
          break;
        case METHODID_QUERY_CAMERA:
          serviceImpl.queryCamera((com.solis.protobuf.notis.QueryCameraRequest) request,
              (io.grpc.stub.StreamObserver<com.solis.protobuf.notis.QueryCameraResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NotisServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NotisServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.solis.protobuf.notis.NotisServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NotisService");
    }
  }

  private static final class NotisServiceFileDescriptorSupplier
      extends NotisServiceBaseDescriptorSupplier {
    NotisServiceFileDescriptorSupplier() {}
  }

  private static final class NotisServiceMethodDescriptorSupplier
      extends NotisServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NotisServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NotisServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NotisServiceFileDescriptorSupplier())
              .addMethod(getGetAllCameraMethod())
              .addMethod(getQueryCameraMethod())
              .build();
        }
      }
    }
    return result;
  }
}
