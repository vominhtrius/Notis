package com.solis.notis.common.store.dto;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.PrePersist;

/**
 *
 * @author Võ Minh Trí
 */
@Entity(value = "notis_log", noClassnameStored = true)
@Indexes(value = @Index(fields = @Field(value = "cameraId"), options = @IndexOptions(unique = true)))
public class NotisLogDTO {

    @Id
    private ObjectId id = new ObjectId();

    private String cameraId;
    private long counter;
    private long date;
    private long _lastUpdated;

    @PrePersist
    void prePersist() {
        _lastUpdated = new Date().getTime() / 1000L;
    }

    public NotisLogDTO() {
    }

    public NotisLogDTO(String cameraId, long counter, long date) {
        this.cameraId = cameraId;
        this.counter = counter;
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public String getCameraId() {
        return cameraId;
    }

    public long getCounter() {
        return counter;
    }

    public long getDate() {
        return date;
    }
}
