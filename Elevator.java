package ElevatorTestTask;

public class Elevator {

    private Cabin cabin;
    private Controller controller;

    public Elevator(int elevatorShaft) {
        controller = new Controller();
        for (int i = 0; i < elevatorShaft; i++) {   
            cabin = new Cabin();                    
            controller.getCabinLabel(cabin);        
        }       
    }

    public void addPerson(Person person) {
        controller.addPerson(person);
    }

    public void start() {
        controller.start();
    }

    public void stop(int floor, String upDown) {
        controller.stop(floor, upDown);
    }

    public void sendNumberOfFloors(int maxCabinFloor) {
        controller.setMaxCabinFloor(maxCabinFloor);
    }

    public void sendNumberOfCabinToController(int numberOfCabin) {
        controller.getNumberOfCabinToController(numberOfCabin);
    }
}





