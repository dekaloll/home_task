package org.PL_hometask;
import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.implementation.SessionImpl;


import java.io.*;

public class Task2 {
    public static void main(String[] args) throws DummyException, IOException, InterruptedException {
        ConnectionImpl connection = new ConnectionImpl();
        SessionImpl session = (SessionImpl) connection.createSession(true);
        DestinationImpl destination = (DestinationImpl) session.createDestination("Говорит Москва");
        ProducerImpl producer = (ProducerImpl) session.createProducer(destination);

        if(args.length > 0) {
            FileReader file = new FileReader(args[0]);
            BufferedReader reader = new BufferedReader(file);
            while (true) {
                String line = reader.readLine();
                producer.send(line);
                Thread.sleep(2000);
                if (reader.ready() == false) {
                    file = new FileReader(args[0]);
                    reader = new BufferedReader(file);
                }


            }
        }
    connection.close();
    }
}