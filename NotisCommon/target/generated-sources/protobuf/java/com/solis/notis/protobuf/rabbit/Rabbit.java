// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rabbit.proto

package com.solis.notis.protobuf.rabbit;

public final class Rabbit {
  private Rabbit() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_solis_notis_protobuf_rabbit_CrawlImagePayload_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_solis_notis_protobuf_rabbit_CrawlImagePayload_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_solis_notis_protobuf_rabbit_MessageRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_solis_notis_protobuf_rabbit_MessageRequestProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014rabbit.proto\022\033solis.notis.protobuf.rab" +
      "bit\032\031google/protobuf/any.proto\"%\n\021CrawlI" +
      "magePayload\022\020\n\010cameraId\030\001 \001(\t\"\227\001\n\023Messag" +
      "eRequestProto\022C\n\014message_type\030\001 \001(\0162-.so" +
      "lis.notis.protobuf.rabbit.MessageTypePro" +
      "to\022\024\n\014request_time\030\002 \001(\003\022%\n\007payload\030\003 \001(" +
      "\0132\024.google.protobuf.Any*\035\n\020MessageTypePr" +
      "oto\022\t\n\005IMAGE\020\000B#\n\037com.solis.notis.protob" +
      "uf.rabbitP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.AnyProto.getDescriptor(),
        }, assigner);
    internal_static_solis_notis_protobuf_rabbit_CrawlImagePayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_solis_notis_protobuf_rabbit_CrawlImagePayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_solis_notis_protobuf_rabbit_CrawlImagePayload_descriptor,
        new java.lang.String[] { "CameraId", });
    internal_static_solis_notis_protobuf_rabbit_MessageRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_solis_notis_protobuf_rabbit_MessageRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_solis_notis_protobuf_rabbit_MessageRequestProto_descriptor,
        new java.lang.String[] { "MessageType", "RequestTime", "Payload", });
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
