package com.solis.notis.common.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.solis.notis.common.core.AbstractConsumer;
import com.solis.notis.common.core.AbstractProducer;
import com.solis.notis.common.core.AbstractService;
import com.solis.notis.common.core.AbstractWorker;
import com.solis.notis.common.core.MessageWraper;
import com.solis.notis.protobuf.rabbit.MessageRequestProto;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class RabbitSender extends AbstractService<MessageRequestProto> {

    private static final Logger LOG = Logger.getLogger(RabbitSender.class.getName());

    ConnectionFactory factory = new ConnectionFactory();
    private String queueName;
    private String host;
    private int port;

    private static RabbitSender instance;

    public static RabbitSender getInstance() {
        if (instance == null) {
            synchronized (RabbitSender.class) {
                if (instance == null) {
                    instance = new RabbitSender();
                }
            }
        }

        return instance;
    }

    private RabbitSender() {
    }

    public void initialize(String host, int port, String queueName, int nWorker, String user, String password) {
        LOG.info("RabbitSender initialize with host=" + host + "port=" + port + " queueName=" + queueName + " nWorker=" + nWorker);

        this.setNWorker(nWorker);
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(user);
        factory.setPassword(password);

        this.queueName = queueName;
    }

    @Override
    protected AbstractConsumer<MessageWraper<MessageRequestProto>> newConsumer() {
        return new AbstractConsumer<MessageWraper<MessageRequestProto>>(queue) {
        };
    }

    @Override
    protected AbstractProducer<MessageWraper<MessageRequestProto>> newProducer() {
        return new AbstractProducer<MessageWraper<MessageRequestProto>>(queue) {
        };
    }

    @Override
    protected AbstractWorker<MessageRequestProto> newWorker(AbstractConsumer<MessageWraper<MessageRequestProto>> consumer) {
        return new SendMessageWorker(consumer, runningThread);
    }

    class SendMessageWorker extends AbstractWorker<MessageRequestProto> {

        Connection connection;
        Channel channel;
        boolean initDone = false;

        public SendMessageWorker(AbstractConsumer<MessageWraper<MessageRequestProto>> consumer, AtomicInteger runningThread) {
            super(consumer, runningThread);
            try {
                connection = factory.newConnection();
                channel = connection.createChannel();
                channel.queueDeclare(queueName, true, false, false, null);
                initDone = true;
            } catch (IOException ex) {
                Logger.getLogger(RabbitSender.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TimeoutException ex) {
                Logger.getLogger(RabbitSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        protected void processMessage(MessageRequestProto message) {
            // gửi message tới abcxyz
            if (initDone) {
                try {
                    channel.basicPublish("", queueName, MessageProperties.BASIC, message.toByteArray());
                } catch (IOException ex) {
                    Logger.getLogger(RabbitSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                LOG.log(Level.SEVERE, "Can't connect to rabbit with host=" + host + " port=" + port + " queueName=" + queueName);
            }
        }
    }
}
