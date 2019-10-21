package parking;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import parking.parkingLot;
import parking.car;
import parking.ticket;

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

        Scanner in = new Scanner(System.in);
        System.out.print("Give a file: ");

        //File test1 = new File("D:\\School\\COSC_4353\\parking\\assignment3\\test1.txt");
        try {
            File test1 = new File(in.nextLine());
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
            //Cars
            while(fileRead.hasNextInt()) {
                int carID = fileRead.nextInt();
                cars.add(new car(carID));
            }
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
                        if(enterCar != null) {
                            int j = 0;
                            while(j < lots.size() && !enterCar.enterLot(lots.get(j))) {
                                j++;
                            }
                            if(j >= lots.size()) {
                                System.out.println("All lots filled to capacity.");
                            }
                        } else {
                            System.out.println("Invalid car.");
                        }
                        break;
                    case 2:
                        //Exit
                        car exitCar = findCar(lineRead.nextInt());
                        if(exitCar != null) {
                            exitCar.exitLot();
                        } else {
                            System.out.println("Invalid car.");
                        }
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

            System.out.println();

            for(int j = 0; j < lots.size(); j++) {
                lots.get(j).getHistory();
                System.out.println();
            }


            in.close();
            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File input");
        }
    }

    public static car findCar(int findID) {
        boolean flag = false;
        int i = 0;
        car result = null;
        while (!flag && i < cars.size()) {
            if (cars.get(i).getID() == findID) {
                result = cars.get(i);
                flag = true;
            }
            i++;
        }
        return result;
    }


}