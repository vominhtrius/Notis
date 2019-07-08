package com.solis.notis.counter.protobuf;

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
    comments = "Source: notis.proto")
public final class NotisCounterServiceGrpc {

  private NotisCounterServiceGrpc() {}

  public static final String SERVICE_NAME = "solis.notis.counter.protobuf.NotisCounterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.solis.notis.counter.protobuf.NotisCounterRequest,
      com.solis.notis.counter.protobuf.NotisCounterResponse> getCountVehicleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "countVehicle",
      requestType = com.solis.notis.counter.protobuf.NotisCounterRequest.class,
      responseType = com.solis.notis.counter.protobuf.NotisCounterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.solis.notis.counter.protobuf.NotisCounterRequest,
      com.solis.notis.counter.protobuf.NotisCounterResponse> getCountVehicleMethod() {
    io.grpc.MethodDescriptor<com.solis.notis.counter.protobuf.NotisCounterRequest, com.solis.notis.counter.protobuf.NotisCounterResponse> getCountVehicleMethod;
    if ((getCountVehicleMethod = NotisCounterServiceGrpc.getCountVehicleMethod) == null) {
      synchronized (NotisCounterServiceGrpc.class) {
        if ((getCountVehicleMethod = NotisCounterServiceGrpc.getCountVehicleMethod) == null) {
          NotisCounterServiceGrpc.getCountVehicleMethod = getCountVehicleMethod = 
              io.grpc.MethodDescriptor.<com.solis.notis.counter.protobuf.NotisCounterRequest, com.solis.notis.counter.protobuf.NotisCounterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "solis.notis.counter.protobuf.NotisCounterService", "countVehicle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solis.notis.counter.protobuf.NotisCounterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solis.notis.counter.protobuf.NotisCounterResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new NotisCounterServiceMethodDescriptorSupplier("countVehicle"))
                  .build();
          }
        }
     }
     return getCountVehicleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NotisCounterServiceStub newStub(io.grpc.Channel channel) {
    return new NotisCounterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NotisCounterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NotisCounterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NotisCounterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NotisCounterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NotisCounterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void countVehicle(com.solis.notis.counter.protobuf.NotisCounterRequest request,
        io.grpc.stub.StreamObserver<com.solis.notis.counter.protobuf.NotisCounterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCountVehicleMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCountVehicleMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.solis.notis.counter.protobuf.NotisCounterRequest,
                com.solis.notis.counter.protobuf.NotisCounterResponse>(
                  this, METHODID_COUNT_VEHICLE)))
          .build();
    }
  }

  /**
   */
  public static final class NotisCounterServiceStub extends io.grpc.stub.AbstractStub<NotisCounterServiceStub> {
    private NotisCounterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NotisCounterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NotisCounterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NotisCounterServiceStub(channel, callOptions);
    }

    /**
     */
    public void countVehicle(com.solis.notis.counter.protobuf.NotisCounterRequest request,
        io.grpc.stub.StreamObserver<com.solis.notis.counter.protobuf.NotisCounterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCountVehicleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NotisCounterServiceBlockingStub extends io.grpc.stub.AbstractStub<NotisCounterServiceBlockingStub> {
    private NotisCounterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NotisCounterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NotisCounterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NotisCounterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.solis.notis.counter.protobuf.NotisCounterResponse countVehicle(com.solis.notis.counter.protobuf.NotisCounterRequest request) {
      return blockingUnaryCall(
          getChannel(), getCountVehicleMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NotisCounterServiceFutureStub extends io.grpc.stub.AbstractStub<NotisCounterServiceFutureStub> {
    private NotisCounterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NotisCounterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NotisCounterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NotisCounterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.solis.notis.counter.protobuf.NotisCounterResponse> countVehicle(
        com.solis.notis.counter.protobuf.NotisCounterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCountVehicleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COUNT_VEHICLE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NotisCounterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NotisCounterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COUNT_VEHICLE:
          serviceImpl.countVehicle((com.solis.notis.counter.protobuf.NotisCounterRequest) request,
              (io.grpc.stub.StreamObserver<com.solis.notis.counter.protobuf.NotisCounterResponse>) responseObserver);
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

  private static abstract class NotisCounterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NotisCounterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.solis.notis.counter.protobuf.Notis.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NotisCounterService");
    }
  }

  private static final class NotisCounterServiceFileDescriptorSupplier
      extends NotisCounterServiceBaseDescriptorSupplier {
    NotisCounterServiceFileDescriptorSupplier() {}
  }

  private static final class NotisCounterServiceMethodDescriptorSupplier
      extends NotisCounterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NotisCounterServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (NotisCounterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NotisCounterServiceFileDescriptorSupplier())
              .addMethod(getCountVehicleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
