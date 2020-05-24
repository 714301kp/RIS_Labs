package com.zabello.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Queue")
        , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageBean implements MessageListener {
    static final Logger logger = Logger.getLogger("MessageBean");

    @Override
    public void onMessage(Message message) {

        TextMessage msg;

        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                deleteFromFile(msg.getText());

            } else {
                logger.warning("Wrong type");
            }
        } catch (JMSException e) {
            e.getMessage();
        }
    }

    private void deleteFromFile(String message) {
        StringBuilder messages = new StringBuilder();

        try (FileReader reader = new FileReader("E:/вучоба/РИС/labs/ris4/messages.txt");
             Scanner scanner = new Scanner(reader)) {

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (!str.equals(message))
                    messages.append(str).append("\n");
            }

        } catch (IOException e) {
            e.getMessage();
        }
        try (FileWriter writer = new FileWriter("E:/вучоба/РИС/labs/ris4/messages.txt", false)) {
            writer.write(messages.toString());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
