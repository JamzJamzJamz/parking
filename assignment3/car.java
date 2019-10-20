package assignment3;

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


    
    
}