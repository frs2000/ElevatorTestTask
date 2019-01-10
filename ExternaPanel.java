package ElTestTask2;

public class ExternaPanel {

    private int floorCallButton;

    public void callTheElevator(int a) {
        floorCallButton = a;
    }

    public int getLevelOfFlorCallBatton() {
        return floorCallButton;
    }
}
