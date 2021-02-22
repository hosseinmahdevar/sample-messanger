package ir.webold.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer extends DefaultConsumer {

    public Consumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        super.handleDelivery(consumerTag, envelope, properties, body);
        Message message = Main.mapper.readValue(new String(body, StandardCharsets.UTF_8),Message.class);
        System.out.println(message.getSender() +" : "+ message.getBody());
        this.getChannel().basicAck(envelope.getDeliveryTag(),false);

    }

}
