package br.com.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteConsumidorFila {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageReader());

        session.close();
        connection.close();
        context.close();

    }

}
