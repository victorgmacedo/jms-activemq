package br.com.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutorFila {

    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = (Destination) context.lookup("financeiro");
        Message message = session.createTextMessage("Mensage de Teste");
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 50; i++) {
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();

    }

}
