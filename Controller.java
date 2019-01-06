package ElevatorTestTask;
import java.util.HashSet;

public class Controller {
    private int startCabinFloor = 1;
    private int currentCabinFloor;
    private int maxCabinFloor;
    private int minCabinFloor;
    private int elevatorSpeed = 1;
    private Cabin cabin;
    private HashSet<Person> peopleParameters;
    private HashSet<Person> peopleUp;
    private HashSet<Person> peopleDown;
    private boolean elevatorStopStart;

    public Controller() {
        maxCabinFloor = 4;
        minCabinFloor = startCabinFloor;
        currentCabinFloor = startCabinFloor;
        peopleParameters = new HashSet<>();
        peopleUp = new HashSet<>();
        peopleDown = new HashSet<>();
    }

    public void stop(int floor, String upDown) {
        elevatorStopStart = !elevatorStopStart;
        int catchFloor = floor;
        String catchUpDown = upDown;
    }

    public void start() {
        System.out.println("Elevator is stay on the " + currentCabinFloor + "th floor.");
        cabin.doorStatus();
        numberOfPeopleWhoWantGoUpOrDown();
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
                    System.out.println("Elevator and person ( " + peopleUp + " ) ow on the floor " + (currentCabinFloor));
                }
                if (tempPers.getUpDown().equals("Up") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    System.out.println(tempPers + " goes out of the elevator");
                    peopleUp.remove(tempPers);
                    System.out.println("Elevator and person ( " + peopleUp + " ) ow on the floor " + (currentCabinFloor));

                }
            }
            System.out.println("numberOfPeopleInCabin = " + peopleUp.size());

            if (cabin.getOpenClosed() == true) {
                cabin.openCloseDoor();
            }
            currentCabinFloor++;
            if (currentCabinFloor == 5) {
                currentCabinFloor = maxCabinFloor;
            }
        }
    }
    //-----------------------------------------------------------------------

    public void cabinMovementAlgorithmDown() {
        System.out.println(" -----------cabinMovementAlgorithmDown------------------ ");

        int tempMinCabinFloor = 5;
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
                    System.out.println("Elevator and person ( " + peopleDown + " ) ow on the floor "
                            + (currentCabinFloor));
                }
                if (tempPers.getUpDown().equals("Down") && currentCabinFloor == tempPers.getDesiredFloor()) {
                    System.out.println(tempPers + " goes out of the elevator");
                    peopleDown.remove(tempPers);
                    System.out.println("Elevator and person ( " + peopleDown + " ) ow on the floor "
                            + (currentCabinFloor));
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
        this.cabin = cabin;
    }

    public void checkDirection() {
        if (currentCabinFloor == startCabinFloor && cabin.getOpenClosed() == false) {
            System.out.println("The door of the elevator is closed. "
                    + "The elevator will go up.");
        }

        if (currentCabinFloor == maxCabinFloor && cabin.getOpenClosed() == false) {
            System.out.println("The door of the elevator is closed. "
                    + "The elevator will go down.");
        }
    }

    //----------------------- Start (everything that related to people)------------------------------
    public void addPerson(Person person) {
        peopleParameters.add(person);
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
        System.out.println("up = " + up);
        System.out.println("down = " + down);
        if (up > 0 && down > 0) {
            cabinMovementAlgorithmUp();
            cabinMovementAlgorithmDown();
        }
    }
    //--------------------- End (everything that related to people) ----------------------------------   
}
