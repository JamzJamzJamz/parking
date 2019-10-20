package assignment3;
import java.time.LocalDateTime;

public class ticket {
    public Long ID;
    public LocalDateTime entryTime;
    public int carID;

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