// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: notis.proto

package com.solis.notis.counter.protobuf;

/**
 * Protobuf type {@code solis.notis.counter.protobuf.NotisCounterResponse}
 */
public  final class NotisCounterResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:solis.notis.counter.protobuf.NotisCounterResponse)
    NotisCounterResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NotisCounterResponse.newBuilder() to construct.
  private NotisCounterResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NotisCounterResponse() {
    status_ = 0;
    counter_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NotisCounterResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            status_ = input.readInt32();
            break;
          }
          case 16: {

            counter_ = input.readInt64();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.solis.notis.counter.protobuf.Notis.internal_static_solis_notis_counter_protobuf_NotisCounterResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.solis.notis.counter.protobuf.Notis.internal_static_solis_notis_counter_protobuf_NotisCounterResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.solis.notis.counter.protobuf.NotisCounterResponse.class, com.solis.notis.counter.protobuf.NotisCounterResponse.Builder.class);
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private int status_;
  /**
   * <code>int32 status = 1;</code>
   */
  public int getStatus() {
    return status_;
  }

  public static final int COUNTER_FIELD_NUMBER = 2;
  private long counter_;
  /**
   * <code>int64 counter = 2;</code>
   */
  public long getCounter() {
    return counter_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (status_ != 0) {
      output.writeInt32(1, status_);
    }
    if (counter_ != 0L) {
      output.writeInt64(2, counter_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, status_);
    }
    if (counter_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, counter_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.solis.notis.counter.protobuf.NotisCounterResponse)) {
      return super.equals(obj);
    }
    com.solis.notis.counter.protobuf.NotisCounterResponse other = (com.solis.notis.counter.protobuf.NotisCounterResponse) obj;

    boolean result = true;
    result = result && (getStatus()
        == other.getStatus());
    result = result && (getCounter()
        == other.getCounter());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + getStatus();
    hash = (37 * hash) + COUNTER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getCounter());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.solis.notis.counter.protobuf.NotisCounterResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.solis.notis.counter.protobuf.NotisCounterResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code solis.notis.counter.protobuf.NotisCounterResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:solis.notis.counter.protobuf.NotisCounterResponse)
      com.solis.notis.counter.protobuf.NotisCounterResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.solis.notis.counter.protobuf.Notis.internal_static_solis_notis_counter_protobuf_NotisCounterResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.solis.notis.counter.protobuf.Notis.internal_static_solis_notis_counter_protobuf_NotisCounterResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.solis.notis.counter.protobuf.NotisCounterResponse.class, com.solis.notis.counter.protobuf.NotisCounterResponse.Builder.class);
    }

    // Construct using com.solis.notis.counter.protobuf.NotisCounterResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      status_ = 0;

      counter_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.solis.notis.counter.protobuf.Notis.internal_static_solis_notis_counter_protobuf_NotisCounterResponse_descriptor;
    }

    public com.solis.notis.counter.protobuf.NotisCounterResponse getDefaultInstanceForType() {
      return com.solis.notis.counter.protobuf.NotisCounterResponse.getDefaultInstance();
    }

    public com.solis.notis.counter.protobuf.NotisCounterResponse build() {
      com.solis.notis.counter.protobuf.NotisCounterResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.solis.notis.counter.protobuf.NotisCounterResponse buildPartial() {
      com.solis.notis.counter.protobuf.NotisCounterResponse result = new com.solis.notis.counter.protobuf.NotisCounterResponse(this);
      result.status_ = status_;
      result.counter_ = counter_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.solis.notis.counter.protobuf.NotisCounterResponse) {
        return mergeFrom((com.solis.notis.counter.protobuf.NotisCounterResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.solis.notis.counter.protobuf.NotisCounterResponse other) {
      if (other == com.solis.notis.counter.protobuf.NotisCounterResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.getCounter() != 0L) {
        setCounter(other.getCounter());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.solis.notis.counter.protobuf.NotisCounterResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.solis.notis.counter.protobuf.NotisCounterResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int status_ ;
    /**
     * <code>int32 status = 1;</code>
     */
    public int getStatus() {
      return status_;
    }
    /**
     * <code>int32 status = 1;</code>
     */
    public Builder setStatus(int value) {
      
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 status = 1;</code>
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private long counter_ ;
    /**
     * <code>int64 counter = 2;</code>
     */
    public long getCounter() {
      return counter_;
    }
    /**
     * <code>int64 counter = 2;</code>
     */
    public Builder setCounter(long value) {
      
      counter_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 counter = 2;</code>
     */
    public Builder clearCounter() {
      
      counter_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:solis.notis.counter.protobuf.NotisCounterResponse)
  }

  // @@protoc_insertion_point(class_scope:solis.notis.counter.protobuf.NotisCounterResponse)
  private static final com.solis.notis.counter.protobuf.NotisCounterResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.solis.notis.counter.protobuf.NotisCounterResponse();
  }

  public static com.solis.notis.counter.protobuf.NotisCounterResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NotisCounterResponse>
      PARSER = new com.google.protobuf.AbstractParser<NotisCounterResponse>() {
    public NotisCounterResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new NotisCounterResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NotisCounterResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NotisCounterResponse> getParserForType() {
    return PARSER;
  }

  public com.solis.notis.counter.protobuf.NotisCounterResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
