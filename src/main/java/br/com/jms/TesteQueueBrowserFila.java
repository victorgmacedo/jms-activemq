package br.com.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class TesteQueueBrowserFila {

    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = (Destination) context.lookup("financeiro");
        QueueBrowser browser = session.createBrowser((Queue) destination);

        while(browser.getEnumeration().hasMoreElements()){
            TextMessage msg = (TextMessage) browser.getEnumeration().nextElement();
            System.out.println("Message: " + msg.getText());
        }

        session.close();
        connection.close();
        context.close();
    }

}
