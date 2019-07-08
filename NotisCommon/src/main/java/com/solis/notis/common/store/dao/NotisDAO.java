package com.solis.notis.common.store.dao;

import com.solis.notis.common.store.dto.NotisDTO;
import java.util.List;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author Võ Minh Trí
 */
public class NotisDAO extends AbstractDAO<NotisDTO> {

    public NotisDAO(Datastore datastore) {
        super(datastore);
    }

    public NotisDTO get(String cameraId) {
        return datastore.createQuery(NotisDTO.class)
                .field("cameraId").equal(cameraId)
                .get();
    }

    public List<NotisDTO> getAll() {
        return datastore.createQuery(NotisDTO.class).asList();
    }
}
