package com.company;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

class Person {
    private final Date insertTime;
    private Date outTime;

    Person(Date initTime) {
        this.insertTime = initTime;
    }

    Date getInsertTime() {
        return insertTime;
    }

    void setOutTime(Date value) {
        this.outTime = value;
    }

    Date getOutTime() {
        return outTime;
    }

    long getSubWithInsertAndOutTime() {
        return outTime.getTime() - insertTime.getTime();
    }
}

public class MyQueue {
    public final Date initTime = new Date();
    private final Date currentTimeInsert = (Date) initTime.clone();
    private final Date currentTimeOut = (Date) initTime.clone();

    private final Queue<Person> queue = new LinkedList<>();

    void addPerson(int countSeconds) {
        currentTimeInsert.setTime(currentTimeInsert.getTime() + countSeconds * 1000L);
        queue.add(new Person((Date) currentTimeInsert.clone()));
    }

    Person pollPerson(int countSeconds) {
        Person currentPerson = queue.poll();
        assert currentPerson != null;

        if (currentTimeOut.getTime() < currentPerson.getInsertTime().getTime())
            currentTimeOut.setTime(currentPerson.getInsertTime().getTime() + countSeconds * 1000L);
        else
            currentTimeOut.setTime(currentTimeOut.getTime() + countSeconds * 1000L);

        currentPerson.setOutTime((Date) currentTimeOut.clone());

        return currentPerson;
    }
}
