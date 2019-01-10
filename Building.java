package ElTestTask2;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;

public class Building {

    Scanner scanner = new Scanner(System.in);
    private int numberOfPiole;
    private int elevatorShaft;
    public static int numberOfFloors;
    private Elevator elevator;
    private Person person;
    private ExternaPanel externPanel ;
    private ExternaPanel[] externPan ;

    public Building() {
        selectConditions();
    }

    private void selectConditions() {
        System.out.println("Hi. let's start? :)");

        while (true) {
            System.out.println("How many people do you want to use in total? "
                    + "Enter a number from 1 to 20");
            numberOfPiole = scanner.nextInt();
            if (numberOfPiole >= 1 && numberOfPiole <= 20) {
                break;
            } else {
                System.out.println("You entered an invalid value. Try again!");
            }
        }
        while (true) {
            System.out.println("How many floors do you want to create?"
                    + " Enter a number from 4 to 15");
            numberOfFloors = scanner.nextInt();
            if (numberOfFloors >= 4 && numberOfFloors <= 15) {
                createExternalPanel();
                break;
            } else {
                System.out.println("You entered an invalid value. Try again!");
            }
        }
        while (true) {
            System.out.println("How many elevator shafts do you want to "
                    + "create (1 or 2)? Enter a number.");
            elevatorShaft = scanner.nextInt();
            if (elevatorShaft == 1 || elevatorShaft == 2) {
                elevator = new Elevator(elevatorShaft); 
                break;
            } else {
                System.out.println("You entered an invalid value. Try again!");
            }
        }
        allParameters();
        elevator.start(); //  start for elevator
    }

    private void allParameters() {
        System.out.println("----------------------------------");
        System.out.println("Thank you. You have entered the following parameters:");
        System.out.println("numberOfPiole = " + numberOfPiole);
        System.out.println("numberOfFloors = " + numberOfFloors);
        System.out.println("elevatorShaft = " + elevatorShaft);
        System.out.println("Let's create these objects!");
        System.out.println("----------------------------------");
        sendNumberOfCabinToControllerViaElevator();
        createPerson();
    }

    private void createPerson() {
        Random random = new Random();
        System.out.println("Each person presses the elevator call button.");
        System.out.println("----------------------------------");

        for (int i = 0; i < numberOfPiole; i++) {
            int a = random.nextInt(numberOfFloors) + 1;
            ExternaPanel tempExternPanel = externPan[(a-1)] ;
            tempExternPanel.callTheElevator(a); 
            int b = random.nextInt(numberOfFloors) + 1;
            person = new Person(tempExternPanel.getLevelOfFlorCallBatton(), b);
            elevator.addPerson(person);
        }
    }

    private void sendNumberOfCabinToControllerViaElevator() {
        elevator.sendNumberOfCabinToController(elevatorShaft);
    }
    
    private void createExternalPanel(){
        externPan = new ExternaPanel[numberOfFloors] ;
        for (int i = 0 ; i <numberOfFloors ; i++){
            externPanel = new ExternaPanel();
            externPan[i] = externPanel ;
        }
    }
}






//        for (int i = 0; i < numberOfPiole; i++) {
//            int a = random.nextInt(numberOfFloors) + 1;
////                   System.out.println("a = "+a);
//            ExternaPanel tempExternPanel = externPan[(a-1)] ;
////                    System.out.println("externPan[a-1] = "+ externPan[(a-1)]);
////                    System.out.println("tempExternPanel = "+ tempExternPanel);
//            tempExternPanel.callTheElevator(a); 
//            int b = random.nextInt(numberOfFloors) + 1;
////                    System.out.println("tempExternPanel.getLevelOfFlorCallBatton() = "+tempExternPanel.getLevelOfFlorCallBatton()); //**************************
//            person = new Person(tempExternPanel.getLevelOfFlorCallBatton(), b);
////                    System.out.println("tempExternPanel.getLevelOfFlorCallBatton() = "+tempExternPanel.getLevelOfFlorCallBatton()); //**************************
//            elevator.addPerson(person);
//        }
//    }