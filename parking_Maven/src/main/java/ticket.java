package main.java;

import java.time.LocalDateTime;

public class ticket {
    private Long ID;
    private LocalDateTime entryTime;
    private int carID;


    public ticket(LocalDateTime enterTime, Long lotID, int carID) {
        this.entryTime = enterTime;
        this.ID = lotID;
        this.carID = carID;
    }

    public Long getID() {
        return this.ID;
    }

    public int getCar() {
        return carID;
    }
    
    public LocalDateTime getEntryTime() {
        return this.entryTime;
    }
}