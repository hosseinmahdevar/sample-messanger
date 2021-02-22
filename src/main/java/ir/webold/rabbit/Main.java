package ir.webold.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final ObjectMapper mapper = new ObjectMapper();
    static final String HOST_NAME="localhost";

    public static void main(String[] args) throws JsonProcessingException {

        receive("rabbit_test_queue");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Message message = new Message("me", scanner.next());

            sendToFanoutExchange("rabbit.test", mapper.writeValueAsString(message));
        }
    }

    private static final String FANOUT_EXCHANGE_TYPE = "direct";

    public static void sendToFanoutExchange(String exchange, String message) {
        Sender sender = new Sender();
        sender.initialize();
        sender.send(exchange, FANOUT_EXCHANGE_TYPE, message);
        sender.destroy();
    }

    public static void receive(String queue) {
        Receiver receiver = new Receiver();
        receiver.initialize();
        try {
            receiver.comsue(queue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
