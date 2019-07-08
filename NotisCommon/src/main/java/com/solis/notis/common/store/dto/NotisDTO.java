package com.solis.notis.common.store.dto;

import java.util.Date;
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
@Entity(value = "notis", noClassnameStored = true)
@Indexes(value = @Index(fields = @Field(value = "cameraId"), options = @IndexOptions(unique = true)))
public class NotisDTO {

    @Id
    private ObjectId id = new ObjectId();

    private String cameraId;
    private String location;

    private long _lastUpdated;

    public NotisDTO() {
    }

    public NotisDTO(String cameraId, String location) {
        this.cameraId = cameraId;
        this.location = location;
    }

    @PrePersist
    void prePersist() {
        _lastUpdated = new Date().getTime() / 1000L;
    }

    public String getCameraId() {
        return cameraId;
    }

    public String getLocation() {
        return location;
    }
}
