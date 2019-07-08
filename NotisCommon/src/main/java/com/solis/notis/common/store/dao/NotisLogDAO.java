package com.solis.notis.common.store.dao;

import com.solis.notis.common.store.dto.NotisLogDTO;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Võ Minh Trí
 */
public class NotisLogDAO extends AbstractDAO<NotisLogDTO> {

    public NotisLogDAO(Datastore datastore) {
        super(datastore);
    }

    public long queryNotis(String cameraId, long from, long to) {

        Query<NotisLogDTO> query = datastore.createQuery(NotisLogDTO.class);
        query.and(
                query.criteria("cameraId").equal(cameraId),
                query.criteria("date").greaterThanOrEq(from),
                query.criteria("date").lessThanOrEq(to));

        List<NotisLogDTO> notisLogDTOs = query.asList();

        long res = 0;

        for (NotisLogDTO logDTO : notisLogDTOs) {
            res += logDTO.getCounter();
        }

        return res;
    }
}
