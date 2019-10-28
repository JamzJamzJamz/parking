package main.java;

public class car {
    private ticket lotTicket;
    private int ID;
    private parkingLot occupying;

    public car(int inID) {
        this.ID = inID;
    }

    public int getID() {
        return ID;
    }

    public ticket getTicket() {
        return lotTicket;
    }

    public parkingLot getLot() {
        return occupying;
    }

    public void setLot(parkingLot enteredLot) {
        occupying = enteredLot;
    }

    public void receiveTicket(ticket givenTicket) {
        lotTicket = givenTicket;
    }

    public void giveTicket() {
        lotTicket = null;
    }

    public boolean haveTicket() {
        return lotTicket != null;
    }

    public boolean enterLot(parkingLot entering) {
        if(occupying == null && entering != null) {
            return entering.carEnter(this);
        } else {
            System.out.printf("Car %d cannot enter lot %d, already occupying a lot\n", ID, entering.getID());
            return false;
        }
    }

    public void exitLot() {
        if(occupying != null) {
            occupying.carExit(this);
        } else {
            System.out.printf("Car %d cannot exit, not occupying a lot\n", ID);
        }
    }
    
    
}