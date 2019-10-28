package main.java;//import java.time.LocalDateTime;
//import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//import parking.parkingLot;
//import parking.car;
//import parking.ticket;
//import parking.lotGroup;

import java.io.*;
import java.io.File;


public class main {
    static ArrayList<parkingLot> lots;
    static ArrayList<car> cars;
    static ArrayList<lotGroup> groups;

    public static void main(String[] args) throws FileNotFoundException {
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
            groups = new ArrayList<lotGroup>();

            //Lot Groups
            int i = 0;
            while(fileRead.hasNextDouble()) {
                fileLine = fileRead.nextLine();
                Scanner groupLine = new Scanner(fileLine);
                double price = groupLine.nextDouble();
                double discount = groupLine.nextDouble();
                groupLine.close();
                groups.add(new lotGroup(i, price, discount));
                i++;
            }
            fileRead.nextLine();
            //fileRead.nextLine();
            //Parking Lots
            i = 0;
            while(fileRead.hasNextInt()) {
                fileLine = fileRead.nextLine();
                Scanner lotLine = new Scanner(fileLine);
                int cap = lotLine.nextInt();
                int groupID = lotLine.nextInt();
                parkingLot newLot = new parkingLot(cap, i, groups.get(groupID));
                lots.add(newLot);
                i++;
                lotLine.close();
            }
            fileRead.nextLine();
            //fileRead.nextLine();

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
                            boolean enteredFlag = false;
                            int groupIndex = 0;
                            System.out.printf("Car %s is trying to enter...\n", enterCar.getID());
                            while(groupIndex < groups.size() && enteredFlag == false) {
                                //System.out.println("Looking for group to enter...");
                                if(!groups.get(groupIndex).allLotsFull()) {
                                    //System.out.printf("Looking for lot in group %s...\n", groups.get(groupIndex).getID());
                                    enterCar.enterLot(groups.get(groupIndex).getFreeLot());
                                    enteredFlag = true;
                                }
                                groupIndex++;
                            }
                            if(enteredFlag == false && groupIndex >= groups.size()) {
                                System.out.println("All lots in all groups full");
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
                            System.out.println("Waiting...");
                            TimeUnit.MILLISECONDS.sleep(lineRead.nextInt());
                        } catch(InterruptedException e) {

                        }
                        break;
                    case 4:
                        //Check group prices and discounts
                        car inputCar = findCar(lineRead.nextInt());
                        for(int k = 0; k < groups.size(); k++) {
                            groups.get(k).giveGroupInfo(inputCar.getID());
                            System.out.println();
                        }
                        
                }
                lineRead.close();
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