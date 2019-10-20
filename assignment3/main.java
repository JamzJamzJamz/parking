package assignment3;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import assignment3.parkingLot;
import assignment3.car;
import assignment3.ticket;

import java.io.*;
import java.io.File;


public class main {
    static ArrayList<parkingLot> lots;
    static ArrayList<car> cars;

    public static void main(String[] args) throws FileNotFoundException {
        /*
        parkingLot toyotaCenter = new parkingLot(0);
        Scanner in = new Scanner(System.in);
        System.out.println("What is the car's ID?");
        int carID = in.nextInt();
        car honda = new car(carID);
        toyotaCenter.carEnter(honda);
        System.out.println("Please wait a bit before typing again...");
        String test = in.next();
        toyotaCenter.carExit(honda.getTicket());
        */
        File test1 = new File("D:\\School\\COSC_4353\\parking\\assignment3\\test1.txt");
        Scanner fileRead = new Scanner(test1);

        fileRead.nextLine();
        String fileLine = "";

        lots = new ArrayList<parkingLot>();
        cars = new ArrayList<car>();

        //Parking Lots
        int i = 0;
        while(fileRead.hasNextInt()) {
            lots.add(new parkingLot(fileRead.nextInt(), i));
            i++;
        }
        fileRead.nextLine();
        fileRead.nextLine();
        fileRead.nextLine();
        //Cars
        while(fileRead.hasNextInt()) {
            int carID = fileRead.nextInt();
            cars.add(new car(carID));
        }
        fileRead.nextLine();
        fileRead.nextLine();
        fileRead.nextLine();
        while(fileRead.hasNext()) {
            fileLine = fileRead.nextLine();
            Scanner lineRead = new Scanner(fileLine);
            int option = lineRead.nextInt();
            switch(option) {
                case 1:
                    //Enter
                    car enterCar = findCar(lineRead.nextInt());
                    int j = 0;
                    boolean carParked = false;
                    while(carParked == false) {
                        carParked = lots.get(j).carEnter(enterCar);
                        j++;
                    }

                    break;
                case 2:
                    //Exit
                    car exitCar = findCar(lineRead.nextInt());
                    parkingLot exitLot = exitCar.getLot();
                    exitLot.carExit(exitCar.getTicket());
                    break;
                case 3:
                    //Wait
                    try {
                        TimeUnit.MILLISECONDS.sleep(lineRead.nextInt());
                    } catch(InterruptedException e) {

                    }
                    break;
            }
        }


        fileRead.close();
    }

    public static car findCar(int findID) {
        boolean flag = false;
        int i = 0;
        car result = null;
        while (!flag) {
            if (cars.get(i).getID() == findID) {
                result = cars.get(i);
                flag = true;
            }
            i++;
        }
        return result;
    }


}