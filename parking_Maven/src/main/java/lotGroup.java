package main.java;

import java.util.ArrayList;
//import parking.parkingLot;

public class lotGroup {

    private ArrayList<parkingLot> lots;
    private double price;
    private double discount;
    private int groupID;

    public lotGroup(int ID, double rate, double discount) {
        this.groupID = ID;
        this.price = rate;
        this.discount = discount;
        lots = new ArrayList<parkingLot>();
    }

    public void addLot(parkingLot newLot) {
        newLot.setRate(price);
        newLot.setDiscount(discount);
        lots.add(newLot);
    }

    public parkingLot getFreeLot() {
        int i = 0;
        boolean lotFlag = false;
        parkingLot givenLot = null;
        while (i < lots.size() && lotFlag == false) {
            if(lots.get(i).isFull() == false) {
                givenLot = lots.get(i);
                lotFlag = true;
            }
            i++;
        }
        return givenLot;
    }

    public boolean allLotsFull() {
        for(int i = 0; i < lots.size(); i++) {
            if(!lots.get(i).isFull()) {
                return false;
            }
        }
        return true;
    }

    public void giveGroupInfo(int carID) {
        System.out.printf("Lot %s info for car %s:\nPrice(per second): %s\nDiscount(applied if stayed longer than 5 seconds): %s\n", groupID, carID, price, discount);
    }

    public int getID() {
        return groupID;
    }
    
}