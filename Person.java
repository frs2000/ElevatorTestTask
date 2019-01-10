package ElevatorTestTask;

public class Person {
    private int currentFloor ;
    private int desiredFloor ;
    private String upDown ;
    private boolean doYouNeedElevator ; // false = no or true = yes
    
    public Person(int currentFloor, int desiredFloor  ){
        if (currentFloor >0 && currentFloor<= Building.numberOfFloors && 
                desiredFloor >0 && desiredFloor <= Building.numberOfFloors){
        this.currentFloor = currentFloor ;
        this.desiredFloor = desiredFloor;
        doYouNeedElevator = true ;
        setUpDown();
        }
        else System.out.println("You can not create Person and set settings "
                + "becouse you wrote wrong some of next parameters: " 
                + "currentFloor or desiredFloor");
    }
    
    private void setUpDown(){
        upDown = currentFloor < desiredFloor ? "Up" : "Down" ;
    }
    
    public String getUpDown(){
        return upDown ;
    }
    
    public int getCurrentFloor(){
        return currentFloor ;
    }
    
    public int getDesiredFloor(){
        return desiredFloor ;
    }

    public void setDoYouNeedElevator(){
        doYouNeedElevator = !doYouNeedElevator ;
    }
    
    public boolean getDoYouNeedElevator(){
        return doYouNeedElevator ;
    }  
}
