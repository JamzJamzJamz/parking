package assignment3;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Scanner;

import assignment3.parkingLot;
import assignment3.car;
import assignment3.ticket;

import java.io.*;


public class main {
    public static void main(String[] args) {
        /*
        LocalDateTime firstDate = LocalDateTime.now();

        System.out.println(firstDate);
        
        Scanner in = new Scanner(System.in);

        String message = in.nextLine();

        LocalDateTime secondDate = LocalDateTime.now();
        System.out.println(secondDate);
        Long difference = Duration.between(firstDate, secondDate).getSeconds();
        System.out.println(difference);
        */

        parkingLot toyotaCenter = new parkingLot(0);
        Scanner in = new Scanner(System.in);
        System.out.println("What is the car's ID?");
        int carID = in.nextInt();
        car honda = new car(carID);
        toyotaCenter.carEnter(honda);
        System.out.println("Please wait a bit before typing again...");
        String test = in.next();
        toyotaCenter.carExit(honda.getTicket());


        


    }
}