package org.PL_hometask;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.implementation.SessionImpl;


import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) throws InterruptedException, DummyException {
        ConnectionImpl conn = new ConnectionImpl();
        SessionImpl sess = (SessionImpl) conn.createSession(true);
        DestinationImpl dest = (DestinationImpl) sess.createDestination("Поток пошел");
        ProducerImpl prod = (ProducerImpl) sess.createProducer(dest);

        ArrayList<String> myList = new ArrayList<>();
        myList.add("Четыре");
        myList.add("Пять");
        myList.add("Шесть");

        for(String s : myList) {
            prod.send(s);
            Thread.sleep(2000);
        }
        conn.close();
    }
}