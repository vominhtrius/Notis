package com.solis.notis.common.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.solis.notis.protobuf.rabbit.MessageRequestProto;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class RabbitReceiver {

    private static final Logger LOG = Logger.getLogger(RabbitReceiver.class.getName());

    String host;
    String taskQueueName;
    int port;

    ConnectionFactory factory = new ConnectionFactory();
    Connection connection;
    Channel channel;
    boolean initDone = false;
    MessageHandler handleMessage;

    public RabbitReceiver() {
    }

    public RabbitReceiver(MessageHandler handleMessage) {
        this.handleMessage = handleMessage;
    }

    public void initialize(String host, int port, String queueName, String user, String password) {
        try {
            LOG.info("initalizing host=" + host + " port=" + port + " queueName= " + queueName);

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(user);
            factory.setPassword(password);

            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicQos(1);
            taskQueueName = queueName;

            initDone = true;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        MessageRequestProto messageRequest = MessageRequestProto.parseFrom(delivery.getBody());
        try {

            handleMessage.handleMesssage(messageRequest);

            //monitorService.putMessage(toTaskMessage(messageRequest));
        } finally {
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    };

//    private TaskMessage toTaskMessage(MessageRequestProto messageRequest) {
//        try {
//            PagePayloadProto pagePayloadProto = messageRequest.getPayload().unpack(PagePayloadProto.class);
//
//            PagePayload pagePayload = new PagePayload(
//                    pagePayloadProto,
//                    messageRequest.getRequestTime()
//            );
//
//            return new TaskMessage(TaskMessage.Type.Page, pagePayload, messageRequest.getRequestTime());
//        } catch (InvalidProtocolBufferException ex) {
//            Logger.getLogger(RabbitReceiver.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
    public void serve() {
        if (initDone == false) {
            System.out.println("can't serve");
            return;
        }

        try {
            LOG.log(Level.INFO, " [*] Waiting for messages. To exit press CTRL+C");

            channel.basicConsume(taskQueueName, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void shutdown() {
        try {
            channel.close();
        } catch (IOException ex) {
            Logger.getLogger(RabbitReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(RabbitReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
