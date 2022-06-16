package com.project.systemeventsver2;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: logService.proto")
public final class LogServiceGrpc {

  private LogServiceGrpc() {}

  public static final String SERVICE_NAME = "com.project.systemeventsver2.LogService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.project.systemeventsver2.LogRequest,
      com.project.systemeventsver2.LogResponse> METHOD_LOG =
      io.grpc.MethodDescriptor.<com.project.systemeventsver2.LogRequest, com.project.systemeventsver2.LogResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.project.systemeventsver2.LogService", "log"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.project.systemeventsver2.LogRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.project.systemeventsver2.LogResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LogServiceStub newStub(io.grpc.Channel channel) {
    return new LogServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LogServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LogServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LogServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LogServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LogServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void log(com.project.systemeventsver2.LogRequest request,
        io.grpc.stub.StreamObserver<com.project.systemeventsver2.LogResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOG, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LOG,
            asyncUnaryCall(
              new MethodHandlers<
                com.project.systemeventsver2.LogRequest,
                com.project.systemeventsver2.LogResponse>(
                  this, METHODID_LOG)))
          .build();
    }
  }

  /**
   */
  public static final class LogServiceStub extends io.grpc.stub.AbstractStub<LogServiceStub> {
    private LogServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogServiceStub(channel, callOptions);
    }

    /**
     */
    public void log(com.project.systemeventsver2.LogRequest request,
        io.grpc.stub.StreamObserver<com.project.systemeventsver2.LogResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOG, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LogServiceBlockingStub extends io.grpc.stub.AbstractStub<LogServiceBlockingStub> {
    private LogServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.project.systemeventsver2.LogResponse log(com.project.systemeventsver2.LogRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOG, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LogServiceFutureStub extends io.grpc.stub.AbstractStub<LogServiceFutureStub> {
    private LogServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.project.systemeventsver2.LogResponse> log(
        com.project.systemeventsver2.LogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOG, getCallOptions()), request);
    }
  }

  private static final int METHODID_LOG = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LogServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOG:
          serviceImpl.log((com.project.systemeventsver2.LogRequest) request,
              (io.grpc.stub.StreamObserver<com.project.systemeventsver2.LogResponse>) responseObserver);
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

  private static final class LogServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.project.systemeventsver2.LogServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LogServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LogServiceDescriptorSupplier())
              .addMethod(METHOD_LOG)
              .build();
        }
      }
    }
    return result;
  }
}
