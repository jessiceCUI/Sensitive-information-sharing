package com.jessice.mscontroller;

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
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: msgShare_controller.proto")
public class MsgShareControllerGrpc {

  private MsgShareControllerGrpc() {}

  public static final String SERVICE_NAME = "mscontroller.MsgShareController";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jessice.mscontroller.UUrl,
      com.jessice.mscontroller.Matrix> METHOD_GET_MATRIX =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "mscontroller.MsgShareController", "GetMatrix"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.UUrl.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.Matrix.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jessice.mscontroller.RectangleUrl,
      com.jessice.mscontroller.Matrix> METHOD_LIST_MATRIXS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "mscontroller.MsgShareController", "ListMatrixs"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.RectangleUrl.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.Matrix.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jessice.mscontroller.UUrl,
      com.jessice.mscontroller.UrlSummary> METHOD_RECORD_URL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "mscontroller.MsgShareController", "RecordUrl"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.UUrl.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.UrlSummary.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jessice.mscontroller.UrlNote,
      com.jessice.mscontroller.UrlNote> METHOD_URL_CHAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "mscontroller.MsgShareController", "UrlChat"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.UrlNote.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.jessice.mscontroller.UrlNote.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgShareControllerStub newStub(io.grpc.Channel channel) {
    return new MsgShareControllerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgShareControllerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MsgShareControllerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MsgShareControllerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MsgShareControllerFutureStub(channel);
  }

  /**
   */
  public static abstract class MsgShareControllerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *一个简单的rpc
     * </pre>
     */
    public void getMatrix(com.jessice.mscontroller.UUrl request,
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.Matrix> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MATRIX, responseObserver);
    }

    /**
     * <pre>
     *服务端流式rpc
     * </pre>
     */
    public void listMatrixs(com.jessice.mscontroller.RectangleUrl request,
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.Matrix> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST_MATRIXS, responseObserver);
    }

    /**
     * <pre>
     *客户端流式rpc
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.jessice.mscontroller.UUrl> recordUrl(
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlSummary> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_RECORD_URL, responseObserver);
    }

    /**
     * <pre>
     *双向流式rpc
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlNote> urlChat(
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlNote> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_URL_CHAT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_MATRIX,
            asyncUnaryCall(
              new MethodHandlers<
                com.jessice.mscontroller.UUrl,
                com.jessice.mscontroller.Matrix>(
                  this, METHODID_GET_MATRIX)))
          .addMethod(
            METHOD_LIST_MATRIXS,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.jessice.mscontroller.RectangleUrl,
                com.jessice.mscontroller.Matrix>(
                  this, METHODID_LIST_MATRIXS)))
          .addMethod(
            METHOD_RECORD_URL,
            asyncClientStreamingCall(
              new MethodHandlers<
                com.jessice.mscontroller.UUrl,
                com.jessice.mscontroller.UrlSummary>(
                  this, METHODID_RECORD_URL)))
          .addMethod(
            METHOD_URL_CHAT,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.jessice.mscontroller.UrlNote,
                com.jessice.mscontroller.UrlNote>(
                  this, METHODID_URL_CHAT)))
          .build();
    }
  }

  /**
   */
  public static final class MsgShareControllerStub extends io.grpc.stub.AbstractStub<MsgShareControllerStub> {
    private MsgShareControllerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MsgShareControllerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgShareControllerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MsgShareControllerStub(channel, callOptions);
    }

    /**
     * <pre>
     *一个简单的rpc
     * </pre>
     */
    public void getMatrix(com.jessice.mscontroller.UUrl request,
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.Matrix> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MATRIX, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *服务端流式rpc
     * </pre>
     */
    public void listMatrixs(com.jessice.mscontroller.RectangleUrl request,
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.Matrix> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_LIST_MATRIXS, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *客户端流式rpc
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.jessice.mscontroller.UUrl> recordUrl(
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlSummary> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_RECORD_URL, getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *双向流式rpc
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlNote> urlChat(
        io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlNote> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_URL_CHAT, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class MsgShareControllerBlockingStub extends io.grpc.stub.AbstractStub<MsgShareControllerBlockingStub> {
    private MsgShareControllerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MsgShareControllerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgShareControllerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MsgShareControllerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *一个简单的rpc
     * </pre>
     */
    public com.jessice.mscontroller.Matrix getMatrix(com.jessice.mscontroller.UUrl request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MATRIX, getCallOptions(), request);
    }

    /**
     * <pre>
     *服务端流式rpc
     * </pre>
     */
    public java.util.Iterator<com.jessice.mscontroller.Matrix> listMatrixs(
        com.jessice.mscontroller.RectangleUrl request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_LIST_MATRIXS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MsgShareControllerFutureStub extends io.grpc.stub.AbstractStub<MsgShareControllerFutureStub> {
    private MsgShareControllerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MsgShareControllerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgShareControllerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MsgShareControllerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *一个简单的rpc
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jessice.mscontroller.Matrix> getMatrix(
        com.jessice.mscontroller.UUrl request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MATRIX, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MATRIX = 0;
  private static final int METHODID_LIST_MATRIXS = 1;
  private static final int METHODID_RECORD_URL = 2;
  private static final int METHODID_URL_CHAT = 3;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgShareControllerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(MsgShareControllerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_MATRIX:
          serviceImpl.getMatrix((com.jessice.mscontroller.UUrl) request,
              (io.grpc.stub.StreamObserver<com.jessice.mscontroller.Matrix>) responseObserver);
          break;
        case METHODID_LIST_MATRIXS:
          serviceImpl.listMatrixs((com.jessice.mscontroller.RectangleUrl) request,
              (io.grpc.stub.StreamObserver<com.jessice.mscontroller.Matrix>) responseObserver);
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
        case METHODID_RECORD_URL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.recordUrl(
              (io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlSummary>) responseObserver);
        case METHODID_URL_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.urlChat(
              (io.grpc.stub.StreamObserver<com.jessice.mscontroller.UrlNote>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_GET_MATRIX,
        METHOD_LIST_MATRIXS,
        METHOD_RECORD_URL,
        METHOD_URL_CHAT);
  }

}
