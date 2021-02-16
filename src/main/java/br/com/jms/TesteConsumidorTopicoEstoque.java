package br.com.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorTopicoEstoque {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID("estoque");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = (Topic) context.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topic, "assinatura");
        consumer.setMessageListener(new MessageReader());

        new Scanner(System.in).next();

        session.close();
        connection.close();
        context.close();

    }

}
