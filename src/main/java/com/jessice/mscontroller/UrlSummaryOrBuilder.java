// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: msgShare_controller.proto

package com.jessice.mscontroller;

public interface UrlSummaryOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mscontroller.UrlSummary)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The number of url received.接收到url的数量
   * </pre>
   *
   * <code>int32 url_count = 1;</code>
   */
  int getUrlCount();

  /**
   * <pre>
   *遍历路线时传递的已知矩阵的数量。
   * </pre>
   *
   * <code>int32 matrix_count = 2;</code>
   */
  int getMatrixCount();

  /**
   * <pre>
   * The distance covered in metres.距离以米为单位
   * </pre>
   *
   * <code>int32 distance = 3;</code>
   */
  int getDistance();

  /**
   * <pre>
   * The duration of the traversal in seconds.遍历的持续时间以秒为单位
   * </pre>
   *
   * <code>int32 elapsed_time = 4;</code>
   */
  int getElapsedTime();
}
