package com.aleksandrova.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
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

        try (FileReader reader = new FileReader("C:/Users/Elizaveta Alex&drova/Downloads/Telegram Desktop/ris4/messages.txt");
             Scanner scanner = new Scanner(reader)) {

            int i=0;
            String a[] = new String[5];
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                a[i]=str;
                i++;
            }
            a[i]=message;

            Arrays.sort(a, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });

            for (int j =0; j<a.length; j++){
                if(a[j]!=null && a[j]!="") {
                    messages.append(a[j]).append("\n");
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        try (FileWriter writer = new FileWriter("C:/Users/Elizaveta Alex&drova/Downloads/Telegram Desktop/ris4/messages.txt", false)) {
            writer.write(messages.toString());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
