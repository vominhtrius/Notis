package com.solis.notis.common.store.task;

import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.store.dao.NotisLogDAO;
import com.solis.notis.common.store.dto.NotisLogDTO;

/**
 *
 * @author Võ Minh Trí
 */
public class SaveNotisLogTask extends StorageTask {

    NotisLogDTO logDTO;

    public SaveNotisLogTask(NotisLogDTO logDTO) {
        this.logDTO = logDTO;
    }

    @Override
    public void process(StorageService service) {
        NotisLogDAO notisLogDAO = service.getNotisLogDAO();
        notisLogDAO.save(logDTO);
    }
}
