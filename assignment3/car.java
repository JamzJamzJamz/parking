package assignment3;

public class car {
    public ticket lotTicket;
    public int ID;

    public car(int inID) {
        this.ID = inID;
    }

    public int getID() {
        return ID;
    }

    public void receiveTicket(ticket givenTicket) {
        lotTicket = givenTicket;
    }

    public ticket getTicket() {
        return lotTicket;
    }

    public boolean haveTicket() {
        return lotTicket != null;
    }


    
    
}