package ElevatorTestTask;

import java.util.HashSet;
import java.util.Random;

public class Controller {

    private int startCabinFloor = 1;
    private int currentCabinFloor;
    private int maxCabinFloor;
    private int minCabinFloor;
    private int elevatorSpeed = 1;
    private Cabin cabin; 
    private HashSet<Person> peopleParameters;
    private HashSet<Person> peopleParameters2;
    private HashSet<Person> peopleUp;
    private HashSet<Person> peopleDown;
    private HashSet<Cabin> cabins; 
    private boolean elevatorStopStart;
    private int numberOfCabins; 

    public Controller() {
        minCabinFloor = startCabinFloor;
        currentCabinFloor = startCabinFloor;
        peopleParameters = new HashSet<>();
        peopleParameters2 = new HashSet<>();
        peopleUp = new HashSet<>();
        peopleDown = new HashSet<>();
        cabins = new HashSet<>();
    }

    public void setMaxCabinFloor(int maxCabinFloor) {
        this.maxCabinFloor = maxCabinFloor;
    }

    public void stop(int floor, String upDown) {
        elevatorStopStart = !elevatorStopStart;
        int catchFloor = floor;
        String catchUpDown = upDown;
    }

    public void start() {
        System.out.println("Elevator(s) is stay on the " + currentCabinFloor + "th floor.");
        createNumberOfCabin();
    }

    //---------- Everything that related to cabinMovementAlgorithm-----------------------
    public void cabinMovementAlgorithmUp() {
        System.out.println(" ----------cabinMovementAlgorithmUp-------------------- ");
        int tempMaxCabinFloor = 0;
        for (Person tempPers : peopleParameters) {
            if (tempPers.getUpDown().equals("Up") && tempMaxCabinFloor <= tempPers.getDesiredFloor()) {
                tempMaxCabinFloor = tempPers.getDesiredFloor();
                maxCabinFloor = tempMaxCabinFloor;
            }
        }
        System.out.println("The elevator should go up to the " + maxCabinFloor + "th floor.");

        for (int i = currentCabinFloor - 1; i < maxCabinFloor; i++) {
            System.out.println("--------------------------------");
            System.out.println("Elevator on the floor = " + currentCabinFloor);

            for (Person tempPers : peopleParameters) {
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getCurrentFloor()
                        || tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    if (cabin.getOpenClosed() == false) {
                        cabin.doorStatus();
                        cabin.openCloseDoor();
                    }
                }
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getCurrentFloor()) {
                    System.out.println(tempPers + " comes into the elevator");
                    peopleUp.add(tempPers);
                    System.out.println("Elevator and person ( " + peopleUp + " ) "
                            + "ow on the floor " + (currentCabinFloor));
                }
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    System.out.println(tempPers + " goes out of the elevator");
                    peopleUp.remove(tempPers);
                    System.out.println("Elevator and person ( " + peopleUp + " )"
                            + " ow on the floor " + (currentCabinFloor));

                }
            }
            System.out.println("numberOfPeopleInCabin = " + peopleUp.size());

            if (cabin.getOpenClosed() == true) {
                cabin.openCloseDoor();
            }
            currentCabinFloor++;
            if (currentCabinFloor == maxCabinFloor + 1) {
                currentCabinFloor = maxCabinFloor;
            }
        }
    }

    //----------------------------------------------------------------------------
    public void cabinMovementFustUp() {
        System.out.println(" ----------cabinMovementFustUp-------------------- ");

        int tempMinCabinFloor = 21;
        for (Person tempPers : peopleParameters) {
            if (tempPers.getUpDown().equals("Down") && tempMinCabinFloor >= tempPers.getDesiredFloor()) {
                tempMinCabinFloor = tempPers.getDesiredFloor();
                maxCabinFloor = tempMinCabinFloor;
            }
        }
        System.out.println("The elevator should go up to the " + maxCabinFloor + "th floor.");

        for (int i = currentCabinFloor - 1; i < maxCabinFloor; i++) {
            System.out.println("--------------------------------");
            System.out.println("Elevator on the floor = " + currentCabinFloor);
            System.out.println("numberOfPeopleInCabin = " + peopleUp.size());
   
            currentCabinFloor++;
            if (currentCabinFloor == maxCabinFloor + 1) {
                currentCabinFloor = maxCabinFloor;
            }
        }
    }
    //-----------------------------------------------------------------------

    public void cabinMovementAlgorithmDown() {
        System.out.println(" -----------cabinMovementAlgorithmDown------------------ ");

        int tempMinCabinFloor = 21;
        for (Person tempPers : peopleParameters) {
            if (tempPers.getUpDown().equals("Down") && tempMinCabinFloor >= tempPers.getDesiredFloor()) {
                tempMinCabinFloor = tempPers.getDesiredFloor();
                minCabinFloor = tempMinCabinFloor;
            }
        }
        System.out.println("The elevator should go down to the " + minCabinFloor + "th floor.");

        for (int i = currentCabinFloor + 1; i > minCabinFloor; i--) {
            System.out.println("--------------------------------");
            System.out.println("Elevator on the floor = " + (i - 1));

            for (Person tempPers : peopleParameters) {
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getCurrentFloor()
                        || tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    if (cabin.getOpenClosed() == false) {
                        cabin.doorStatus();
                        cabin.openCloseDoor();
                    }
                }
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getCurrentFloor()) {
                    System.out.println(tempPers + " comes into the elevator");
                    peopleDown.add(tempPers);
                    System.out.println("Elevator and person ( " + peopleDown + " ) "
                            + "ow on the floor " + (currentCabinFloor));
                }
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    System.out.println(tempPers + " goes out of the elevator");
                    peopleDown.remove(tempPers);
                    System.out.println("Elevator and person ( " + peopleDown + " )"
                            + " ow on the floor " + (currentCabinFloor));
                }
            }
            System.out.println("numberOfPeopleInCabin = " + peopleDown.size());
            if (cabin.getOpenClosed() == true) {
                cabin.openCloseDoor();
            }
            currentCabinFloor--;
            if (currentCabinFloor == 0) {
                currentCabinFloor = minCabinFloor;
            }
        }
    }

    //----------------------- Everything that related to Cabin ------------------------------
    public void getCabinLabel(Cabin cabin) {
        cabins.add(cabin);
    }

    //----------------------- Start (everything that related to people)------------------------------
    public void addPerson(Person person) {
        if (numberOfCabins == 1) {
            peopleParameters.add(person);
        }

        if (numberOfCabins == 2) {
            Random random = new Random();
            int counter = random.nextInt(numberOfCabins);
            if (counter == 0) {
                peopleParameters.add(person);
            }
            if (counter == 1) {
                peopleParameters2.add(person);
            }
        }
    }

    public void numberOfPeopleWhoWantGoUpOrDown() {
        int up = 0;
        int down = 0;
        for (Person tempPers : peopleParameters) {
            if (tempPers.getUpDown().equals("Up")) {
                up++;
            } else {
                down++;
            }
        }
        System.out.println("People up = " + up);
        System.out.println("People down = " + down);

        if (up > 0 && down > 0) {
            cabinMovementAlgorithmUp();
            cabinMovementAlgorithmDown();
        }
        if (up == 0 && down > 0) {
            cabinMovementFustUp(); 
            cabinMovementAlgorithmDown(); 
        }
        if (up > 0 && down == 0) {
            cabinMovementAlgorithmUp();
        }
    }

    //--------------------- End (everything that related to people) ----------------------------------   
    public void createNumberOfCabin() {
        if (cabins.size() == 1) {
            for (Cabin newCabin : cabins) {
                this.cabin = newCabin;
                cabin.doorStatus();                 
                numberOfPeopleWhoWantGoUpOrDown(); 
            }
        }
        if (cabins.size() == 2) {
            Cabin[] myCabinArray = {};
            myCabinArray = cabins.toArray(new Cabin[cabins.size()]);
            System.out.println("-------------------- Elevator 1 ---------------------");
            System.out.println("-------------------- Elevatir 1 ---------------------");
            this.cabin = myCabinArray[0];
            cabin.doorStatus();                 
            numberOfPeopleWhoWantGoUpOrDown(); 
            System.out.println("-------------------- Elevator 2 ---------------------");
            System.out.println("-------------------- Elevatir 2 ---------------------");
            Cabin cabin2 = myCabinArray[1];
            cabin2.doorStatus();                 
            numberOfPeopleWhoWantGoUpOrDown2(); 
        }
    }

    public void getNumberOfCabinToController(int numberOfCabin) {
        numberOfCabins = numberOfCabin;
    }

    /*
     *
     */
    //---------- Everything that related to cabinMovementAlgorithm-----------------------
    public void cabinMovementAlgorithmUp2() {
        int maxCabinFloor2 = maxCabinFloor ;
        System.out.println(" ----------cabinMovementAlgorithmUp-------------------- ");
        int tempMaxCabinFloor2 = 0;
        for (Person tempPers : peopleParameters2) {
            if (tempPers.getUpDown().equals("Up") && tempMaxCabinFloor2 <= tempPers.getDesiredFloor()) {
                tempMaxCabinFloor2 = tempPers.getDesiredFloor();
                maxCabinFloor2 = tempMaxCabinFloor2;
            }
        }
        System.out.println("The elevator should go up to the " + maxCabinFloor2 + "th floor.");
        int startCabinFloor = 1 ;
        currentCabinFloor = startCabinFloor ;
        for (int i = currentCabinFloor - 1; i < maxCabinFloor2; i++) {
            System.out.println("--------------------------------");
            System.out.println("Elevator on the floor = " + currentCabinFloor);

            for (Person tempPers : peopleParameters2) {
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getCurrentFloor()
                        || tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    if (cabin.getOpenClosed() == false) {
                        cabin.doorStatus();
                        cabin.openCloseDoor();
                    }
                }
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getCurrentFloor()) {
                    System.out.println(tempPers + " comes into the elevator");
                    peopleUp.add(tempPers);
                    System.out.println("Elevator and person ( " + peopleUp + " )"
                            + " ow on the floor " + (currentCabinFloor));
                }
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    System.out.println(tempPers + " goes out of the elevator");
                    peopleUp.remove(tempPers);
                    System.out.println("Elevator and person ( " + peopleUp + " )"
                            + " ow on the floor " + (currentCabinFloor));

                }
            }
            System.out.println("numberOfPeopleInCabin = " + peopleUp.size());

            if (cabin.getOpenClosed() == true) {
                cabin.openCloseDoor();
            }
            currentCabinFloor++;
            if (currentCabinFloor == maxCabinFloor2 + 1) {
                currentCabinFloor = maxCabinFloor2;
            }
        }
    }

    //------------------------------------------------------------------------
    public void cabinMovementFustUp2() {
        int maxCabinFloor2 = maxCabinFloor ;
        System.out.println(" ----------cabinMovementFustUp-------------------- ");

        int tempMinCabinFloor = 21;
        int tempMaxCabinFloor;
        for (Person tempPers : peopleParameters2) {
            if (tempPers.getUpDown().equals("Down") && tempMinCabinFloor >= tempPers.getDesiredFloor()) {
                tempMinCabinFloor = tempPers.getDesiredFloor();
                maxCabinFloor2 = tempMinCabinFloor;
            }
        }
        System.out.println("The elevator should go up to the " + maxCabinFloor2 + "th floor.");
        int startCabinFloor = 1 ;
        currentCabinFloor = startCabinFloor ;
        for (int i = currentCabinFloor - 1; i < maxCabinFloor2; i++) {
            System.out.println("--------------------------------");
            System.out.println("Elevator on the floor = " + currentCabinFloor);
            System.out.println("numberOfPeopleInCabin = " + peopleUp.size());
            
            currentCabinFloor++;
            if (currentCabinFloor == maxCabinFloor2 + 1) {
                currentCabinFloor = maxCabinFloor2;
            }
        }
    }
    //-----------------------------------------------------------------------

    public void cabinMovementAlgorithmDown2() {
        System.out.println(" -----------cabinMovementAlgorithmDown------------------ ");

        int tempMinCabinFloor = 21;
        for (Person tempPers : peopleParameters2) {
            if (tempPers.getUpDown().equals("Down") && tempMinCabinFloor >= tempPers.getDesiredFloor()) {
                tempMinCabinFloor = tempPers.getDesiredFloor();
                minCabinFloor = tempMinCabinFloor;
            }
        }
        System.out.println("The elevator should go down to the " + minCabinFloor + "th floor.");

        for (int i = currentCabinFloor + 1; i > minCabinFloor; i--) {
            System.out.println("--------------------------------");
            System.out.println("Elevator on the floor = " + (i - 1));

            for (Person tempPers : peopleParameters2) {
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getCurrentFloor()
                        || tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    if (cabin.getOpenClosed() == false) {
                        cabin.doorStatus();
                        cabin.openCloseDoor();
                    }
                }
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getCurrentFloor()) {
                    System.out.println(tempPers + " comes into the elevator");
                    peopleDown.add(tempPers);
                    System.out.println("Elevator and person ( " + peopleDown + " ) "
                            + "ow on the floor " + (currentCabinFloor));
                }
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    System.out.println(tempPers + " goes out of the elevator");
                    peopleDown.remove(tempPers);
                    System.out.println("Elevator and person ( " + peopleDown + " ) "
                            + "ow on the floor " + (currentCabinFloor));
                }
            }
            System.out.println("numberOfPeopleInCabin = " + peopleDown.size());
            if (cabin.getOpenClosed() == true) {
                cabin.openCloseDoor();
            }
            currentCabinFloor--;
            if (currentCabinFloor == 0) {
                currentCabinFloor = minCabinFloor;
            }
        }
    }

    //----------------------- Start (everything that related to people)------------------------------
    public void numberOfPeopleWhoWantGoUpOrDown2() {
        int up = 0;
        int down = 0;
        for (Person tempPers : peopleParameters2) {
            if (tempPers.getUpDown().equals("Up")) {
                up++;
            } else {
                down++;
            }
        }
        System.out.println("People up = " + up);
        System.out.println("People down = " + down);

        if (up > 0 && down > 0) {
            cabinMovementAlgorithmUp2();
            cabinMovementAlgorithmDown2();
        }
        if (up == 0 && down > 0) {
            cabinMovementFustUp2();         
            cabinMovementAlgorithmDown2();  
        }
        if (up > 0 && down == 0) {
            cabinMovementAlgorithmUp2();
        }
    }
}

