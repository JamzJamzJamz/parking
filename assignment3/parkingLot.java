package assignment3;

import java.time.LocalDateTime;
import java.time.Duration;
import java.io.*;
import assignment3.ticket;
import assignment3.car;

public class parkingLot {
    private int ID;
    private int maxCapacity;
    private int currentCapacity;
    private long idGenerator;

    public parkingLot(int cap, int lotID) {
        maxCapacity = cap;
        currentCapacity = 0;
        idGenerator = 1;
        this.ID = lotID;
    } 

    public boolean isFull() {
        return currentCapacity == maxCapacity;
    }

    public boolean carEnter(car customer) {
        if((currentCapacity < maxCapacity) && !customer.haveTicket()) {
            LocalDateTime currentTime = LocalDateTime.now();
            ticket generated = new ticket(currentTime, idGenerator, customer.getID());
            customer.receiveTicket(generated);
            customer.setLot(this);
            currentCapacity++;
            idGenerator++;
            System.out.printf("Car %d has just entered lot %d\n", customer.getID(), getID());
            return true;
        } else {
            if (currentCapacity == maxCapacity) {
                System.out.printf("Car %d cannot enter lot %d, filled to capacity\n", customer.getID(), getID());
            }
            return false;
        }
        
    }

    public void carExit(ticket givenTicket) {
        LocalDateTime currentTime = LocalDateTime.now();
        Long difference = Duration.between(givenTicket.getEntryTime(), currentTime).getSeconds();

        System.out.printf("Car %d has exited lot %d, it stayed for %d seconds\n", givenTicket.getCar(), ID, difference);

        currentCapacity--;
    }

    public int getID() {
        return ID;
    }
   
}