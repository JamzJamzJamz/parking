package assignment3;

import java.time.LocalDateTime;
import java.time.Duration;
import java.io.*;
import assignment3.ticket;
import assignment3.car;

public class parkingLot {
    private int maxCapacity;
    private int currentCapacity;
    private long idGenerator;

    public parkingLot(int cap) {
        maxCapacity = cap;
        currentCapacity = 0;
        idGenerator = 1;
    } 

    public boolean isFull() {
        return currentCapacity == maxCapacity;
    }

    public void carEnter(car customer) {
        if((currentCapacity < maxCapacity) && !customer.haveTicket()) {
            LocalDateTime currentTime = LocalDateTime.now();
            ticket generated = new ticket(currentTime, idGenerator, customer.getID());
            customer.receiveTicket(generated);
            currentCapacity++;
            idGenerator++;
            System.out.printf("Car %d has just entered\n", customer.getID());
        } else {
            if (currentCapacity == maxCapacity) {
                System.out.println("Garage filled to capacity");
            }
        }
        
    }

    public void carExit(ticket givenTicket) {
        LocalDateTime currentTime = LocalDateTime.now();
        Long difference = Duration.between(givenTicket.getEntryTime(), currentTime).getSeconds();

        System.out.printf("Car %d has been here for %d seconds\n", givenTicket.getCar(), difference);

        currentCapacity--;
    }
   
}