package ir.webold.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
private final static String QUEUE_NAME = "event_queue";
    private final static Logger LOGGER =
        LoggerFactory.getLogger(Sender.class);
    private static final String DEFAULT_EXCHANGE = "";
    private Channel channel;
    private Connection connection;
private static final String FANOUT_EXCHANGE_TYPE = "direct";
    public void initialize() {
        try {
                 ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Main.HOST_NAME);
            factory.setUsername("livehesab");
            factory.setPassword("123456");
            connection = factory.newConnection();
            channel = connection.createChannel();


        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (TimeoutException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void comsue(String queue) throws IOException {
        channel.basicConsume(queue, new Consumer(this.channel));
    }
}
