package main.java;

import java.time.LocalDateTime;
import java.time.Duration;
import java.io.*;
import java.text.NumberFormat;

//import parking.ticket;
//import parking.car;
//import parking.lotGroup;

public class parkingLot {
    private int ID;
    private int maxCapacity;
    private int currentCapacity;
    private long idGenerator;
    private int carsEntered;
    private double revenue;
    private double rate;
    private double discount;
    private lotGroup group;

    public parkingLot(int cap, int lotID, lotGroup group) {
        maxCapacity = cap;
        currentCapacity = 0;
        idGenerator = 1;
        this.ID = lotID;
        carsEntered = 0;
        revenue = 0;
        this.group = group;
        group.addLot(this);
    } 

    public boolean isFull() {
        return currentCapacity == maxCapacity;
    }

    //Car Enters Lot
    //If there is capacity, the lot will allow the car in, and give them a ticket
    public boolean carEnter(car customer) {
        if((currentCapacity < maxCapacity) && !customer.haveTicket()) {
            LocalDateTime currentTime = LocalDateTime.now();
            ticket generated = new ticket(currentTime, idGenerator, customer.getID());
            customer.receiveTicket(generated);
            customer.setLot(this);
            currentCapacity++;
            idGenerator++;
            System.out.printf("Car %d has just entered lot %d(Group %s) at %tT\n", customer.getID(), getID(), group.getID(), currentTime);
            carsEntered++;
            return true;
        } else {
            if (currentCapacity == maxCapacity) {
                System.out.printf("Car %d cannot enter lot %d, filled to capacity\n", customer.getID(), getID());
            }
            return false;
        }
        
    }

    //Will take the ticket from the car
    //Will price based on how long it stayed
    public void carExit(car customer) {
        if(customer.haveTicket()) {
            ticket givenTicket = customer.getTicket();
            LocalDateTime currentTime = LocalDateTime.now();
            Long difference = Duration.between(givenTicket.getEntryTime(), currentTime).getSeconds();
            double price;
            if(difference > 5) {
                price = difference*rate*discount;
            } else {
                price = difference*rate;
            }
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            revenue += price;
            System.out.printf("Car %d has exited lot %d at %tT, it paid %s\n", givenTicket.getCar(), ID, currentTime, formatter.format(price));

            currentCapacity--;
        } else {
            System.out.println("Invalid Action");
        }
    }

    public int getID() {
        return ID;
    }

    public int getCurrentCap() {
        return currentCapacity;
    }

    public int getMaxCap() {
        return maxCapacity;
    }
    
    public void getHistory() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.printf("Lot %d Stats\nCars entered: %d\nTotal Revenue: %s\n", ID, carsEntered, formatter.format(revenue));
    }

    public void setRate(double groupRate) {
        rate = groupRate;
    }

    public void setDiscount(double groupDiscount) {
        discount = groupDiscount;
    }
   
}