package com.solis.notis.common.rabbit;

import com.solis.notis.protobuf.rabbit.MessageRequestProto;

/**
 *
 * @author Võ Minh Trí
 */
public interface MessageHandler {
    void handleMesssage(MessageRequestProto messageRequestProto);
}
