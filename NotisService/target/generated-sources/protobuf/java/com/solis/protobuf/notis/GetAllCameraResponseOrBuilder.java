// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: notis_service.proto

package com.solis.protobuf.notis;

public interface GetAllCameraResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:solis.service.notis.api.proto.GetAllCameraResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   */
  int getStatus();

  /**
   * <code>repeated .solis.service.notis.api.proto.CameraProto cameras = 2;</code>
   */
  java.util.List<com.solis.protobuf.notis.CameraProto> 
      getCamerasList();
  /**
   * <code>repeated .solis.service.notis.api.proto.CameraProto cameras = 2;</code>
   */
  com.solis.protobuf.notis.CameraProto getCameras(int index);
  /**
   * <code>repeated .solis.service.notis.api.proto.CameraProto cameras = 2;</code>
   */
  int getCamerasCount();
  /**
   * <code>repeated .solis.service.notis.api.proto.CameraProto cameras = 2;</code>
   */
  java.util.List<? extends com.solis.protobuf.notis.CameraProtoOrBuilder> 
      getCamerasOrBuilderList();
  /**
   * <code>repeated .solis.service.notis.api.proto.CameraProto cameras = 2;</code>
   */
  com.solis.protobuf.notis.CameraProtoOrBuilder getCamerasOrBuilder(
      int index);
}
