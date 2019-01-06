package ElevatorTestTask;

public class Elevator {
    private Cabin cabin;
    private Controller controller;

    public Elevator() {
        cabin = new Cabin();
        controller = new Controller();
        controller.getCabinLabel(cabin);
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
}





