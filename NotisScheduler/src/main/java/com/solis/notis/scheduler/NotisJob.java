package com.solis.notis.scheduler;

import com.google.protobuf.Any;
import com.solis.common.utils.Utils;
import com.solis.notis.common.rabbit.RabbitSender;
import com.solis.notis.common.store.StorageService;
import com.solis.notis.common.store.dao.NotisDAO;
import com.solis.notis.common.store.dto.NotisDTO;
import com.solis.notis.protobuf.rabbit.CrawlImagePayload;
import com.solis.notis.protobuf.rabbit.MessageRequestProto;
import com.solis.notis.protobuf.rabbit.MessageTypeProto;
import java.util.List;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Võ Minh Trí
 */
public class NotisJob implements Job {

    private static final Logger LOG = Logger.getLogger(NotisJob.class.getName());

    RabbitSender sender = RabbitSender.getInstance();

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        long date = Utils.getCurrentUnix();
        LOG.info("Executing a task at " + date);

        NotisDAO notisDAO = StorageService.getInstance().getNotisDAO();
        List<NotisDTO> notisDTOs = notisDAO.getAll();

        for (NotisDTO notisDTO : notisDTOs) {
            CrawlImagePayload crawlImagePayload = CrawlImagePayload.newBuilder()
                    .setCameraId(notisDTO.getCameraId())
                    .build();

            MessageRequestProto messageRequestProto = MessageRequestProto.newBuilder()
                    .setPayload(Any.pack(crawlImagePayload))
                    .setMessageType(MessageTypeProto.IMAGE)
                    .setRequestTime(date)
                    .build();

            sender.putMessage(messageRequestProto);
        }
    }
}
