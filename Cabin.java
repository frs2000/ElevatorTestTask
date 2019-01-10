package ElevatorTestTask;

public class Cabin {

    private boolean door;
    private int numberOfPeopleInCabin = 0;
    private int maxTotalPeopleWeight = 700;
    private int onePeopleWeight = 70;
    private int maxNumberOfPiople = maxTotalPeopleWeight / onePeopleWeight; // = 10 

    public void openCloseDoor() {
        door = !door;
        if (door == false) {
            System.out.println("Request. Door closed");
        }
        if (door == true) {
            System.out.println("Request. The door is open");
        }
    }

    public boolean getOpenClosed() {
        return door;
    }

    public void doorStatus() {
        if (door == false) {
            System.out.println("Door closed");
        } else {
            System.out.println("The door is open");
        }
    }

    public int getMaxNumberOfPiople() {
        return maxNumberOfPiople;
    }
}
